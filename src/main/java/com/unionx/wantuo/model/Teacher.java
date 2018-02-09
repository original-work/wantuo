package com.unionx.wantuo.model;


public class Teacher {

    private Integer id;

    private String loginAccounts;//老师帐号

    private String organizationAccounts;//机构帐号

    private Integer status;

    private String statusName;

    private String createDate;
    
    private String phone;//老师电话
    
    private String name;//老师姓名

    private String password;//密码
    
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

	public String getOrganizationAccounts() {
		return organizationAccounts;
	}

	public void setOrganizationAccounts(String organizationAccounts) {
		this.organizationAccounts = organizationAccounts;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}