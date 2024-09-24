package com.yj.reservation.common.oss;

public class GetFileType {
    public static String getContentType(String suffixName) {

        switch (suffixName){
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
        }
        return "multipart/form-data";
    }
}
