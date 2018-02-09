package com.unionx.wantuo.model;


public class StudentSchool {

    private Integer id;

    private Integer studentId;//学生id

    private String organizationAccounts;//机构帐号

    private String beginDate;//开始日期

    private String endDate;//结束日期

    private String name;//学生姓名
    
    private String school;//学校
    
    private String grade;//班级
    
    private String loginAccounts;//家长帐号
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getOrganizationAccounts() {
		return organizationAccounts;
	}

	public void setOrganizationAccounts(String organizationAccounts) {
		this.organizationAccounts = organizationAccounts;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getLoginAccounts() {
		return loginAccounts;
	}

	public void setLoginAccounts(String loginAccounts) {
		this.loginAccounts = loginAccounts;
	}
	
}