package com.unionx.wantuo.domains;
import org.springframework.stereotype.Component;
@Component
public class KeyValue {
	private  String id;
    private  String name;
    private  String code;
    private String enumid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEnumid() {
		return enumid;
	}
	public void setEnumid(String enumid) {
		this.enumid = enumid;
	}
	@Override
	public String toString() {
		return "id:" + id + ", name:" + name + ", code:" + code + ", enumid:" + enumid;
	}
}
