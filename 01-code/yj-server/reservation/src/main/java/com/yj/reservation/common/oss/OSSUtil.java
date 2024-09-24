package com.yj.reservation.common.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.yj.reservation.common.util.LoggerUtil;
import jakarta.servlet.http.HttpSession;

/**
 * 集成阿里云OSS 封装Util使用
 */
public class OSSUtil {
    public static final String STYLE = "imm/previewdoc,copy_1";
    /**
     * 查看自己的节点
     *
     * (接口调用的话)内网上传 oss地址
     */
    public static final String INNER_ENDPOINT = "https://mm-mb-test.oss-cn-beijing-internal.aliyuncs.com";
    /**
     * 查看自己的节点 使用公网地址上传图片
     * (接口调用的话)外网下载或预览 oss地址  oss-cn-beijing.aliyuncs.com
     * https://mm-mb-test.oss-cn-beijing.aliyuncs.com
     */
    public static final String OUT_ENDPOINT = "https://oss-cn-beijing.aliyuncs.com";

    public static final String KEY_ID = "LTAI5tLaya38qPpXW3EVyMLq";
    public static final String KEY_SECRET = "QP1m3GyjtNpx2s29GfosaabuD9PAFz";
    public static final String BUCKET_NAME = "mm-mb-test";


    public static void delOssFile(String fileName) {
        if (fileName.isEmpty()){
            return;
        }
        OSS oss = new OSSClientBuilder().build(OUT_ENDPOINT, KEY_ID, KEY_SECRET);
        oss.deleteObject(BUCKET_NAME, fileName);
        oss.shutdown();
    }

    public static void delOssFile(String ossPreviewName,String ossDownloadName) {
        if (ossPreviewName.isEmpty()||ossDownloadName.isEmpty()){
            return;
        }
        OSS oss = new OSSClientBuilder().build(OUT_ENDPOINT, KEY_ID, KEY_SECRET);
        oss.deleteObject(BUCKET_NAME, ossPreviewName);
        oss.shutdown();
    }


    public static OSS getOssBeanInner(){
//        return new OSSClientBuilder().build(INNER_ENDPOINT, KEY_ID, KEY_SECRET);
        return new OSSClientBuilder().build(OUT_ENDPOINT, KEY_ID, KEY_SECRET);
    }

    public static OSS getOssBeanOut(){
        return new OSSClientBuilder().build(OUT_ENDPOINT, KEY_ID, KEY_SECRET);
    }

    public static class PutObjectProgressListener implements ProgressListener {
        private HttpSession session;
        private long bytesWritten = 0;
        private long totalBytes = -1;
        private boolean succeed = false;
        private int percent = 0;

        //构造方法中加入session
        public PutObjectProgressListener() {
        }

        public PutObjectProgressListener(HttpSession mSession) {
            this.session = mSession;
            session.setAttribute("upload_percent", percent);
        }

        @Override
        public void progressChanged(ProgressEvent progressEvent) {
            long bytes = progressEvent.getBytes();
            ProgressEventType eventType = progressEvent.getEventType();
            switch (eventType) {
                case TRANSFER_STARTED_EVENT:
                    LoggerUtil.info("Start to upload......");
                    break;
                case REQUEST_CONTENT_LENGTH_EVENT:
                    this.totalBytes = bytes;
                    LoggerUtil.info(this.totalBytes + " bytes in total will be uploaded to OSS");
                    break;
                case REQUEST_BYTE_TRANSFER_EVENT:
                    this.bytesWritten += bytes;
                    if (this.totalBytes != -1) {
                        percent = (int) (this.bytesWritten * 100.0 / this.totalBytes);
                        //将进度percent放入session中
                        session.setAttribute("upload_percent", percent);
                        LoggerUtil.info(bytes + " bytes have been written at this time, upload progress: " + percent + "%(" + this.bytesWritten + "/" + this.totalBytes + ")");
                    } else {
                        LoggerUtil.info(bytes + " bytes have been written at this time, upload ratio: unknown" + "(" + this.bytesWritten + "/...)");
                    }
                    break;
                case TRANSFER_COMPLETED_EVENT:
                    this.succeed = true;
                    LoggerUtil.info("Succeed to upload, " + this.bytesWritten + " bytes have been transferred in total");
                    break;
                case TRANSFER_FAILED_EVENT:
                    LoggerUtil.info("Failed to upload, " + this.bytesWritten + " bytes have been transferred");
                    break;
                default:
                    break;
            }
        }

        public boolean isSucceed() {
            return succeed;
        }
    }

}
