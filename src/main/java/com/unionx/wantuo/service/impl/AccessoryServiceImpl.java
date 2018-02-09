package com.unionx.wantuo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.AccessoryMapper;
import com.unionx.wantuo.model.Accessory;
import com.unionx.wantuo.service.AccessoryService;

@Service("accessoryService")
public class AccessoryServiceImpl extends BaseServiceImpl<Accessory> implements AccessoryService {
	
	
	@Autowired
	private AccessoryMapper am;

	/**
	 * @Title: getByName 
	 * @Description: TODO(根据文件名称获取文件地址) 
	 * @param @param name
	 * @author abner
	 * @return Accessory 返回类型 
	 * @throws
	 */
	@Override
	public Accessory getByName(Accessory accessory) {
		return am.getByName(accessory);
	}

	
	@Override
	public int saveAccessory(Accessory ay) {
		// TODO Auto-generated method stub
		return am.insertSelective(ay);
	}
}
