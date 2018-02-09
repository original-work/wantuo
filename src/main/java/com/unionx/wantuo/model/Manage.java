package com.unionx.wantuo.model;

import java.io.Serializable;

public class Manage implements Serializable{
	
	private static final long serialVersionUID = 4374587259667537699L;

	private Integer id;//id
	
	private String login_accounts;//管理员登录帐号
	
	private String password;//密码
	
	private String manage_name;//管理员姓名
	
	private String create_date;//创建日期
	
	private String update_date;//修改日期
	
	private String last_login_date;//最后登录时间
	
	private Integer status;//状态
	
	private String status_name;//状态名称

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin_accounts() {
		return login_accounts;
	}

	public void setLogin_accounts(String login_accounts) {
		this.login_accounts = login_accounts;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getManage_name() {
		return manage_name;
	}

	public void setManage_name(String manage_name) {
		this.manage_name = manage_name;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	public String getLast_login_date() {
		return last_login_date;
	}

	public void setLast_login_date(String last_login_date) {
		this.last_login_date = last_login_date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	
    
}