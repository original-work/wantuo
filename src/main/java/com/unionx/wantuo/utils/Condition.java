package com.unionx.wantuo.utils;

public class Condition {

	private Integer page = 1;

	private Integer rows = 10;

	private String sort;

	private String order;

	private String create_date_begin;// 创建日期

	private String create_date_end;// 创建日期

	private String update_date_begin;// 修改日期

	private String update_date_end;// 修改日期

	private String start_time_begin;// 启用日期

	private String start_time_end;// 启用日期

	private String end_time_begin;// 停用日期

	private String end_time_end;// 停用日期

	private Integer counts_begin;// 数量

	private Integer counts_end;// 数量

	private String statusMix;// 状态

	private String sumbit_date_begin;// 提交日期

	private String sumbit_date_end;// 提交日期

	private String check_date_begin;// 审核日期

	private String check_date_end;// 审核日期

	private String limit;//
	
    private String x;//x坐标111.133km
    
    private String y;//y坐标95.218km
    
    private Integer distance;//距离
    
	private Integer currentPage = 1; // 当前页
	
	private Integer rowCount = 0; // 总行数
	
	private Integer pageSize = 10; // 页大小
	
	private Integer pageCount = 0; // 总页数
	
	private Integer start = 0;// 当前页起始记录
	
	private Integer pageTail = 0;// 当前页到达的记录
	
	private String signinDateBegin;// 签到开始时间
	
	private String signinDateEnd;// 签到结束时间
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCreate_date_begin() {
		return create_date_begin;
	}

	public void setCreate_date_begin(String create_date_begin) {
		this.create_date_begin = create_date_begin;
	}

	public String getCreate_date_end() {
		return create_date_end;
	}

	public void setCreate_date_end(String create_date_end) {
		this.create_date_end = create_date_end;
	}

	public String getUpdate_date_begin() {
		return update_date_begin;
	}

	public void setUpdate_date_begin(String update_date_begin) {
		this.update_date_begin = update_date_begin;
	}

	public String getUpdate_date_end() {
		return update_date_end;
	}

	public void setUpdate_date_end(String update_date_end) {
		this.update_date_end = update_date_end;
	}

	public String getStart_time_begin() {
		return start_time_begin;
	}

	public void setStart_time_begin(String start_time_begin) {
		this.start_time_begin = start_time_begin;
	}

	public String getStart_time_end() {
		return start_time_end;
	}

	public void setStart_time_end(String start_time_end) {
		this.start_time_end = start_time_end;
	}

	public String getEnd_time_begin() {
		return end_time_begin;
	}

	public void setEnd_time_begin(String end_time_begin) {
		this.end_time_begin = end_time_begin;
	}

	public String getEnd_time_end() {
		return end_time_end;
	}

	public void setEnd_time_end(String end_time_end) {
		this.end_time_end = end_time_end;
	}

	public Integer getCounts_begin() {
		return counts_begin;
	}

	public void setCounts_begin(Integer counts_begin) {
		this.counts_begin = counts_begin;
	}

	public Integer getCounts_end() {
		return counts_end;
	}

	public void setCounts_end(Integer counts_end) {
		this.counts_end = counts_end;
	}

	public String getStatusMix() {
		return statusMix;
	}

	public void setStatusMix(String statusMix) {
		this.statusMix = statusMix;
	}

	public String getSumbit_date_begin() {
		return sumbit_date_begin;
	}

	public void setSumbit_date_begin(String sumbit_date_begin) {
		this.sumbit_date_begin = sumbit_date_begin;
	}

	public String getSumbit_date_end() {
		return sumbit_date_end;
	}

	public void setSumbit_date_end(String sumbit_date_end) {
		this.sumbit_date_end = sumbit_date_end;
	}

	public String getCheck_date_begin() {
		return check_date_begin;
	}

	public void setCheck_date_begin(String check_date_begin) {
		this.check_date_begin = check_date_begin;
	}

	public String getCheck_date_end() {
		return check_date_end;
	}

	public void setCheck_date_end(String check_date_end) {
		this.check_date_end = check_date_end;
	}
	
	


	protected void doPage(Integer rowCount) {
		this.pageCount = rowCount / this.rows + 1;
		if ((rowCount % this.rows == 0) && pageCount > 1)
			this.pageCount--;
		this.start = ((this.page - 1) * this.rows)>0?((this.page - 1) * this.rows):0;
		this.pageTail = this.start + this.rows;
		if ((this.start + this.rows) > this.rowCount)
			this.pageTail = rowCount;
		this.setLimit();
	}

	public String getMysqlQueryCondition() {
		String condition = "";
		condition = " limit " + start + "," + rows;
		return condition;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public int getStart() {
		return start;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageTail(int pageTail) {
		this.pageTail = pageTail;
	}

	public int getPageTail() {
		return pageTail;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
		this.doPage(rowCount);
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public void setPageTail(Integer pageTail) {
		this.pageTail = pageTail;
	}

	public void setLimit() {
		this.limit = " limit " + start + "," + rows;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getSigninDateBegin() {
		return signinDateBegin;
	}

	public void setSigninDateBegin(String signinDateBegin) {
		this.signinDateBegin = signinDateBegin;
	}

	public String getSigninDateEnd() {
		return signinDateEnd;
	}

	public void setSigninDateEnd(String signinDateEnd) {
		this.signinDateEnd = signinDateEnd != null&&signinDateEnd !="" ? signinDateEnd+" 23:59:59" :"";
	}
}
