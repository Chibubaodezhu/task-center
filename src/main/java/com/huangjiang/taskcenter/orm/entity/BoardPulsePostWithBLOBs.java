package com.huangjiang.taskcenter.orm.entity;

public class BoardPulsePostWithBLOBs extends BoardPulsePost {
    private String content;

    private String users;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users == null ? null : users.trim();
    }
}