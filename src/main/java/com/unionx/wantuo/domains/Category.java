package com.unionx.wantuo.domains;
import java.util.Date;
public class Category {
    private Integer id;
    private Integer data;
    private Date updatetime;
    private Integer platform;
    private Integer chanel;
    private Integer intervals;
    private Date requestStamp;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getData() {
        return data;
    }
    public void setData(Integer data) {
        this.data = data;
    }
    public Date getUpdatetime() {
        return updatetime;
    }
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    public Integer getPlatform() {
        return platform;
    }
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
    public Integer getChanel() {
        return chanel;
    }
    public void setChanel(Integer chanel) {
        this.chanel = chanel;
    }
    public Integer getIntervals() {
        return intervals;
    }
    public void setIntervals(Integer intervals) {
        this.intervals = intervals;
    }
    public Date getRequestStamp() {
        return requestStamp;
    }
    public void setRequestStamp(Date requestStamp) {
        this.requestStamp = requestStamp;
    }

	@Override
	public String toString() {
		return "Category [id=" + id + ", data=" + data + ", updatetime=" + updatetime + ", platform=" + platform
				+ ", chanel=" + chanel + ", intervals=" + intervals + ", requestStamp=" + requestStamp + "]";
	}
    
   
}