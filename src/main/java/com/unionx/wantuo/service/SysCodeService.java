package com.unionx.wantuo.service;

import java.util.List;

import com.unionx.wantuo.model.SysCode;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.Pager;


public interface SysCodeService {
	
	/**
	 * @Title: queryByList 
	 * @Description: TODO(查询字典表列表 分页) 
	 * @param @param s
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Pager<SysCode> 返回类型 
	 * @throws
	 */
	public Pager<SysCode> queryByList(SysCode s,Condition c);
	
	/**
	 * 后台管理分页列表查询
	 * @param s
	 * @param c
	 * @return
	 */
	public List<SysCode> adminQueryByList(String name,Integer pageIndex,Integer pageSize);
	
	/**
	 * 后台管理分页列表查询总计路数
	 * @param s
	 * @param c
	 * @return
	 */
	public Integer getcount(String name);
	
	/**
	 * 后台管理删除数据字典
	 * @param id
	 * @return
	 */
	public int adminDeleteSysCode(String id);
	
	/**
	 * 后台管理修改子店信息
	 * @param sc
	 * @return
	 */
	public int updateSysCode(SysCode ssc);
	
	public List<SysCode> findByParent();
	
	/**
	 * 后台管理添加数据字典
	 * @param sc
	 * @return
	 */
	int addSysCode(SysCode sc);
}
