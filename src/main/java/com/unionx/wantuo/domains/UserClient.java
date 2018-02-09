package com.unionx.wantuo.domains;
public class UserClient {
    private Integer id;
    private String username;
    private String clientid;
    private Integer flag;
    private String fisttime;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getClientid() {
        return clientid;
    }
    public void setClientid(String clientid) {
        this.clientid = clientid == null ? null : clientid.trim();
    }
    public Integer getFlag() {
        return flag;
    }
    public void setFlag(Integer flag) {
        this.flag = flag;
    }
	public String getFisttime() {
		return fisttime;
	}
	public void setFisttime(String fisttime) {
		this.fisttime = fisttime;
	}
}