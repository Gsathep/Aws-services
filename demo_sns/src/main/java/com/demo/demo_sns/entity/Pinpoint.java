package com.demo.demo_sns.entity;

public class Pinpoint
{

    private String subject;
    private String apiKey;
    private String fromEmail;
    private String toEmail;

    public Pinpoint() {
    }

    public Pinpoint(String subject, String apiKey, String fromEmail, String toEmail) {
        this.subject = subject;
        this.apiKey = apiKey;
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }
}
