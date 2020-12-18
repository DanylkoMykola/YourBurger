package com.danylko.yourburger.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

    private String resourceLocation;
    private String uploadFileLocation;
    private String defaultImg;

    public String getResourceLocation() {
        return resourceLocation;
    }

    public void setResourceLocation(String resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public String getUploadFileLocation() {
        return uploadFileLocation;
    }

    public void setUploadFileLocation(String uploadFileLocation) {
        this.uploadFileLocation = uploadFileLocation;
    }

    public String getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(String defaultImg) {
        this.defaultImg = defaultImg;
    }

}
