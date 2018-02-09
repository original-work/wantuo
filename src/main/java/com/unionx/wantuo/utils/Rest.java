package com.unionx.wantuo.utils;

import java.util.HashMap;
import java.util.Map;

public class Rest {
	private String status;
	private String message;
	private Map data=new HashMap<Object, Object>();
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<Object, Object> getData() {
		return data;
	}
	public void setData(Map<Object, Object> data) {
		this.data = data;
	}
}	
