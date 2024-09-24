package com.yj.reservation.bean;

import lombok.Data;

@Data
public class FileBean {

    public static final String SUFFIX_XLS = "xls";
    public static final String SUFFIX_XLSX = "xlsx";
    public static final String SUFFIX_PNG = "png";
    public static final String SUFFIX_JPG = "jpg";
    public static final String SUFFIX_JPEG = "jpeg";


    private String fileName;
    private String suffix;
    private String filePath;
}
