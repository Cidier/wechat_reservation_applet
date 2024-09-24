package com.yj.reservation.controller;


import com.yj.reservation.business.FileBusiness;
import com.yj.reservation.business.MenuBusiness;
import com.yj.reservation.common.bean.JsonResult;
import com.yj.reservation.common.oss.OSSUtil;
import com.yj.reservation.common.util.JsonUtil;
import com.yj.reservation.pojo.cms.vo.MmCmsMenuVO;
import com.yj.reservation.service.cms.MmCmsArticlesContentService;
import com.yj.reservation.service.cms.MmCmsArticlesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor

@RequestMapping("/mm-cms-transfer-json")
public class JsonController {
    private final MenuBusiness menuBusiness;
    private final MmCmsArticlesService articleService;
    private final MmCmsArticlesContentService articleContentService;


    @PostMapping("/list")
    public JsonResult list(){
//        HashMap<String, List<MmCmsMenuVO>> hashMapMenu = new HashMap();
        HashMap<String,Object> hashMapMenu = new HashMap();
        HashMap<String,Object> hashMapArticle = new HashMap();
        HashMap<String,Object> hashMapArticleContent = new HashMap();

        hashMapMenu.put("menu",menuBusiness.buildTreeGrid());
        hashMapArticle.put("article",articleService.list());
        hashMapArticleContent.put("articleContent",articleContentService.list());
//文件路径
        //这里需要配置一个 宿主机的绝对路径 如：/data/resources/
        // windows 要设置在本地地址
        String os = System.getProperty("os.name");
        System.out.println("哈哈哈哈哈哈哈哈哈哈哈"+os);
        String prefix = "/data/resources/";
        if(os.toLowerCase().contains("windows")){
            prefix = "D:\\uploadJson\\";
        }
        String menu = "menu.json";
        String article = "article.json";
        String articleContent = "articleContent.json";
        String path1 = prefix+menu;
        String path2 = prefix+article;
        String path3 = prefix+articleContent;
        // 获取文件存储路径（绝对路径）
//        String path = fileBusiness.toDateFilePath(prefix);
        System.out.println("要保存的文件路径为"+path1+path2+path3+"\n\n\n");

//        fileBusiness.uploadFile(new File(JSON.toJSONString(hashMapMenu)),path1);
        JsonUtil.saveDataAsJsonFile(hashMapArticleContent,path3);

        JsonUtil.saveDataAsJsonFile(hashMapMenu,path1);

       JsonUtil.saveDataAsJsonFile(hashMapArticle,path2);


       Map<String,Object>  uploadToOssMenuMap = uploadToOSS(path1,menu);
        Map<String,Object>  uploadToOssArticleMap = uploadToOSS(path2,article);
        Map<String,Object>  uploadToOssArticleContentMap = uploadToOSS(path3,articleContent);


//        return JsonResult.success().put("page", page);
        return JsonResult.success()
//                .put("menu",menuBusiness.buildTreeGrid())
//                .put("article",articleService.list())
//                .put("articleContent",articleContentService.list())
                .put("jsonOssMenuFile",uploadToOssMenuMap)
                .put("jsonOssArticleFile",uploadToOssArticleMap)
                .put("jsonOssArticleContentFile",uploadToOssArticleContentMap)
        ;

    }

    private Map uploadToOSS(String path, String fileName){

        File jsonFile = new File(path);
        HashMap<String,Object> map = new HashMap();
        if(!jsonFile.exists()){
            throw new RuntimeException("文件不存在"+path);
        }
        try{
//            上传文件
            OSSUtil.getOssBeanInner().putObject(OSSUtil.BUCKET_NAME,fileName,jsonFile);

            // set expired time
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 50);
            String previewUrl = OSSUtil.getOssBeanOut().generatePresignedUrl(OSSUtil.BUCKET_NAME, fileName, expiration).toString();
            System.out.println("文件上传成功到OSS: " + fileName);
            map.put("previewUrl",previewUrl);
            map.put("fileName",fileName);
            return map;


        }catch (Exception e){
            throw new RuntimeException("上传文件到OSS失败: " + fileName, e);
        }
        finally {
            OSSUtil.getOssBeanInner().shutdown();
            OSSUtil.getOssBeanOut().shutdown();
             }
//          map.put("previewUrl", previewUrl);

        //这里需要redis缓存，也就是，如果redis缓存失效后，清除上传后的垃圾文件





    }
}
