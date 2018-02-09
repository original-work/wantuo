package com.unionx.wantuo.model;

public class Organization {
	
	private String distance;
	
    private Integer id;//id

    private Integer location;//所在地

    private String locationName;//所在地
    
    private Integer bairro;//所在区
    
    private String bairroName;//所在区

    private String address;//详细地址

    private String loginAccounts;//登录帐号

    private String email;//邮箱

    private Integer organizatioType;//机构类型

    private String organizatioTypeName;//机构类型名称

    private String organizationAbbreviation;//机构简称

    private String organization;//机构全称

    private String organizationContacts;//机构联系人

    private String idCardImage;//身份证图片

    private String businessLicenseImage;//营业执照图片

    private String coordinateX;//x坐标
    
    private String coordinateY;//y坐标

    private String phone;//联系电话

    private String idCard;//身份证号

    private String businessLicense;//营业执照

    private String createDate;//创建日期

    private String updateDate;//修改日期

    private Integer check;//审核状态

    private String checkName;//审核状态名称

    private Integer attestation;//认证状态

    private String attestationName;//认证状态名称

    private Integer warranty;//授权状态

    private String warrantyName;//授权状态名称

    private String introduce;//介绍

    private String label;//标签

    private String photoAlbum;//效果图（相册）

    private Double cost;//价格

    private Integer orderNum;//预约单数

    private Double evaluate;//评价星数

    private Integer evaluateNum;//评价个数

    private String subject;//学科
    
    private String teacher;//班主任

    private String headPortrait;//头像
    
    private String type;// 手机1 附近查询 2 星级查询
    
    private Integer sort;//排行
    private String pathName;
    
    private String password;
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

	public String getLoginAccounts() {
		return loginAccounts;
	}

	public void setLoginAccounts(String loginAccounts) {
		this.loginAccounts = loginAccounts;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getOrganizatioType() {
		return organizatioType;
	}

	public void setOrganizatioType(Integer organizatioType) {
		this.organizatioType = organizatioType;
	}

	public String getOrganizatioTypeName() {
		return organizatioTypeName;
	}

	public void setOrganizatioTypeName(String organizatioTypeName) {
		this.organizatioTypeName = organizatioTypeName;
	}

	public String getOrganizationAbbreviation() {
		return organizationAbbreviation;
	}

	public void setOrganizationAbbreviation(String organizationAbbreviation) {
		this.organizationAbbreviation = organizationAbbreviation;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOrganizationContacts() {
		return organizationContacts;
	}

	public void setOrganizationContacts(String organizationContacts) {
		this.organizationContacts = organizationContacts;
	}

	public String getIdCardImage() {
		return idCardImage;
	}

	public void setIdCardImage(String idCardImage) {
		this.idCardImage = idCardImage;
	}

	public String getBusinessLicenseImage() {
		return businessLicenseImage;
	}

	public void setBusinessLicenseImage(String businessLicenseImage) {
		this.businessLicenseImage = businessLicenseImage;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public Integer getCheck() {
		return check;
	}

	public void setCheck(Integer check) {
		this.check = check;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public Integer getAttestation() {
		return attestation;
	}

	public void setAttestation(Integer attestation) {
		this.attestation = attestation;
	}

	public String getAttestationName() {
		return attestationName;
	}

	public void setAttestationName(String attestationName) {
		this.attestationName = attestationName;
	}

	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}

	public String getWarrantyName() {
		return warrantyName;
	}

	public void setWarrantyName(String warrantyName) {
		this.warrantyName = warrantyName;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPhotoAlbum() {
		return photoAlbum;
	}

	public void setPhotoAlbum(String photoAlbum) {
		this.photoAlbum = photoAlbum;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Double getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Double evaluate) {
		this.evaluate = evaluate;
	}

	public Integer getEvaluateNum() {
		return evaluateNum;
	}

	public void setEvaluateNum(Integer evaluateNum) {
		this.evaluateNum = evaluateNum;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
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

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public synchronized String getPathName() {
		return pathName;
	}

	public synchronized void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public synchronized Integer getSort() {
		return sort;
	}

	public synchronized void setSort(Integer sort) {
		this.sort = sort;
	}
	
}