package com.unionx.wantuo.model;


public class PhoneStudent {
	
    private Integer id;//学生编码

    private String name;//姓名

    private String school;//学生名字
    
    private String grade;//班级
    
    private String loginAccounts;//家长帐号
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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