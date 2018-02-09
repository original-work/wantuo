package com.unionx.wantuo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.unionx.wantuo.model.Manage;
import com.unionx.wantuo.model.School;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.service.SchoolService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.ExcelVersionUtil;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/school")
public class SchoolController {
	
	private static Logger log = LoggerFactory.getLogger(SchoolController.class);
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private SystemLogService systemLogService;
	/**
	 * @Title: phoneQueryByList 
	 * @Description: TODO(手机端模糊查询学校，分页) 
	 * @param @param s
	 * @param @param c
	 * @author abner
	 * @return Map<Object,Object> 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneQueryByList")
	public Rest phoneQueryByList(School s,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			log.error("schoolName is "+s.getSchoolName());
			if(!"".equals(s.getSchoolName())&&null!=s.getSchoolName()){
				s.setSchoolName(new String(s.getSchoolName().getBytes("ISO-8859-1"),"UTF-8"));
			}
			s.setStatus(ConstantUtil.SATATUS_CHECK);
			Pager<School> pager=schoolService.queryByList(s,c);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			map.put("schoolList",pager.getList());
			map.put("count", pager.getCount());
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	
	/**
	 * 后台管理学校列表展示
	 * @param s
	 * @param c
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/QueryByList",method=RequestMethod.POST)
	public Rest QueryByList(School s,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			s.setStatus(ConstantUtil.SATATUS_CHECK);
			Pager<School> pager=schoolService.queryByList(s,c);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			map.put("schoolList",pager.getList());
			map.put("count", pager.getCount());
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	@RequestMapping(value="deleteBySchoolId",method=RequestMethod.POST)
	@ResponseBody
	public Rest deleteBySchoolId(HttpServletRequest request,String idStrs){
		Rest rest=new Rest();
		String[] ids=null;
		try {
			if(idStrs!=null&!"".equals(idStrs.toString())){
				ids=idStrs.split(",");
			}
			for (int i = 0; i < ids.length; i++) {
				if(ids[i]==null||ids[i].equals("")){
					continue;
				}
				schoolService.deleteById(ids[i].toString());
			}
			try{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				HttpSession session =request.getSession();
			    Manage manage =(Manage)session.getAttribute("userinfo");
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(manage.getLogin_accounts());
				sl.setCreate_date(format.format(new Date()));
				sl.setOperation_module("学校管理");
				sl.setOperation_function("删除");
				sl.setName(manage.getManage_name());
				systemLogService.save(sl);
			}catch (Exception e){
			}
			rest.setStatus("0");
			rest.setMessage("信息删除成功！");
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法请求失败！");
		}
		return rest;
	}
	
	@RequestMapping(value="/saveSchoolBySeletc",method=RequestMethod.POST)
	@ResponseBody
	public Rest saveSchoolBySeletc(HttpServletRequest request,@ModelAttribute School school){
		Rest rest=new Rest();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			school.setCreateDate(formatter.format(new Date()));
			school.setStatus(2);
			int num=schoolService.saveSchool(school);
			if(num>0){
				rest.setStatus("0");
				rest.setMessage("添加成功");
				try{
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					HttpSession session =request.getSession();
				    Manage manage =(Manage)session.getAttribute("userinfo");
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(manage.getLogin_accounts());
					sl.setCreate_date(format.format(new Date()));
					sl.setOperation_module("学校管理");
					sl.setOperation_function("添加");
					sl.setName(manage.getManage_name());
					systemLogService.save(sl);
				}catch (Exception e){
				}
			}else{
				rest.setStatus("2");
				rest.setMessage("添加失败");
			}
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法访问失败");
		}
		return rest;
	}
	
	
	@RequestMapping(value="/updateSchool",method=RequestMethod.POST)
	@ResponseBody
	public Rest updateSchool(HttpServletRequest request,@ModelAttribute School school){
		Rest rest=new Rest();
		try {
			int num=schoolService.updateSchoolById(school);
			if(num>0){
				rest.setStatus("0");
				rest.setMessage("修改成功");
				try{
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					HttpSession session =request.getSession();
				    Manage manage =(Manage)session.getAttribute("userinfo");
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(manage.getLogin_accounts());
					sl.setCreate_date(format.format(new Date()));
					sl.setOperation_module("学校管理");
					sl.setOperation_function("编辑");
					sl.setName(manage.getManage_name());
					systemLogService.save(sl);
				}catch (Exception e){
				}
			}else{
				rest.setStatus("2");
				rest.setMessage("修改失败");
			}
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法访问失败");
		}
		return rest;
	}
	
	/**
	 * 批量导入学校
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/importSchool",method=RequestMethod.POST)
	@ResponseBody
	public String importSchool(HttpServletRequest request,MultipartFile file){
		ExcelVersionUtil excelVersionUtil = new ExcelVersionUtil();
		Workbook workbook = null;
		String message;
		int i=0;
		String ids = "";
		try{
            if ("xlsx".equals(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1, file.getOriginalFilename().length()))){
            	workbook = new XSSFWorkbook(file.getInputStream());
            }
            if("xls".equals(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1, file.getOriginalFilename().length()))){
            	workbook = new HSSFWorkbook(file.getInputStream());
            }
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workbook.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }
                int firsti = sheet.getFirstRowNum();
                int lasti = sheet.getLastRowNum();
                // 读取数据行
                for (i = firsti + 1; i <= lasti; i++) {
                    try{
                    	Row currentRow = sheet.getRow(i);// 当前行
                        int j = currentRow.getFirstCellNum(); // 首列
                        School s = new School();
                        s.setLocation(Integer.valueOf(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;","")));
                        s.setLocationName(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;",""));
                        s.setBairro(Integer.valueOf(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;","")));
                        s.setBairroName(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;",""));
                        s.setSchoolName(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;",""));
                        s.setAddress(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;",""));
                        s.setProperty(Integer.valueOf(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;","")));
                        s.setPropertyName(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;",""));
                        s.setFullName(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;",""));
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        s.setCreateDate(dateFormat.format(new Date()));
                        s.setUpdateDate(dateFormat.format(new Date()));
                        schoolService.saveSchool(s);
                    }catch (Exception e){
                    	ids=ids+(i+1)+",";
                    }

                    }
                }
            if(ids.length()>1){
                message="导入成功，第"+ids+"行导入失败！";
            }else{
                message="导入成功！";
            }
			try{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				HttpSession session =request.getSession();
			    Manage manage =(Manage)session.getAttribute("userinfo");
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(manage.getLogin_accounts().replace("&nbsp;",""));
				sl.setCreate_date(format.format(new Date()));
				sl.setOperation_module("学校管理");
				sl.setOperation_function("导入");
				sl.setName(manage.getManage_name());
				systemLogService.save(sl);
			}catch (Exception e){
			}
		} catch (Exception e) {
			message="导入失败！";
			}
		return message;
	}
	
	@RequestMapping(value="/schoolDingWei",method=RequestMethod.POST)
	@ResponseBody
	public Rest schoolDingWei(String pathStr,String idStr){
		Rest rest=new Rest();
		Map<String, String> map = new HashMap<String,String>();
		try{
			String[] paths=null;
			String[] ids=null;
			if(pathStr!=null&&!"".equals(pathStr)){
				paths=pathStr.split("~`");
			}
			if(idStr!=null&&!"".equals(idStr)){
				ids=idStr.split(",");
			}
//			int num=0;
			for (int i = 0; i < ids.length; i++) {
				if(ids[i]!=null&&!"".equals(ids[i])){
					map=com.unionx.wantuo.utils.LocationUtil.getLatitude(paths[i]);
					School s=new School();
					s.setId(Integer.parseInt(ids[i]));
					s.setCoordinateX(map.get("lat"));
					s.setCoordinateY(map.get("lng"));
//					num=schoolService.updateSchoolById(s);
				}
			}
				rest.setStatus("0");
				rest.setMessage("定位成功！");
			
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rest.setStatus("1");
				rest.setMessage("方法访问失败");
		}
		return rest;
	}
}
