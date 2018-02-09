package com.unionx.wantuo.model;


public class Login {
	
    private Integer id;

    private String loginAccounts;

    private String password;

    private Integer accountsType;

    private String createDate;

    private String updateDate;

    private Integer status;

    private String statusName;

    private String cId;

    private String deviceToken;
    
    private String phoneUuid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginAccounts() {
		return loginAccounts;
	}

	public void setLoginAccounts(String loginAccounts) {
		this.loginAccounts = loginAccounts;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAccountsType() {
		return accountsType;
	}

	public void setAccountsType(Integer accountsType) {
		this.accountsType = accountsType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getPhoneUuid() {
		return phoneUuid;
	}

	public void setPhoneUuid(String phoneUuid) {
		this.phoneUuid = phoneUuid;
	}
	
}