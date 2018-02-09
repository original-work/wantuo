package com.unionx.wantuo.domains;
import org.springframework.stereotype.Component;
@Component
public class CategoryDBRe {
	public Integer id;
	public String name;
	public Integer categoryTypeId;
	public CategoryDBRe(){}
	public CategoryDBRe(String name,Integer categoryTypeId){
		this.name=name;this.categoryTypeId=categoryTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCategoryTypeId() {
		return categoryTypeId;
	}
	public void setCategoryTypeId(Integer integer) {
		this.categoryTypeId = integer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
