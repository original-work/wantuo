package com.unionx.wantuo.domains;
public class categoryD {
    private Integer id;
    private Integer type;
    private Integer categoryId;
    private Integer categoryTypeId;
    private Integer jumptype;
    private String name;
    private String group;
    private String path;
    private Integer moduleCount;
    private Integer isFinal;
    private String detailContent;
    private String year;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public Integer getCategoryTypeId() {
        return categoryTypeId;
    }
    public void setCategoryTypeId(Integer categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }
    public Integer getJumptype() {
        return jumptype;
    }
    public void setJumptype(Integer jumptype) {
        this.jumptype = jumptype;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group == null ? null : group.trim();
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
    public Integer getModuleCount() {
        return moduleCount;
    }
    public void setModuleCount(Integer moduleCount) {
        this.moduleCount = moduleCount;
    }
    public Integer getIsFinal() {
        return isFinal;
    }
    public void setIsFinal(Integer isFinal) {
        this.isFinal = isFinal;
    }
    public String getDetailContent() {
        return detailContent;
    }
    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent == null ? null : detailContent.trim();
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }
}