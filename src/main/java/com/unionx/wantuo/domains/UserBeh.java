package com.unionx.wantuo.domains;
import java.util.Date;
public class UserBeh {
    private Integer id;
    private Integer videoid;
    private Integer userid;
    private String time;
    private Integer count;
    private Date requestStamp;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getVideoid() {
        return videoid;
    }
    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }
    public Integer getUserid() {
        return userid;
    }
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public Date getRequestStamp() {
        return requestStamp;
    }
    public void setRequestStamp(Date requestStamp) {
        this.requestStamp = requestStamp;
    }
}