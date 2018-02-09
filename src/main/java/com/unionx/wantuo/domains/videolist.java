package com.unionx.wantuo.domains;
public class videolist {
    private Integer id;
    private Integer categoryId;
    private Integer categoryTypeId;
    private String name;
    private String path;
    private Integer jumptype;
    private String title;
    private String year;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
    public Integer getJumptype() {
        return jumptype;
    }
    public void setJumptype(Integer jumptype) {
        this.jumptype = jumptype;
    }
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }
}