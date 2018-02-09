package com.unionx.wantuo.dao;

import com.unionx.wantuo.base.BaseDao;
import com.unionx.wantuo.model.Accessory;

public interface AccessoryMapper extends BaseDao<Accessory> {
	
	/**
	 * @Title: getByName 
	 * @Description: TODO(根据文件名称获取文件地址) 
	 * @param @param name
	 * @author abner
	 * @return Accessory 返回类型 
	 * @throws
	 */
	public Accessory getByName(Accessory accessory);
	
	/**
	 * 图片上传保存信息
	 * @param accessory
	 * @return
	 */
	public int insertSelective(Accessory accessory);
}