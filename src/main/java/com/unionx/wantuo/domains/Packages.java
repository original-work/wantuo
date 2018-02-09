package com.unionx.wantuo.domains;
public class Packages {
    private Integer id;
    private Integer code;
    private String name;
    private Integer orders;
    private Integer groups;
    private Integer categoryTypeId;
    private Integer categoryId;
    private String time;
    private Integer packageInfoIndex;
    private Integer moduleCode;
    private Integer stageId;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public Integer getorders() {
        return orders;
    }
    public void setorders(Integer orders) {
        this.orders = orders;
    }
    public Integer getGroups() {
        return groups;
    }
    public void setGroups(Integer groups) {
        this.groups = groups;
    }
    public Integer getCategoryTypeId() {
        return categoryTypeId;
    }
    public void setCategoryTypeId(Integer categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
    public Integer getPackageInfoIndex() {
        return packageInfoIndex;
    }
    public void setPackageInfoIndex(Integer packageInfoIndex) {
        this.packageInfoIndex = packageInfoIndex;
    }
    public Integer getModuleCode() {
        return moduleCode;
    }
    public void setModuleCode(Integer moduleCode) {
        this.moduleCode = moduleCode;
    }
    public Integer getStageId() {
        return stageId;
    }
    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }
}