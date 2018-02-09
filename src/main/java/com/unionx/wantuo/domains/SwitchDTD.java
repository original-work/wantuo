package com.unionx.wantuo.domains;
/**
 * ����
 * @author liuzhengyu
 *
 */
public class SwitchDTD {
	/**
	 * ID
	 */
	private Integer id;
	/**
	 * ����
	 */
	private String title;
	/**
	 * ����
	 */
	private String des;
	/**
	 * ����ʱ��
	 */
	private String updatetime;
	/**
	 * JSON���
	 */
	private String data;
	/**
	 * ƽ̨
	 */
	private String platform;
	/**
	 * ����
	 */
	private String chanel;
	/**
	 * ���¼��ʱ��
	 */
	private Integer intervals;
	/**
	 * ״̬���Ƿ�����
	 */
	private Integer stats;
	private Integer type;
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
		this.title = title;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
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
		this.data = data;
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
