package com.unionx.wantuo.domains;
public class NewUser {
    private Integer userId;
    private String name;
    private String password;
    private String mbphone;
    private String clientid;
    private String tongbu;
    private String beiyong1;
    private String beiyong2;
    private String beiyong3;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    public String getMbphone() {
        return mbphone;
    }
    public void setMbphone(String mbphone) {
        this.mbphone = mbphone == null ? null : mbphone.trim();
    }
    public String getClientid() {
        return clientid;
    }
    public void setClientid(String clientid) {
        this.clientid = clientid == null ? null : clientid.trim();
    }
    public String getTongbu() {
        return tongbu;
    }
    public void setTongbu(String tongbu) {
        this.tongbu = tongbu == null ? null : tongbu.trim();
    }
    public String getBeiyong1() {
        return beiyong1;
    }
    public void setBeiyong1(String beiyong1) {
        this.beiyong1 = beiyong1 == null ? null : beiyong1.trim();
    }
    public String getBeiyong2() {
        return beiyong2;
    }
    public void setBeiyong2(String beiyong2) {
        this.beiyong2 = beiyong2 == null ? null : beiyong2.trim();
    }
    public String getBeiyong3() {
        return beiyong3;
    }
    public void setBeiyong3(String beiyong3) {
        this.beiyong3 = beiyong3 == null ? null : beiyong3.trim();
    }
}