package com.unionx.wantuo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.model.PhoneStudent;
import com.unionx.wantuo.model.StudentClass;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.model.Teacher;
import com.unionx.wantuo.service.ClassService;
import com.unionx.wantuo.service.OrganizationService;
import com.unionx.wantuo.service.StudentClassService;
import com.unionx.wantuo.service.StudentSchoolService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.service.TeacherService;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/studentClass")
public class StudentClassController {
	
	private static Logger log = LoggerFactory.getLogger(StudentClassController.class);
	
	@Autowired
	private StudentClassService studentClassService;
	
	@Autowired
	private StudentSchoolService studentSchoolService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private OrganizationService ozService;
	
	@Autowired
	private TeacherService thService;
	
	@Autowired
	private SystemLogService systemLogService;
	/**
	 * @Title: phoneSelect 
	 * @Description: TODO(查询班级下学生) 
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneSelect")
	public Rest phoneSelect(StudentClass sc,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			Pager<StudentClass> pager=studentClassService.queryByList(sc,c);
			map.put("StudentClassList",pager.getList());
			map.put("count", pager.getCount());
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * @Title: phoneSaveList 
	 * @Description: TODO(批量保存学员与班级关系) 
	 * @param @param studentIds
	 * @param @param classId
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@Transactional
	@ResponseBody
	@RequestMapping("/phoneSaveList")
	public Rest phoneSaveList(String studentIds,String classId,String login) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(studentIds)||null==studentIds){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写学生id！");
				return rest;
			}
			if("".equals(classId)||null==classId){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写班级id！");
				return rest;
			}
			String studentId[]= studentIds.split(",");
			for(int i=0;i<studentId.length;i++){
				studentClassService.saveStudentClass(studentId[i],classId);
			}
			int i=studentId.length;
			classService.updateNum(i,classId);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SAVE_MESSAGE_SUCCESS);
			try{
				if(!"".equals(login)&&null!=login){
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(login);
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					sl.setCreate_date(formatter.format(new Date()));
					sl.setOperation_module("班级管理");
					sl.setOperation_function("班级批量添加学生");
					Organization o = ozService.selectByLoginAccounts(login);
					if(null == o){
						Teacher th=thService.selectByLoginAccounts(login);
						sl.setName(th.getName());
					}else{
						sl.setName(o.getOrganization());
					}
					systemLogService.save(sl);
				}
			}catch (Exception e){
			}
		}catch(Exception e){
			e.printStackTrace();
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SAVE_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * @Title: phoneDeleteList 
	 * @Description: TODO(批量删除班级下的学生) 
	 * @param @param studentIds
	 * @param @param classId
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@Transactional
	@ResponseBody
	@RequestMapping("/phoneDeleteList")
	public Rest phoneDeleteList(String studentIds,String classId,String login) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(studentIds)||null==studentIds){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写学生id！");
				return rest;
			}
			if("".equals(classId)||null==classId){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写班级id！");
				return rest;
			}
			String studentId[]= studentIds.split(",");
			for(int i=0;i<studentId.length;i++){
				studentClassService.deleteStudentClass(studentId[i],classId);
			}
			int i=-studentId.length;
			classService.updateNum(i,classId);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SAVE_MESSAGE_SUCCESS);
			try{
				if(!"".equals(login)&&null!=login){
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(login);
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					sl.setCreate_date(formatter.format(new Date()));
					sl.setOperation_module("班级管理");
					sl.setOperation_function("批量删除班级下的学生");
					Organization o = ozService.selectByLoginAccounts(login);
					if(null == o){
						Teacher th=thService.selectByLoginAccounts(login);
						sl.setName(th.getName());
					}else{
						sl.setName(o.getOrganization());
					}
					systemLogService.save(sl);
				}
			}catch (Exception e){
			}
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SAVE_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * @Title: phoneSelectNoStudent 
	 * @Description: TODO(获取班级下该机构未添加的学生) 
	 * @param @param classId
	 * @param @param organizationAccounts
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneSelectNoStudent")
	public Rest phoneSelectNoStudent(String classId,String organizationAccounts,String name) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(classId)||null==classId){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写班级id！");
				return rest;
			}
			if("".equals(organizationAccounts)||null==organizationAccounts){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写机构帐号！");
				return rest;
			}
			List<PhoneStudent> studentList=studentSchoolService.selectOrganizationStudent(organizationAccounts,name);
			List<PhoneStudent> studentListA=studentClassService.selectClassStudent(classId);
			
			for(int i=studentList.size();i>0;i--){
				int num=0;
					for(PhoneStudent sla : studentListA){
						if(num==0){
							if(studentList.get(i-1).getId().equals(sla.getId())){
								num=1;
								studentList.remove(studentList.get(i-1));
							}
						}
				}
			}
			map.put("studentList",studentList);
			map.put("count", studentList.size());
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);	
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
}
