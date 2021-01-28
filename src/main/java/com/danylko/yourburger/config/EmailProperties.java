package com.danylko.yourburger.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "email")
public class EmailProperties {

    private String from;
    private String facilityEmail;
    private String subject;
    private String htmlTemplateOrderResult;
    private String htmlTemplateCustomer;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFacilityEmail() {
        return facilityEmail;
    }

    public void setFacilityEmail(String facilityEmail) {
        this.facilityEmail = facilityEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtmlTemplateOrderResult() {
        return htmlTemplateOrderResult;
    }

    public void setHtmlTemplateOrderResult(String htmlTemplateOrderResult) {
        this.htmlTemplateOrderResult = htmlTemplateOrderResult;
    }

    public String getHtmlTemplateCustomer() {
        return htmlTemplateCustomer;
    }

    public void setHtmlTemplateCustomer(String htmlTemplateCustomer) {
        this.htmlTemplateCustomer = htmlTemplateCustomer;
    }
}
