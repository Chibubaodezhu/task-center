package com.huangjiang.taskcenter.model.param;

public class UserParam {

    private String id;

    private String password;

    private String verificationCode;

    private String apartmentId;

    private String status;

    private String mobile;

    private String email;

    private String cn;

    private String manager;

    private String info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "UserParam{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", apartmentId='" + apartmentId + '\'' +
                ", status='" + status + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", cn='" + cn + '\'' +
                ", manager='" + manager + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
