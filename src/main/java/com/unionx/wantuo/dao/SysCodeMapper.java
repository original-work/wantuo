package com.unionx.wantuo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.unionx.wantuo.base.BaseDao;
import com.unionx.wantuo.model.SysCode;

public interface SysCodeMapper extends BaseDao<SysCode>{
	
	/**
	 * 后台管理查询列表
	 * @param sc
	 * @return
	 */
	List<SysCode> adminSelect(@Param("name") String name ,@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize);
	
	/**
	 * 后台管理查询总记录数
	 * @param sc
	 * @return
	 */
	Integer  adminSelectCount(@Param("name") String name );
	
	/**
	 * 后台管理删除数据字典
	 * @param id
	 * @return
	 */
	int deleteById(@Param("id")String id);
	
	/**
	 * 修改字典信息
	 * @param sc
	 * @return
	 */
	int updateByPrimaryKeySelective(SysCode sc);
	
	List<SysCode> selectParent();
	
	/**
	 * 添加数据子店
	 * @param sc
	 * @return
	 */
	int insertSelective(SysCode sc);
}