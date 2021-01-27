package com.danylko.yourburger.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "email")
public class EmailProperties {

    private String from;
    private String facilityEmail;
    private String subject;

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
}
