package com.unionx.wantuo.model;


public class Activity {
    private Integer id;

    private String location;//位置

    private String activityName;//活动名称

    private String createDate;//创建时间

    private String thumbnailPath;//缩略图地址

    private String largePath;//详细图地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath == null ? null : thumbnailPath.trim();
    }

    public String getLargePath() {
        return largePath;
    }

    public void setLargePath(String largePath) {
        this.largePath = largePath == null ? null : largePath.trim();
    }

	public synchronized String getCreateDate() {
		return createDate;
	}

	public synchronized void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}