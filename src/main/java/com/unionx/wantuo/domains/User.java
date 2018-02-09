package com.unionx.wantuo.domains;
import java.io.Serializable;
public class User implements Serializable {
	private static final long serialVersionUID = 7310420250450393139L;
	private Long id; // ���
	private String username; // �û���
	private String password; // ����
	private String salt; // �����������
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getCredentialsSalt() {
		return username + salt;
	}
}