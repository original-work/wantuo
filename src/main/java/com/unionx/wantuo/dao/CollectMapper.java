package com.unionx.wantuo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.unionx.wantuo.base.BaseDao;
import com.unionx.wantuo.model.Collect;
import com.unionx.wantuo.model.Organization;



public interface CollectMapper extends BaseDao<Collect>{
	
	/**
	 * 收藏机构方法
	 * @param ct
	 * @return
	 */
	public int insertSelective(Collect ct);
	
	/**
	 * 取消收藏
	 * @param patriarchAccounts 家长登入账号
	 * @param organizationAccounts 机构账号
	 * @return
	 */
	public int unfavorite(@Param("patriarchAccounts")String patriarchAccounts,@Param("organizationAccounts")
	String organizationAccounts);

	/**
	 * @Title: select 
	 * @Description: TODO(根据家长帐号，学校帐号查询是否关注) 
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Integer 返回类型 
	 * @throws
	 */
	public Integer select(Collect c);
	
	public List<Organization> queryList(Map<Object, Object> map);
	
	public Integer count(Map<Object, Object> map);
}