package com.unionx.wantuo.domains;
public class ClientDevicesDTO {
	private String clientdeviceid;
	private  String clientSecret;
	public String getClientdeviceid() {
		return clientdeviceid;
	}
	public void setClientdeviceid(String clientdeviceid) {
		this.clientdeviceid = clientdeviceid;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public ClientDevicesDTO() {
		super();
	}
}
