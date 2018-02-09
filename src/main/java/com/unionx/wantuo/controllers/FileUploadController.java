package com.unionx.wantuo.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.unionx.wantuo.model.Accessory;
import com.unionx.wantuo.service.AccessoryService;
import com.unionx.wantuo.utils.CommonUtils;
import com.unionx.wantuo.utils.Rest;
import com.unionx.wantuo.utils.UUIDUtil;

@Controller
@RequestMapping("/FileUpload")
public class FileUploadController {
	@Autowired
	private AccessoryService accessoryService;
	
	@RequestMapping(value="/phoneFilesUpload",method=RequestMethod.POST)
	@ResponseBody
	 public Rest filesUpload(@RequestParam("file") MultipartFile[] files,HttpServletRequest request) {
		Rest rest=new Rest();
		Map<Object,Object> map=new HashMap<Object, Object>();
		 String fielName="";
		 String fielPath="";
		 String path="";
	        if (files != null && files.length > 0) {
	            for (int i = 0; i < files.length; i++) {
	            	Accessory a=new Accessory();
	                MultipartFile file = files[i];
	                // 保存文件
	                fielPath=CommonUtils.saveFile(file,CommonUtils.getProperties());
	                a.setName(UUIDUtil.getUUID());
	                a.setPath(fielPath);
	                a.setStatus(2);
	                a.setStatusName("可用");
	                a.setExt(fielPath.substring(fielPath.lastIndexOf(".")+1, fielPath.length()));
	                fielName+=a.getName()+",";
	                path+=a.getPath()+",";
	                accessoryService.saveAccessory(a);
	                map.put("fielName", fielName.substring(0, fielName.length()-1));
	                map.put("filePath", path.substring(0, path.length()-1));
	            }
	            if(fielPath!=null&&!"".equals(fielPath)){
                	rest.setStatus("0");
                	rest.setMessage("上传成功");
                }
	        }else{
	        	rest.setStatus("1");
            	rest.setMessage("上传失败");
	        }
	        rest.setData(map);
	        return rest;
	    }

	
	/**
	 * 上传头像
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/phoneHeadImgsUpload",method=RequestMethod.POST)
	@ResponseBody
	public Rest headImgsUpload(MultipartFile file){
		Rest rest=new Rest();
		Map<Object,Object> map=new HashMap<Object, Object>();
		try {
			String filePath=CommonUtils.saveFile(file,CommonUtils.getProperties());
			map.put("filePath", filePath);
			rest.setStatus("0");
			rest.setMessage("方法请求成功！");
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		rest.setData(map);
		return rest;
	}
	/**
	 * @param files
	 * @param request web 端上传 返回name
	 * @return
	 */
	@RequestMapping(value="/oneFilesUpload")
	@ResponseBody
	 public String oneFilesUpload(@RequestParam("file") MultipartFile[] files,HttpServletRequest request) {
		Rest rest=new Rest();
		Map<Object,Object> map=new HashMap<Object, Object>();
		 String fielName="";
		 String fielPath="";
		 String path="";
	        if (files != null && files.length > 0) {
	            for (int i = 0; i < files.length; i++) {
	            	Accessory a=new Accessory();
	                MultipartFile file = files[i];
	                // 保存文件
	                String fileName = file.getOriginalFilename();
			        String extensionName = fileName
			                .substring(fileName.lastIndexOf(".") + 1);
			        String newFileName = String.valueOf(System.currentTimeMillis())
			                + "." + extensionName;
			        fielPath=CommonUtils.FileUpload(newFileName, file,request);
	                a.setName(UUIDUtil.getUUID());
	                a.setPath(fielPath);
	                a.setStatus(2);
	                a.setStatusName("可用");
	                a.setExt(fielPath.substring(fielPath.lastIndexOf(".")+1, fielPath.length()));
	                fielName+=a.getName()+",";
	                path+=a.getPath()+",";
	                accessoryService.saveAccessory(a);
	                map.put("fielName", fielName.substring(0, fielName.length()-1));
	                map.put("filePath", path.substring(0, path.length()-1));
	            }
	            if(fielPath!=null&&!"".equals(fielPath)){
                	rest.setStatus("0");
                	rest.setMessage("上传成功");
                }
	        }else{
	        	rest.setStatus("1");
            	rest.setMessage("上传失败");
	        }
	        rest.setData(map);
	        return fielName.substring(0, fielName.length()-1);
	    }
	
	/**
	 * 活动编辑图片
	 * @param file
	 */
	@ResponseBody
	@RequestMapping(value="activityPicture")
	public String updateActivityPicture(@RequestParam("file") MultipartFile[] files,HttpServletRequest request){
		String filePath="";
        try {
			if (files != null && files.length > 0) {
			    for (int i = 0; i < files.length; i++) {
			        MultipartFile file = files[i];
			        String fileName = file.getOriginalFilename();
			        String extensionName = fileName
			                .substring(fileName.lastIndexOf(".") + 1);
			        String newFileName = String.valueOf(System.currentTimeMillis())
			                + "." + extensionName;
			        filePath=CommonUtils.FileUpload(newFileName, file,request);
			    }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "http://"+filePath;
	}
}
