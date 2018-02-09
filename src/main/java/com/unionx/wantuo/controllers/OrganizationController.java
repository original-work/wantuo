package com.unionx.wantuo.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.unionx.wantuo.model.Accessory;
import com.unionx.wantuo.model.ClassModel;
import com.unionx.wantuo.model.ClassReport;
import com.unionx.wantuo.model.Collect;
import com.unionx.wantuo.model.Login;
import com.unionx.wantuo.model.Manage;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.model.SystemLog;
import com.unionx.wantuo.service.AccessoryService;
import com.unionx.wantuo.service.ClassService;
import com.unionx.wantuo.service.CollectService;
import com.unionx.wantuo.service.LoginService;
import com.unionx.wantuo.service.OrganizationService;
import com.unionx.wantuo.service.SystemLogService;
import com.unionx.wantuo.utils.CommonUtils;
import com.unionx.wantuo.utils.Condition;
import com.unionx.wantuo.utils.ConstantUtil;
import com.unionx.wantuo.utils.ExcelVersionUtil;
import com.unionx.wantuo.utils.Pager;
import com.unionx.wantuo.utils.Rest;

@Controller
@RequestMapping("/organization")
public class OrganizationController {
	
	private static Logger log = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private AccessoryService accessoryService;
	
	@Autowired
	private CollectService collectService;
	
