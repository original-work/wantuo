package com.unionx.wantuo.domains;
import java.util.Date;
public class Module {
    private Integer id;
    private String name;
    private Integer moduleCode;
    private Integer categoryTypeId;
    private Integer categoryId;
    private Integer groups;
    private Integer stageId;
    private Integer moduleOrder;
    private Date requestStamp;
    private String version;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
    public Integer getGroups() {
        return groups;
    }
    public void setGroups(Integer groups) {
        this.groups = groups;
    }
    public Integer getStageId() {
        return stageId;
    }
    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }
    public Integer getModuleOrder() {
        return moduleOrder;
    }
    public void setModuleOrder(Integer moduleOrder) {
        this.moduleOrder = moduleOrder;
    }
    public Date getRequestStamp() {
        return requestStamp;
    }
    public void setRequestStamp(Date requestStamp) {
        this.requestStamp = requestStamp;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

	@Override
	public String toString() {
		return "Module [id=" + id + ", name=" + name + ", moduleCode=" + moduleCode + ", categoryTypeId="
				+ categoryTypeId + ", categoryId=" + categoryId + ", groups=" + groups + ", stageId=" + stageId
				+ ", moduleOrder=" + moduleOrder + ", requestStamp=" + requestStamp + ", version=" + version + "]";
	}
    
}