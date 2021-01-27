package com.danylko.yourburger.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "storage")
public class StorageProperties {

    private String resourceLocation;
    private String uploadFileLocation;
    private String defaultImg;
    private String pathToAttachment;
    private String htmlTemplate;

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

    public String getPathToAttachment() {
        return pathToAttachment;
    }

    public void setPathToAttachment(String pathToAttachment) {
        this.pathToAttachment = pathToAttachment;
    }

    public String getHtmlTemplate() {
        return htmlTemplate;
    }

    public void setHtmlTemplate(String htmlTemplate) {
        this.htmlTemplate = htmlTemplate;
    }
}
