package com.unionx.wantuo.model;

public class Trace {

    private Integer id;//id

    private String organizationAccounts;//机构帐号

    private String studentId;//学生编号

    private String createDate;//日期

    private String signIn;//签到文字

    private String signInImage;//签到图片

    private String leave;//离校文字

    private String leaveImage;//离校图片

    private Integer workStatus;//作业状态

    private String workStatusName;//作业状态

    private String behavior;//行为评价

    private String study;//学习评价

    private String grade;//成绩

    private String subject;//学科id

    private String subjectName;//学科name
    
    private String note;//总结文字

    private Integer status;//状态

    private String statusName;//状态0删除1签到2总结3离校
    
    private String signinDate;//签到时间

    private String signinPerson;//签到人

    private String leaveDate;//离校时间

    private String leavePerson;//离校人

    private String summaryPerson;//总结人

    private String summaryDate;//总结时间

    private String organizationName;//机构名称
    
    private String className;//班级名称
    
    private Integer classId;//班级id
    
    private String studentName;//学生名字
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrganizationAccounts() {
		return organizationAccounts;
	}

	public void setOrganizationAccounts(String organizationAccounts) {
		this.organizationAccounts = organizationAccounts;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSignIn() {
		return signIn;
	}

	public void setSignIn(String signIn) {
		this.signIn = signIn;
	}

	public String getSignInImage() {
		return signInImage;
	}

	public void setSignInImage(String signInImage) {
		this.signInImage = signInImage;
	}

	public String getLeave() {
		return leave;
	}

	public void setLeave(String leave) {
		this.leave = leave;
	}

	public String getLeaveImage() {
		return leaveImage;
	}

	public void setLeaveImage(String leaveImage) {
		this.leaveImage = leaveImage;
	}

	public Integer getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(Integer workStatus) {
		this.workStatus = workStatus;
	}

	public String getWorkStatusName() {
		return workStatusName;
	}

	public void setWorkStatusName(String workStatusName) {
		this.workStatusName = workStatusName;
	}

	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	public String getStudy() {
		return study;
	}

	public void setStudy(String study) {
		this.study = study;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public synchronized String getSigninDate() {
		return signinDate;
	}

	public synchronized void setSigninDate(String signinDate) {
		this.signinDate = signinDate;
	}

	public synchronized String getSigninPerson() {
		return signinPerson;
	}

	public synchronized void setSigninPerson(String signinPerson) {
		this.signinPerson = signinPerson;
	}

	public synchronized String getLeaveDate() {
		return leaveDate;
	}

	public synchronized void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public synchronized String getLeavePerson() {
		return leavePerson;
	}

	public synchronized void setLeavePerson(String leavePerson) {
		this.leavePerson = leavePerson;
	}

	public synchronized String getSummaryPerson() {
		return summaryPerson;
	}

	public synchronized void setSummaryPerson(String summaryPerson) {
		this.summaryPerson = summaryPerson;
	}

	public synchronized String getSummaryDate() {
		return summaryDate;
	}

	public synchronized void setSummaryDate(String summaryDate) {
		this.summaryDate = summaryDate;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
}   