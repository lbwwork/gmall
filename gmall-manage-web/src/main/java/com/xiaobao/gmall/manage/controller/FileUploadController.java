package com.xiaobao.gmall.manage.controller;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传控制器
 * @author lbw
 */
@RestController
@CrossOrigin
public class FileUploadController {
    /**
     * @Value 注解使用的前提是当前类必须在Spring容器中
     */
    @Value("${fileServer.url}")
    private String fileUrl;
    private static TrackerClient trackerClient;

    static {
        try {
             ClientGlobal.init("tracker.conf");
            trackerClient = new TrackerClient();
        } catch (IOException | MyException e) {
            System.err.println("获取配置文件出错" + e);
        }
    }
    /**
     * 文件上传
     * @param file 上传的文件，springMvc
     * @return  文件在文件服务器的路径
     */
    @RequestMapping("fileUpload")
    public String fileUpload(MultipartFile file){
        StringBuilder imgUrl = new StringBuilder(fileUrl);
        if (file != null){
            //获取连接
            TrackerServer trackerServer = null;
            try {
                trackerServer = trackerClient.getTrackerServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
            StorageClient storageClient = new StorageClient(trackerServer,null);
            String orginalFilename=file.getOriginalFilename();
            String extName = StringUtils.substringAfterLast(orginalFilename, ".");
            String[] uploadFile = new String[2];
            try {
                uploadFile = storageClient.upload_appender_file(file.getBytes(), extName, null);
                if (uploadFile == null || uploadFile.length != 2){
                    System.err.println("向FastDFS上传文件失败");
                }else {
                    System.out.println("向FastDFS上传文件成功");
                    imgUrl.append("/");
                    imgUrl.append(uploadFile[0]);
                    imgUrl.append("/");
                    imgUrl.append(uploadFile[1]);
                }
            } catch (IOException | MyException e) {
                e.printStackTrace();
            }
        }
        return imgUrl.toString();
    }

}
