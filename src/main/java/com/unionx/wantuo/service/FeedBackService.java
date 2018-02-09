package com.unionx.wantuo.service;

import com.unionx.wantuo.model.FeedBack;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;




public interface FeedBackService {
	
	/**
	 * @Title: save 
	 * @Description: TODO(手机端保存反馈信息) 
	 * @param @param fb
	 * @param @return 设定文件 
	 * @author abner
	 * @return Integer 返回类型 
	 * @throws
	 */
	public Integer save(FeedBack fb);
	
	/**
	 * @Title: queryByList 
	 * @Description: TODO(后台查询反馈列表) 
	 * @param @param f
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Pager<FeedBack> 返回类型 
	 * @throws
	 */
	public Pager<FeedBack> queryByList(FeedBack f, Condition c);
	
	/**
	 * @Title: update 
	 * @Description: TODO(后台删除) 
	 * @author abner
	 * @return void 返回类型 
	 * @throws
	 */
	public Integer update(FeedBack f);
	
}
