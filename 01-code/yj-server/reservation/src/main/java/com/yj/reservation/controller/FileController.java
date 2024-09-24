package com.yj.reservation.controller;


import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ObjectMetadata;

import com.yj.reservation.bean.FileBean;
import com.yj.reservation.business.FileBusiness;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.common.oss.GetFileType;
import com.yj.reservation.common.oss.OSSUtil;
import com.yj.reservation.common.security.annotation.OpenApi;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("file")
//@OpenApi(code=true)
public class FileController {

    @Autowired
    private FileBusiness fileBusiness;

    /*
        上传文件：
            不限制类型上传，将上传位置缓存redis中，并加上过期时间24小时。

            1.将上传的文件名称加上随机数或日期限制
            2.根据上传的日期进行分目录存储
            3.将文件名称，文件存储路径，文件类型 三个信息进行缓存，并通过唯一key进行访问

        */
    @RequestMapping("/upload")
    public JsonResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest req)
            throws IllegalStateException, IOException {
        //req.getServletContext().getRealPath("/WEB-INF/file/upload") 获取当前jar地址
        //这里需要配置一个 宿主机的绝对路径 如：/data/resources/
        // windows 要设置在本地地址
        String os = System.getProperty("os.name");
        System.out.println(os);
        String prefix = "/data/resources/";
        if(os.toLowerCase().contains("windows")){
            prefix = "D:\\upload\\";
        }
        return fileBusiness.uploadFile(file, prefix);
    }


    @GetMapping("/dowload")
    public void dowload(HttpServletResponse response, @RequestParam("fileKey") String fileKey) throws Exception{
        JsonResult result = fileBusiness.findByFileKey(fileKey);
        if(JsonResult.CODE_SUCCESS != result.getErrcode()){
            response.getOutputStream().write(result.toString().getBytes());
            return ;
        }
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", "inline;filename=" + ((FileBean)result.getData().get("fileUploadBean")).getFileName());
        fileBusiness.dowloadFile(response.getOutputStream(), ((FileBean)result.getData().get("fileUploadBean")).getFilePath());
    }


    ///file/uploadOSS
    @OpenApi(code=true)
    @PostMapping("/uploadOSS")
    public JsonResult uploadOSS(HttpServletRequest request){
        System.out.println("哈哈哈哈");
        try {
            //得到上传文件夹名称
            String folderName = request.getParameter("folderName");
            Map<String, Object> map = new HashMap<>();
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile("file");
            // 获取文件名
            assert multipartFile != null;
            String fileName = multipartFile.getOriginalFilename();
            // 获取文件后缀名
            assert fileName != null;
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 最后上传生成的文件名
            String substring = fileName.substring(0, fileName.lastIndexOf("."));

            String previewName = substring + System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
            // oss中的文件夹名
            String ossPreviewName = folderName + "/" + previewName;

            // 创建上传文件的元信息，可以通过文件元信息设置HTTP header(设置了才能通过返回的链接直接访问)。
            ObjectMetadata objectMetadata = new ObjectMetadata();
            //设置文件类型
            objectMetadata.setContentType(GetFileType.getContentType(suffixName));

            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 50);
            //文件预览

            if ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(objectMetadata.getContentType())
                    || "application/vnd.ms-powerpoint".equals(objectMetadata.getContentType()) || "application/msword".equals(objectMetadata.getContentType())) {


                OSSUtil.getOssBeanInner().putObject(OSSUtil.BUCKET_NAME, ossPreviewName, new ByteArrayInputStream(multipartFile.getBytes()), objectMetadata);

                GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(OSSUtil.BUCKET_NAME, ossPreviewName, HttpMethod.GET);
                req.setExpiration(expiration);
                req.setProcess(OSSUtil.STYLE);

                map.put("previewUrl", OSSUtil.getOssBeanOut().generatePresignedUrl(req));

            } else {
                OSSUtil.getOssBeanInner().putObject(OSSUtil.BUCKET_NAME, ossPreviewName, new ByteArrayInputStream(multipartFile.getBytes()), objectMetadata);
                String previewUrl = OSSUtil.getOssBeanOut().generatePresignedUrl(OSSUtil.BUCKET_NAME, ossPreviewName, expiration).toString();
                map.put("previewUrl", previewUrl);
            }
            map.put("fileName", fileName);
            map.put("ossPreviewName", ossPreviewName);
            map.put("fileType", suffixName);


            //这里需要redis缓存，也就是，如果redis缓存失效后，清除上传后的垃圾文件

            OSSUtil.getOssBeanInner().shutdown();
            OSSUtil.getOssBeanOut().shutdown();
            return JsonResult.success().put("file", map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
