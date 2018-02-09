package com.unionx.wantuo.domains;
public class PackageDraw {
    private Integer id;
    private Integer code;
    private Integer moduleCode;
    private Integer categoryTypeId;
    private Integer categoryId;
    private Integer stageId;
    private Integer groups;
    private Integer indexs;
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
    public Integer getModuleCode() {
        return moduleCode;
    }
    public void setModuleCode(Integer moduleCode) {
        this.moduleCode = moduleCode;
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
    public Integer getStageId() {
        return stageId;
    }
    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }
    public Integer getGroups() {
        return groups;
    }
    public void setGroups(Integer groups) {
        this.groups = groups;
    }
    public Integer getIndexs() {
        return indexs;
    }
    public void setIndexs(Integer indexs) {
        this.indexs = indexs;
    }

	@Override
	public String toString() {
		return "PackageDraw [id=" + id + ", code=" + code + ", moduleCode=" + moduleCode + ", categoryTypeId="
				+ categoryTypeId + ", categoryId=" + categoryId + ", stageId=" + stageId + ", groups=" + groups
				+ ", indexs=" + indexs + "]";
	}
    
}