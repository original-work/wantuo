package com.unionx.wantuo.domains;
public class PackageRelation {
    private Integer id;
    private Integer moduleId;
    private Integer categoryId;
    private Integer packageInfoId;
    private Integer packageRelation＿group;
    private Integer stageId;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getModuleId() {
        return moduleId;
    }
    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public Integer getPackageInfoId() {
        return packageInfoId;
    }
    public void setPackageInfoId(Integer packageInfoId) {
        this.packageInfoId = packageInfoId;
    }
    public Integer getPackageRelation＿group() {
        return packageRelation＿group;
    }
    public void setPackageRelation＿group(Integer packageRelation＿group) {
        this.packageRelation＿group = packageRelation＿group;
    }
    public Integer getStageId() {
        return stageId;
    }
	public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }
    @Override
	public String toString() {
		return "PackageRelation [moduleId=" + moduleId + ", categoryId=" + categoryId
				+ ", packageInfoId=" + packageInfoId + ", packageRelation＿group=" + packageRelation＿group + ", stageId="
				+ stageId + "]";
	}
}