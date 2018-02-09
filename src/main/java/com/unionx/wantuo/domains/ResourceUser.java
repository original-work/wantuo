package com.unionx.wantuo.domains;
public class ResourceUser {
    private Long id;
    private String resourceUsername;
    private String resourcePassword;
    private String salt;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getResourceUsername() {
        return resourceUsername;
    }
    public void setResourceUsername(String resourceUsername) {
        this.resourceUsername = resourceUsername == null ? null : resourceUsername.trim();
    }
    public String getResourcePassword() {
        return resourcePassword;
    }
    public void setResourcePassword(String resourcePassword) {
        this.resourcePassword = resourcePassword == null ? null : resourcePassword.trim();
    }
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }
}