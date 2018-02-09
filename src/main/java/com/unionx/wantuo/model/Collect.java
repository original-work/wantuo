package com.unionx.wantuo.model;

public class Collect {
    private Integer id;

    private String patriarchAccounts;

    private String organizationAccounts;

    private String createDate;

    private Integer status;

    private String statusName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatriarchAccounts() {
        return patriarchAccounts;
    }

    public void setPatriarchAccounts(String patriarchAccounts) {
        this.patriarchAccounts = patriarchAccounts == null ? null : patriarchAccounts.trim();
    }

    public String getOrganizationAccounts() {
        return organizationAccounts;
    }

    public void setOrganizationAccounts(String organizationAccounts) {
        this.organizationAccounts = organizationAccounts == null ? null : organizationAccounts.trim();
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
}