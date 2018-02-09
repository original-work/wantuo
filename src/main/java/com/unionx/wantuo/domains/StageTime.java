package com.unionx.wantuo.domains;
public class StageTime {
    private Integer id;
    private Integer categoryTypeId;
    private Integer categoryId;
    private Integer packageOrder;
    private Integer packageBaseId;
    private String time;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public Integer getPackageOrder() {
        return packageOrder;
    }
    public void setPackageOrder(Integer packageOrder) {
        this.packageOrder = packageOrder;
    }
    public Integer getPackageBaseId() {
        return packageBaseId;
    }
    public void setPackageBaseId(Integer packageBaseId) {
        this.packageBaseId = packageBaseId;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
}