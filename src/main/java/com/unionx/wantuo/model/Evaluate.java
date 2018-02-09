package com.unionx.wantuo.model;


public class Evaluate {

    private Integer id;

    private String organizationAccounts;

    private Integer evaluate;

    private String evaluatePerson;

    private String evaluateDetails;

    private String createDate;

    private Integer status;

    private String statusName;

    private String headPortrait;//头像
    
    private int pageIndex=1;
    
    private int pageSize=10;
    
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
        this.organizationAccounts = organizationAccounts == null ? null : organizationAccounts.trim();
    }

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }

    public String getEvaluatePerson() {
        return evaluatePerson;
    }

    public void setEvaluatePerson(String evaluatePerson) {
        this.evaluatePerson = evaluatePerson == null ? null : evaluatePerson.trim();
    }

    public String getEvaluateDetails() {
        return evaluateDetails;
    }

    public void setEvaluateDetails(String evaluateDetails) {
        this.evaluateDetails = evaluateDetails == null ? null : evaluateDetails.trim();
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
        this.statusName = statusName == null ? null : statusName.trim();
    }

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public  void setPageIndex(int pageIndex) {
		this.pageIndex =(pageIndex*this.pageSize)-this.pageSize;
	}

	public  int getPageSize() {
		return pageSize;
	}

	public  void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    
}