	@Autowired
	private LoginService logService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private SystemLogService systemLogService;
	/**
	 * @Title: getById 
	 * @Description: TODO(手机端获取机构信息) 
	 * @param @param o
	 * @author abner
	 * @return Map<Object,Object> 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneGetById")
	public Rest phoneGetById(Organization o,String currentLogin) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		String pathName="";
		try{
			o=organizationService.getById(o);
			if(null!=o){
				String name[]= o.getPhotoAlbum().split(",");
				String names="";
				for(int i=0;i<name.length;i++){
					if(!"".equals(name[i])&&null!=name[i]){
						Accessory accessory=new Accessory();
						accessory.setName(name[i]);
						accessory=accessoryService.getByName(accessory);
						if(null!=accessory&&null!=accessory.getPath()){
							names=names+accessory.getPath()+",";
							pathName=pathName+accessory.getName()+",";
						}
					}
				}
				if(names.length()>1){
					names=names.substring(0,names.length()-1);	
				}
				o.setPhotoAlbum(names);
				o.setPathName(pathName);
				Collect c =new Collect();
				c.setOrganizationAccounts(o.getLoginAccounts());
				c.setPatriarchAccounts(currentLogin);
				int i=0;
				if(!"".equals(currentLogin)||null!=currentLogin){
					i=collectService.select(c);
				}
				if(i>=1){
					map.put("isCollect", "1");
				}else{
					map.put("isCollect", "0");
				}
				rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
				rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
				map.put("organization",o);
			}else{
				rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
				rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * @Title: phoneQueryByList 
	 * @Description: TODO(手机端查询机构列表，分页) 
	 * @param @param o
	 * @param @param c
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneQueryByList")
	public Rest phoneQueryByList(Organization o,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if(!"".equals(o.getOrganization())&&null!=o.getOrganization()){
				o.setOrganization(new String(o.getOrganization().getBytes("ISO-8859-1"),"UTF-8"));
			}
			o.setWarranty(2);
			o.setCheck(2);
			Pager<Organization> pager=organizationService.queryByList(o,c);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			map.put("organizationList",pager.getList());
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
	 * @Title: phoneUpdate 
	 * @Description: TODO(移动端修改机构信息) 
	 * @param @param o
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneUpdate")
	public Rest phoneUpdate(Organization o) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(o.getLoginAccounts())||null==o.getLoginAccounts()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写机构帐号！");
				return rest;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			o.setUpdateDate(formatter.format(new Date()));
			Organization org=organizationService.selectDetails(o.getLoginAccounts());
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_UPDATE_MESSAGE_SUCCESS);
			map.put("organization", org);
			try{
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(org.getLoginAccounts());
				sl.setCreate_date(formatter.format(new Date()));
				sl.setOperation_module("机构基本信息");
				sl.setOperation_function("修改");
				sl.setName(o.getOrganization());
				systemLogService.save(sl);
			}catch (Exception e){
			}
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_UPDATE_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * @Title: phoneUpdateOrder
	 * @Description: TODO(手机端机构预约+1) 
	 * @param @param o
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/phoneUpdateOrder")
	public Rest phoneUpdateOrder(Organization o) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			if("".equals(o.getId())||null==o.getId()){
				rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
				rest.setMessage("请填写机构id！");
				return rest;
			}
			organizationService.phoneUpdateOrder(o);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_UPDATE_MESSAGE_SUCCESS);
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_UPDATE_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	/**
	 * 后台管理添加机构
	 */
	@ResponseBody
	@RequestMapping("/InsertOrganization")
	public Rest InsertOrganization(HttpServletRequest request,Organization o){
		Rest rest=new Rest();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Login ln=logService.findToRegister(o.getLoginAccounts(),null);
			int num=0;
			if(ln==null||"".equals(ln)){
				/*logService*/
				o.setCheck(1);//审核中，不可用
				o.setCreateDate(format.format(new Date()));
				Login login=new Login();
				login.setLoginAccounts(o.getLoginAccounts());
				login.setPassword(o.getPassword());
				login.setStatus(1);
				login.setAccountsType(2);
				login.setCreateDate(format.format(new Date()));
				num=organizationService.saveOrganizationBySelected(o);
				num= logService.saveLoginBySelected(login);
				if(num>0){
					rest.setStatus("0");
					rest.setMessage("添加成功！");
					try{
						HttpSession session =request.getSession();
					    Manage manage =(Manage)session.getAttribute("userinfo");
						SystemLog sl = new SystemLog();
						sl.setLogin_accounts(manage.getLogin_accounts());
						sl.setCreate_date(format.format(new Date()));
						sl.setOperation_module("机构管理");
						sl.setOperation_function("添加");
						sl.setName(manage.getManage_name());
						systemLogService.save(sl);
					}catch (Exception e){
					}
				}
			}else{
				rest.setStatus("1");
				rest.setMessage("账号已注册！");
			}
		} catch (Exception e) {
			rest.setStatus("1");
			rest.setMessage("方法访问失败");
		}
		return rest;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/updateHouTaiOrg",method=RequestMethod.POST)
	public Rest updateHouTaiOrg(HttpServletRequest request,Organization o){
		Rest rest=new Rest();
		//Map<Object,Object> map = new HashMap<Object,Object>();
		try{
			int num=organizationService.updateOrganzation(o);
			if(num>0){
				rest.setStatus("0");
				rest.setMessage("修改成功！");
			}
			try{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				HttpSession session =request.getSession();
			    Manage manage =(Manage)session.getAttribute("userinfo");
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(manage.getLogin_accounts());
				sl.setCreate_date(format.format(new Date()));
				sl.setOperation_module("机构管理");
				sl.setOperation_function("编辑");
				sl.setName(manage.getManage_name());
				systemLogService.save(sl);
			}catch (Exception e){
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rest.setStatus("1");
				rest.setMessage("方法访问失败");
			}
		return rest;
	}
	
	
	
	/**
	 * 机构定位
	 * @param path 地址
	 * @param id	帐号
	 * @return
	 */
	@RequestMapping(value="/organizationDingWei",method=RequestMethod.POST)
	@ResponseBody
	public Rest organizationDingWei(String pathStr,String idStr){
		Rest rest=new Rest();
		Map<String, String> map = new HashMap<String,String>();
		try{
			String[] paths=null;
			if(pathStr!=null&&!"".equals(pathStr)){
				paths=pathStr.split("~`");
			}
			String[] ids=null;
			if(idStr!=null&&!"".equals(idStr)){
				ids=idStr.split(",");
			}
			int num=0;
			for (int i = 0; i < ids.length; i++) {
				if(ids[i]!=null&&!"".equals(ids[i])){
					map=com.unionx.wantuo.utils.LocationUtil.getLatitude(paths[i]);
					num=organizationService.updateDingWer(map.get("lat"),map.get("lng"),ids[i]);
				}
			}
			if(num>0){
				rest.setStatus("0");
				rest.setMessage("定位成功！");
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rest.setStatus("1");
				rest.setMessage("方法访问失败");
		}
		return rest;
	}
	
	
	/**
	 * 批量导入机构
	 * @param file
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(value="/importOrganization",method=RequestMethod.POST)
	@ResponseBody
	public String importOrganization(HttpServletRequest request,MultipartFile file){
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
                        Organization o = new Organization();
                        o.setLoginAccounts(excelVersionUtil.getCellValue((sheet.getRow(i).getCell(j++)), true).replace("&nbsp;","").trim());
//                      o.setPassword(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true));
/*                        Random r=new Random();*/
                        o.setPassword(CommonUtils.getCode().trim());
                        /*o.setPassword(String.valueOf(r.nextInt(899999)+100000));*/
                        o.setLocation(Integer.valueOf(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;","")));
                        o.setLocationName(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;",""));
                        o.setBairro(Integer.valueOf(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;","")));
                        o.setBairroName(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;",""));
                        
                        o.setOrganization(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;",""));
                        o.setOrganizationAbbreviation(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;",""));
                        o.setAddress(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;",""));
                        o.setPhone(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;",""));
                        o.setOrganizationContacts(excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true).replace("&nbsp;",""));
                        
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        o.setCreateDate(dateFormat.format(new Date()));
                        o.setUpdateDate(dateFormat.format(new Date()));
                        Login ln=logService.findToRegister(o.getLoginAccounts(),null);
                        if(ln==null||"".equals(ln)){
                            Login login=new Login();
            				login.setLoginAccounts(o.getLoginAccounts().replace("&nbsp;",""));
            				login.setPassword(o.getPassword().replace("&nbsp;",""));
            				login.setStatus(1);
            				login.setAccountsType(2);
            				login.setCreateDate(dateFormat.format(new Date()));
                            organizationService.saveOrganzation(o);
                            logService.saveLoginBySelected(login);
                        }else{
                        	ids=ids+(i+1)+",";
                        }
                	}catch(Exception e){
                		ids=ids+(i+1)+",";
                	}
                    }
                }
            if(ids.length()>1){
                message="导入成功，第"+ids+"行导入失败！";
            }else{
                message="导入成功！";
            }try{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				HttpSession session =request.getSession();
			    Manage manage =(Manage)session.getAttribute("userinfo");
				SystemLog sl = new SystemLog();
				sl.setLogin_accounts(manage.getLogin_accounts());
				sl.setCreate_date(format.format(new Date()));
				sl.setOperation_module("机构管理");
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
	
	/**
	 * @Title: getId 
	 * @Description: TODO(分析获取数据) 
	 * @param @param id
	 * @param @return 设定文件 
	 * @author abner
	 * @return Map<Object,Object> 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/phoneGetId")//,method=RequestMethod.POST
	public Map<Object,Object> getId(Integer id) {
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
		    map.put("o",organizationService.selectById(id));
		}catch(Exception e){
			log.error(e.getMessage());
		}
		return map;
	}
	
	@RequestMapping(value="/findDetails",method=RequestMethod.GET)
	@ResponseBody
	public Map<Object,Object> findDetails(String loginAccounts){
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try {
			Organization o=organizationService.selectDetails(loginAccounts);
			map.put("org", o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * @Title: baobiaoList 
	 * @Description: TODO(机构班级报表) 
	 * @param @param o
	 * @param @param c
	 * @param @return 设定文件 
	 * @author abner
	 * @return Rest 返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/baobiaoList")
	public Rest baobiaoList(Organization o,Condition c) {
		Rest rest=new Rest();
		Map<Object,Object> map = new HashMap<Object,Object>(); 
		try{
			o.setWarranty(2);
			o.setCheck(2);
			Pager<Organization> pager=organizationService.queryByList(o,c);
			rest.setStatus(ConstantUtil.PHONE_SATATUS_SUCCESS);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_SUCCESS);
			List<ClassReport> cr =new ArrayList<ClassReport>();
			for(Organization oc : pager.getList()){
				ClassReport cr1 = new ClassReport();
				ClassModel cm =new ClassModel();
				cm.setOrganizationAccounts(oc.getLoginAccounts());
				cm.setStatus(2);
				cr1.setOrganizationAccounts(oc.getLoginAccounts());
				cr1.setOrganization(oc.getOrganization());
				cr1.setCount(classService.getCount(cm,c));
				cr.add(cr1);
			}
			map.put("classList",cr);
			map.put("count", pager.getCount());
		}catch(Exception e){
			rest.setStatus(ConstantUtil.PHONE_SATATUS_ERROR);
			rest.setMessage(ConstantUtil.PHONE_SELECT_MESSAGE_ERROR);
			log.error(e.getMessage());
		}
		rest.setData(map);
		return rest;
	}
	
	
	@RequestMapping(value="/paiMingDetails",method=RequestMethod.GET)
	public String paiMingDetails(HttpServletRequest request){
		try {
			String loginAccounts=request.getParameter("loginAccounts").toString();
			Organization o=organizationService.selectDetails(loginAccounts);
			HttpSession session=request.getSession();
			session.setAttribute("org", o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/views/paiming.jsp";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/updateOrg",method=RequestMethod.POST)
	public Rest updateOrg(HttpServletRequest request,String sort,String evaluate,String loginAccounts){
		Rest rest=new Rest();
		try{
			Organization o=new Organization();
			o.setSort(Integer.parseInt(sort));
			o.setEvaluate(Double.parseDouble(evaluate));
			o.setLoginAccounts(loginAccounts);
			int num=organizationService.updateOrganzation(o);
			if(num>0){
				rest.setStatus("0");
				rest.setMessage("修改成功！");
				try{
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					HttpSession session =request.getSession();
				    Manage manage =(Manage)session.getAttribute("userinfo");
					SystemLog sl = new SystemLog();
					sl.setLogin_accounts(manage.getLogin_accounts());
					sl.setCreate_date(format.format(new Date()));
					sl.setOperation_module("排行榜管理");
					sl.setOperation_function("排名");
					sl.setName(manage.getManage_name());
					systemLogService.save(sl);
				}catch (Exception e){
				}
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				rest.setStatus("1");
				rest.setMessage("方法访问失败");
			}
		return rest;
	}
	
	/**
	 * 导出班级报表
	 * @param o
	 * @param c
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/Exportbaobiao")
	public Rest Exportbaobiao(HttpServletResponse response,Organization o,Condition c) {
		Rest rest=new Rest();
		try{
			o.setWarranty(2);
			o.setCheck(2);
			String a=new String(o.getOrganization().getBytes("iso-8859-1"),"UTF-8");
			o.setOrganization(a);
			Pager<Organization> pager=organizationService.queryByList(o,c);
			List<ClassReport> cr =new ArrayList<ClassReport>();
			for(Organization oc : pager.getList()){
				ClassReport cr1 = new ClassReport();
				ClassModel cm =new ClassModel();
				cm.setOrganizationAccounts(oc.getLoginAccounts());
				cm.setStatus(2);
				cr1.setOrganizationAccounts(oc.getLoginAccounts());
				cr1.setOrganization(oc.getOrganization());
				cr1.setCount(classService.getCount(cm,c));
				cr.add(cr1);
			}
			HSSFWorkbook wb=CommonUtils.ExportClass(cr);
			response.setContentType("application/x-msdownload");
		    response.setHeader("Content-Disposition","attachment;filename=ClassReport.xls");
			wb.write(response.getOutputStream());
			rest.setStatus("0");
			rest.setMessage("导出成功");
		}catch(Exception e){
			rest.setStatus("1");
			rest.setMessage("导出失败");
			log.error(e.getMessage());
		}
		return rest;
	}
	
}
