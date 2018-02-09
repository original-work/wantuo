package com.unionx.wantuo.domains;
public class Images {
    private Integer id;
    private Integer categoryTypeId;
    private Integer categoryId;
    private Integer types;
    private Integer classes;
    private String imagepath;
    private String special_type_id;
    private String beiyong2;
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
    public Integer getTypes() {
        return types;
    }
    public void setTypes(Integer types) {
        this.types = types;
    }
    public Integer getClasses() {
        return classes;
    }
    public void setClasses(Integer classes) {
        this.classes = classes;
    }
    public String getImagepath() {
        return imagepath;
    }
    public void setImagepath(String imagepath) {
        this.imagepath = imagepath == null ? null : imagepath.trim();
    }
    public String getSpecial_type_id() {
		return special_type_id;
	}
	public void setSpecial_type_id(String special_type_id) {
		this.special_type_id = special_type_id;
	}
	public String getBeiyong2() {
        return beiyong2;
    }
    public void setBeiyong2(String beiyong2) {
        this.beiyong2 = beiyong2 == null ? null : beiyong2.trim();
    }
}