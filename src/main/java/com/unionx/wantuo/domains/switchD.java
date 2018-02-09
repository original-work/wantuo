package com.unionx.wantuo.domains;
public class switchD {
    private Integer id;
    private String title="空";
    private String des="空";
    private String updatetime="空";
    private String data="空";
    private String platform;
    private String chanel;
    private Integer intervals=0;
    private Integer stats=0;
    private Integer type=0;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
    public String getUpdatetime() {
        return updatetime;
    }
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }
    public String getPlatform() {
        return platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public String getChanel() {
        return chanel;
    }
    public void setChanel(String chanel) {
        this.chanel = chanel;
    }
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Integer getIntervals() {
		return intervals;
	}
	public void setIntervals(Integer intervals) {
		this.intervals = intervals;
	}
	public Integer getStats() {
		return stats;
	}
	public void setStats(Integer stats) {
		this.stats = stats;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}