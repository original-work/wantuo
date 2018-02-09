package com.unionx.wantuo.model;


public class OnePush {
	
    private Integer id;//id
    
    private String push_login_accounts;//推送人帐号
    
    private String login_accounts;//接收人帐号
    
    private String create_date;//推送时间
    
    private Integer status;//状态
    
    private String status_name;//状态名字
    
    private String push_details;//推送内容
    
    private String push_name;//推送人
    
    private Integer push_type;//推送类型
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPush_login_accounts() {
		return push_login_accounts;
	}

	public void setPush_login_accounts(String push_login_accounts) {
		this.push_login_accounts = push_login_accounts;
	}

	public String getLogin_accounts() {
		return login_accounts;
	}

	public void setLogin_accounts(String login_accounts) {
		this.login_accounts = login_accounts;
	}

	public String getPush_details() {
		return push_details;
	}

	public void setPush_details(String push_details) {
		this.push_details = push_details;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getPush_name() {
		return push_name;
	}

	public void setPush_name(String push_name) {
		this.push_name = push_name;
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

	public Integer getPush_type() {
		return push_type;
	}

	public void setPush_type(Integer push_type) {
		this.push_type = push_type;
	}
	
}