package com.unionx.wantuo.service;

import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;

public interface OrganizationService {
	
	/**
	 * 机构信息保存方法
	 * @param oz
	 * @return
	 */
	public int saveOrganizationBySelected(Organization oz);
	
	/**
	 * 根据机构登入账号查询机构信息
	 * @param LoginAccounts 机构帐号
	 * @return
	 */
	public Organization selectByLoginAccounts(String LoginAccounts);
	
	/**
	 * @Title: getById 
	 * @Description: TODO(手机端获取机构信息) 
	 * @param @param m
	 * @author abner
	 * @return Organization 返回类型 
	 * @throws
	 */
	public Organization getById(Organization o);
	
	/**
	 * @Title: queryByList 
	 * @Description: TODO(分页查询) 
	 * @param @param o
	 * @param @param c
	 * @author abner
	 * @return Pager<Organization> 返回类型 
	 * @throws
	 */
	public Pager<Organization> queryByList(Organization o,Condition c);
	
	/**
	 * @Title: save 
	 * @Description: TODO(保存机构信息) 
	 * @param @param o
	 * @author abner
	 * @return Integer 返回类型 
	 * @throws
	 */
	public Integer save(Organization o);
	
	/**
	 * @Title: save 
	 * @Description: TODO(修改机构信息) 
	 * @param @param o
	 * @author abner
	 * @return Integer 返回类型 
	 * @throws
	 */
	public Integer update(Organization o);
	
	/**
	 * @Title: phoneUpdateOrder 
	 * @Description: TODO(手机端机构预约+1) 
	 * @param @param o 设定文件 
	 * @author abner
	 * @return void 返回类型 
	 * @throws
	 */
	public void phoneUpdateOrder(Organization o);
	
	/**
	 * 根据账号修改机构信息
	 * @param o
	 * @return
	 */
	public int updateByLoginAccounts(Organization o);
	
	/**
	 *添加机构
	 * @param o
	 * @return
	 */
	public int saveOrganzation(Organization o);
	
	/**
	 * 后台管理修改机构信息
	 * @param o
	 * @return
	 */
	public int updateOrganzation(Organization o);
	
	/**
	 * 根据id查询机构
	 * @param id
	 * @return
	 */
	public Organization selectById(Integer id);
	
	public int updateDingWer(String x,String y,String id);
	
	/**
	 * 查询机构详情
	 * @param loginAccounts
	 * @return
	 */
	Organization selectDetails(String loginAccounts);
}
