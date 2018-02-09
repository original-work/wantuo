package com.unionx.wantuo.domains;
public class Stage {
    private Integer id;
    private Integer indexs;
    private String name;
    private String content;
    private Integer categoryTypeId;
    private Integer pid;
    private Integer categoryId;
    private String startTime;
    private Integer isEnable;
    private String explains;
    private Integer isEnd;
    private Integer isEnableMain;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getIndexs() {
		return indexs;
	}
	public void setIndexs(Integer indexs) {
		this.indexs = indexs;
	}
	public String getExplains() {
		return explains;
	}
	public void setExplains(String explains) {
		this.explains = explains;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    public Integer getCategoryTypeId() {
        return categoryTypeId;
    }
    public void setCategoryTypeId(Integer categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }
    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
    public Integer getIsEnd() {
        return isEnd;
    }
    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }
    public Integer getIsEnableMain() {
        return isEnableMain;
    }
    public void setIsEnableMain(Integer isEnableMain) {
        this.isEnableMain = isEnableMain;
    }
}