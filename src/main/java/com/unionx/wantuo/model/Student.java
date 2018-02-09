package com.unionx.wantuo.model;


public class Student {
	
    private Integer id;//编码

    private String name;//姓名
    
    private String school_id;//学校id

    private String school;//学校

    private Integer gradeId;//年级id
    
    private String grade;//年级

    private String loginAccounts;//家长帐号 报表时是机构

    private Integer patriarchId;//学生家长id
    
    private String patriarch;//学生家长
    
    private String phone;//其他家长电话

    private Integer sex;//性别

    private String createDate;//入托时间

    private Integer status;//状态

    private String statusName;//状态名称
    
    private String location;//所在地
    
    private String bairro;//所在区
    
    private Integer locationId;//所在地
    
    private Integer bairroId;//所在区
    
    private String kinsfolk;//亲属

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getBairroId() {
		return bairroId;
	}

	public void setBairroId(Integer bairroId) {
		this.bairroId = bairroId;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getPatriarchId() {
		return patriarchId;
	}

	public void setPatriarchId(Integer patriarchId) {
		this.patriarchId = patriarchId;
	}

	public String getPatriarch() {
		return patriarch;
	}

	public void setPatriarch(String patriarch) {
		this.patriarch = patriarch;
	}

	public String getKinsfolk() {
		return kinsfolk;
	}

	public void setKinsfolk(String kinsfolk) {
		this.kinsfolk = kinsfolk;
	}

	public String getSchool_id() {
		return school_id;
	}

	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	
}