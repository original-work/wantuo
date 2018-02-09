package com.unionx.wantuo.model;




public class School {
	private String distance;
	
    private Integer id;//id

    private Integer location;//所在地

    private String locationName;//所在地
    
    private Integer bairro;//所在区
    
    private String bairroName;//所在区

    private String address;//详细地址

    private String email;//邮箱

    private String schoolName;//学校名称

    private String schoolContacts;//学校联系人

    private String coordinateX;//x坐标
    
    private String coordinateY;//y坐标

    private String phone;//联系电话

    private String createDate;//创建日期

    private String updateDate;//修改日期

    private Integer status;//状态

    private String statusName;//状态名称
    
    private String fullName;
    
    private Integer property;//性质id
   
    private String propertyName;//性质名字

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSchoolContacts() {
		return schoolContacts;
	}

	public void setSchoolContacts(String schoolContacts) {
		this.schoolContacts = schoolContacts;
	}

	public String getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}

	public String getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Integer getBairro() {
		return bairro;
	}

	public void setBairro(Integer bairro) {
		this.bairro = bairro;
	}

	public String getBairroName() {
		return bairroName;
	}

	public void setBairroName(String bairroName) {
		this.bairroName = bairroName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String date) {
		this.createDate = date;
	}

	public synchronized String getDistance() {
		return distance;
	}

	public synchronized void setDistance(String distance) {
		this.distance = distance;
	}
    
	
	
}