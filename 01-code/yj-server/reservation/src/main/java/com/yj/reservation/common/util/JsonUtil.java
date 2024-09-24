package com.yj.reservation.common.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Json数据操作工具包包括：
 * 1.对象转换Json字符串
 * 2.Json字符串转换对象
 */
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();


        public static void saveDataAsJsonFile(HashMap<String,Object> data, String filePath){

        try{
//            transform to json format
//            String json = mapper.writeValueAsString(data); 这个是将数据先转换成json字符串再写入文件 这里我们是直接将数据写入文件
//            write to file
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            mapper.writeValue(file,data);
//            mapper.
            System.out.println("JSON文件保存成功！路径: " + file.getAbsolutePath());
//            System.out.println("data are saved to"+filePath+"Successfully");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static JSONObject parseJSONObject(String source) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(source);
        } catch (JSONException e) {
            logger.error("valid jsonstring," + source + ":", e);
        }
        return jsonObject;
    }

    public static JSONArray parseJSONArray(String source) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(source);
        } catch (JSONException e) {
            logger.error("valid jsonstring," + source + ":", e);
        }
        return jsonArray;
    }

    public static String toJsonString(Object obj) {
//        try {
//            return mapper.writeValueAsString(obj);
//        } catch (JsonProcessingException e) {
//            logger.error("JsonResult process failed:", e);
//            throw new RuntimeJsonMappingException("json process failed");
//        }

        return gson.toJson(obj);
    }

    public static <T> T fromJson(String jsonStr, Class<T> collectionClass, Class<?>... elementClasses) throws Exception {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        return mapper.readValue(jsonStr, javaType);
    }

    public static <T> T fromJson(String jsonStr, Class<T> clazz) throws Exception {
        return mapper.readValue(jsonStr, clazz);
    }

    private static Gson gson = new Gson();


    public static <T> List<T> fromList(String json, Class<T> clazz) {
        return gson.fromJson(json, TypeToken.getParameterized(List.class, clazz).getType());
    }

    public static <T> T fromStrJson(String jsonStr, Class<T> clazz){
        return gson.fromJson(jsonStr, clazz);
    }

//
//  static class Hello {
//    public int id;
//    public String name;
//  }
////
//  public static void main(String[] args) throws Exception {
//    Hello hello = new Hello();
//    hello.id = 1;
//    hello.name = "wangwu";
//    List<Hello> list = new ArrayList<>();
//    list.add(hello);
////    Map<String, Hello> map = new HashMap<>();
////    map.put("123", hello);
////    String str = toJsonString(map);
////    Map<String, Hello> hello1 = fromJson(str, Map.class, String.class, Hello.class);
////    System.out.println(hello1.getClass());
//    String str = toJsonString(list);
//    System.out.println(str);
//    list = fromJson(str, List.class, Hello.class);
//    System.out.println(list.get(0).name);
//  }
}
