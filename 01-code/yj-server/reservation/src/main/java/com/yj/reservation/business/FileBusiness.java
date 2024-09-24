package com.yj.reservation.business;

import com.yj.reservation.bean.FileBean;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.common.redis.RedisService;
import com.yj.reservation.common.util.DateUtil;
import com.yj.reservation.common.util.JsonUtil;
import com.yj.reservation.common.util.StreamUtil;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Service
public class FileBusiness {

    @Autowired
    private RedisService redisService;

    public JsonResult findByFileKey(String fileKey){
        String uploadBeanJson = (String) redisService.get("UPLOAD:" + fileKey);
        if(uploadBeanJson == null){
            return JsonResult.fail().setMsg("无效参数！");
        }
        FileBean fileUploadBean = null;
        try{
            fileUploadBean = JsonUtil.fromJson(uploadBeanJson, FileBean.class);
        }catch(Exception e){
            return JsonResult.fail().setMsg("无效参数！");
        }
//        if(FileBean.SUFFIX_XLS.equals(fileUploadBean.getSuffix())
//                || FileBean.SUFFIX_XLSX.equals(fileUploadBean.getSuffix())){

            return JsonResult.success().put("fileUploadBean", fileUploadBean);
//        }else{
//            return JsonResult.fail().setMsg("错误的文件类型！");
//        }
    }

    public JsonResult uploadFile(MultipartFile file, String readPath) throws IOException {
        // 判断文件是否为空，空则返回失败页面
        if (file.isEmpty()) {
            return JsonResult.fail().setMsg("File is Not Null!");
        }
        // 获取文件存储路径（绝对路径）
        String path = toDateFilePath(readPath);
        // 获取原文件名
        String fileName = toNumberFileName(file.getOriginalFilename());

        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        System.out.println(suffix);

        // 创建文件实例
        File filePath = new File(path, fileName);
        // 如果文件目录不存在，创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录" + filePath);
        }
        // 写入文件
        file.transferTo(filePath);

        FileBean bean = new FileBean();
        bean.setFileName(fileName);
        bean.setFilePath(filePath.toString());
        bean.setSuffix(suffix);
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
        String key = generator.generate(10);
        redisService.set("UPLOAD:" + key, JsonUtil.toJsonString(bean), 86400L); //24小时

        return JsonResult.success().put("fileKey", key);
    }

//    private to public
    public String toDateFilePath(String realPath) {
        String dateStr = DateUtil.dateToStr(new Date(System.currentTimeMillis()), "yyyyMMdd");
        return realPath + "/" + dateStr;
    }

    private String toNumberFileName(String originalFilename) {
        int max=1000,min=1;
        int ran2 = (int) (Math.random()*(max-min)+min);
        return ran2 + originalFilename;
    }

    public void dowloadFile(OutputStream outputStream, String filePath) throws Exception {
        StreamUtil.is2os(new FileInputStream(filePath), outputStream);
    }
}
