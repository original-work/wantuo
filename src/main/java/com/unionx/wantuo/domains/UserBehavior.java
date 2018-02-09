package com.unionx.wantuo.domains;
public class UserBehavior {
    private String username;
    private String videoname;
    private String videotime;
    private String downcount;
    private String course;
    private String areacount;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
    public String getVideoname() {
        return videoname;
    }
    public void setVideoname(String videoname) {
        this.videoname = videoname == null ? null : videoname.trim();
    }
    public String getVideotime() {
        return videotime;
    }
    public void setVideotime(String videotime) {
        this.videotime = videotime == null ? null : videotime.trim();
    }
    public String getDowncount() {
        return downcount;
    }
    public void setDowncount(String downcount) {
        this.downcount = downcount == null ? null : downcount.trim();
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course == null ? null : course.trim();
    }
    public String getAreacount() {
        return areacount;
    }
    public void setAreacount(String areacount) {
        this.areacount = areacount == null ? null : areacount.trim();
    }

	@Override
	public String toString() {
		return "UserBehavior [username=" + username + ", videoname=" + videoname + ", videotime=" + videotime
				+ ", downcount=" + downcount + ", course=" + course + ", areacount=" + areacount + "]";
	}
    
    
}