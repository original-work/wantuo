package com.unionx.wantuo.domains;
import java.util.Date;
public class Stages {
	  private Integer id;
	    private Integer stageIndex;
	    private String name;
	    private String content;
	    private Integer categoryId;
	    private Date startTime;
	    public Integer getId() {
	        return id;
	    }
	    public void setId(Integer id) {
	        this.id = id;
	    }
	    public Integer getStageIndex() {
	        return stageIndex;
	    }
	    public void setStageIndex(Integer stageIndex) {
	        this.stageIndex = stageIndex;
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
	    public Integer getCategoryId() {
	        return categoryId;
	    }
	    public void setCategoryId(Integer categoryId) {
	        this.categoryId = categoryId;
	    }
	    public Date getStartTime() {
	        return startTime;
	    }
	    public void setStartTime(Date startTime) {
	        this.startTime = startTime;
	    }
		@Override
		public String toString() {
			return "Stages [id=" + id + ", stageIndex=" + stageIndex + ", name=" + name + ", content=" + content
					+ ", categoryId=" + categoryId + ", startTime=" + startTime + "]";
		}
}
