package com.unionx.wantuo.model;


public class SystemLog {

    private Integer id;//id

    private String login_accounts;//帐号
    
    private String name;//名称

    private String create_date;//创建日期

    private String operation_module;//模块

    private String operation_function;//功能

    private Integer pageSize;
    
    private Integer pageIndex;
    
	private String signinDateBegin;// 签到开始时间
	
	private String signinDateEnd;// 签到结束时间
    
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getOperation_module() {
		return operation_module;
	}

	public void setOperation_module(String operation_module) {
		this.operation_module = operation_module;
	}

	public String getOperation_function() {
		return operation_function;
	}

	public void setOperation_function(String operation_function) {
		this.operation_function = operation_function;
	}

	public synchronized Integer getPageSize() {
		return pageSize;
	}

	public synchronized void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public synchronized Integer getPageIndex() {
		return pageIndex;
	}

	public synchronized void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getSigninDateBegin() {
		return signinDateBegin;
	}

	public void setSigninDateBegin(String signinDateBegin) {
		this.signinDateBegin = signinDateBegin;
	}

	public String getSigninDateEnd() {
		return signinDateEnd;
	}

	public void setSigninDateEnd(String signinDateEnd) {
		this.signinDateEnd = signinDateEnd != null&&signinDateEnd !="" ? signinDateEnd+" 23:59:59" :"";
	}
    
}