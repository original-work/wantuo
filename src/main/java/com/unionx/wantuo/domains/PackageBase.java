package com.unionx.wantuo.domains;
public class PackageBase {
    private Integer id;
    private String name;
    private String code;
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
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

	@Override
	public String toString() {
		return "PackageBase [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
    
    
}