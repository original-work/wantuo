package com.unionx.wantuo.service;


import com.unionx.wantuo.model.Accessory;


public interface AccessoryService {
	
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
	 * 上传图片保存信息
	 * @param ay
	 * @return
	 */
	public int saveAccessory(Accessory ay);
}
