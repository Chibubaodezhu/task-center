package com.huangjiang.taskcenter.model.param;

import java.util.List;

public class EmailParam {

    private String id;

    private String from;

    private String password;

    private String subject;

    private String content;

    private List<String> receivers;

    private List<String> cc;

    private Boolean htmlFlag;

    private List<String> attachments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public Boolean getHtmlFlag() {
        return htmlFlag;
    }

    public void setHtmlFlag(Boolean htmlFlag) {
        this.htmlFlag = htmlFlag;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }
}
