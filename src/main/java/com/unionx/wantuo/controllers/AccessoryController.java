package com.unionx.wantuo.controllers;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.model.Accessory;
import com.unionx.wantuo.service.AccessoryService;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("accessory")
public class AccessoryController {
	@Autowired
	private AccessoryService ayService;
	
	@RequestMapping(value="/findByName",method=RequestMethod.GET)
	@ResponseBody
	public Rest findByName(String nameStrs){
		Rest rest=new Rest();
		Map<Object, Object> map=new HashMap<Object, Object>();
		String[] nameStr=null;
		String path="";
		if(nameStrs!=null&&!"".equals(nameStrs)){
			nameStr=nameStrs.split(",");
			for (int i = 0; i < nameStr.length; i++) {
				Accessory accessory=new Accessory();
				accessory.setName(nameStr[i]);
				Accessory a=ayService.getByName(accessory);
				if(a!=null){
					path+=a.getPath()+",";
					map.put("path", path);
				}
			}
		}
		rest.setData(map);
		return rest;
	}
	
	@RequestMapping(value="/findPicture",method=RequestMethod.GET)
	@ResponseBody
	public Rest findPicture(String pathNameStr){
		Map<Object, Object> map=new HashMap<Object, Object>();
		Rest rest=new Rest();
		String path="";
		if(pathNameStr!=null&&!"".equals(pathNameStr)){
			String[] pathName=pathNameStr.split(",");
			for (int i = 0; i < pathName.length; i++) {
				Accessory accessory=new Accessory();
				accessory.setName(pathName[i]);
				Accessory a=ayService.getByName(accessory);
				if(a!=null){
					path+=a.getPath()+",";
				}
			}
		}
		map.put("path", path);
		rest.setData(map);
		return rest;
	}
}
