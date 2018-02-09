package com.unionx.wantuo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionx.wantuo.model.ClassModel;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.model.StudentSchool;
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
@RequestMapping("/studentSchool")
public class StudentSchoolController {
	
	private static Logger log = LoggerFactory.getLogger(StudentSchoolController.class);
	
	@Autowired
	private StudentSchoolService studentSchoolService;
	
	@Autowired
	private SystemLogService systemLogService;
	
	@Autowired
	private OrganizationService ozService;
	
	@Autowired
	private TeacherService thService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private StudentClassService studentClassService;
	/**
	 * @Title: phoneStudentList 
	 * @Description: TODO(手机端获取机构下的学员) 
	 * @param @param organizationAccounts 机构id
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneStudentList")
	public Rest phoneStudentList(StudentSchool ss,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(ss.getOrganizationAccounts())||null==ss.getOrganizationAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写机构帐号！");
				return rest;
			}
			Pager<StudentSchool> studentSchoolList=studentSchoolService.queryByList(ss,c);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			map.put("studentSchoolList",studentSchoolList.getList());
			map.put("count",studentSchoolList.getCount());
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * @Title: phoneDeleteStudentSchool 
	 * @Description: TODO(删除学生与机构关系) 
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneDeleteStudentSchool")
	public Rest phoneDeleteStudentSchool(String studentId,String organizationAccounts,String login){
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(studentId)||null==studentId){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写学生id！");
				return rest;
			}
			if("".equals(organizationAccounts)||null==organizationAccounts){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写机构帐号！");
				return rest;
			}
//			int i=studentSchoolService.phoneDeleteStudentSchool(studentId,organizationAccounts);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_DELETE_MESSAGE_SUCCESS);
			ClassModel cm = new ClassModel();
			Condition c = new Condition();
			cm.setStatus(2);
			Pager<ClassModel> pager=classService.queryByList(cm,c);
			for(ClassModel cml : pager.getList()){
				Integer i1 = studentClassService.deleteStudentClass(studentId,String.valueOf(cml.getId()));
				if(i1==1){
					classService.updateNum(-1,String.valueOf(cml.getId()));	
				}
			}
			try{
				if(!"".equals(login)&&null!=login){
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(login);
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					sl.setCreate_date(formatter.format(new Date()));
					sl.setOperation_module("学员管理");
					sl.setOperation_function("删除机构下的学员");
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
			rest.setMessage(ConstantUtil.PHONE_DELETE_MESSAGE_ERROR);
			e.printStackTrace();
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
}
