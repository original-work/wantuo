package com.unionx.wantuo.model;


public class Push {

    private Integer id;//id

    private String pushObjectId;//推送对象Id
    
    private String pushObject;//推送对象

    private String pushDetails;//推送内容

    private String createDate;//推送时间

    private String pushName;//推送人

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPushObject() {
		return pushObject;
	}

	public void setPushObject(String pushObject) {
		this.pushObject = pushObject;
	}

	public String getPushDetails() {
		return pushDetails;
	}

	public void setPushDetails(String pushDetails) {
		this.pushDetails = pushDetails;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getPushName() {
		return pushName;
	}

	public void setPushName(String pushName) {
		this.pushName = pushName;
	}

	public String getPushObjectId() {
		return pushObjectId;
	}

	public void setPushObjectId(String pushObjectId) {
		this.pushObjectId = pushObjectId;
	}
	
}