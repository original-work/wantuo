package com.unionx.wantuo.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.unionx.wantuo.controllers.FileUploadController;
import com.unionx.wantuo.model.ClassReport;
import com.unionx.wantuo.model.Student;
import com.unionx.wantuo.model.Trace;



public class CommonUtils {

    public static String sn = "8SDK-EMY-6699-RFRUN";//软件序列号,请通过亿美销售人员获取
    public static String key = "4912";// 序列号首次激活时自己设定
    public static String password = "714985";// 密码,请通过亿美销售人员获取
    public static String baseUrl = "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/";
  
    public static String MESSAGE_REGISTER="(爱晚托验证码)，感谢您使用爱晚托产品，咨询电话400-070-8835";
    
    
    /**
     * 注册发送验证码
     * @param phone 手机号
     * @param verificatCode 验证码
     * @return 发送结果 0发送成功
     * @throws UnsupportedEncodingException
     */
    public static String sendNote(String phone,String verificatCode) throws UnsupportedEncodingException {
    	String message ="【爱晚托】"+verificatCode+MESSAGE_REGISTER;
    	message = URLEncoder.encode(message, "UTF-8");
		String code = "888";
		long seqId = System.currentTimeMillis();
		String param = "cdkey=" + sn + "&password=" + 714985 + "&phone=" + phone + "&message=" + message + "&addserial=" + code + "&seqid=" + seqId;
		String url = baseUrl + "sendsms.action";
		String ret = SDKHttpClient.sendSMS(url,param);
		return ret;
    }
    
    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
    
    public static String getCode() {
    	int charCount=6;
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;

    }
    
    
    
    public static Properties getProperties(){
		Properties properties = new Properties();
		InputStream inputStream =  FileUploadController.class.getClassLoader().getResourceAsStream("uploadPath.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	

	public static String saveFile(MultipartFile file,Properties ps) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中  )
                String filePath = ps.getProperty("filePath")+"/".trim()+ file.getOriginalFilename();
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                // 转存文件
                file.transferTo(saveDir);
                String linuxPath = ps.getProperty("linuxPath")+"/".trim()+ file.getOriginalFilename();
                return linuxPath;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
	
    public static String attestation(String login,String password) throws UnsupportedEncodingException {
    	String message = "【爱晚托】您好，您的帐号" + login + "密码" + password + "已通过认证可登录！";
    	message = URLEncoder.encode(message, "UTF-8");
		String code = "888";
		long seqId = System.currentTimeMillis();
		String param = "cdkey=" + sn + "&password=" + 714985 + "&phone=" + login + "&message=" + message + "&addserial=" + code + "&seqid=" + seqId;
		String url = baseUrl + "sendsms.action";
		String ret = SDKHttpClient.sendSMS(url,param);
		return ret;
    }
    
    
    
    /**
     * 到处班级报表
     * @param list
     */
    public static HSSFWorkbook ExportClass(List list){
    	// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("班级报表");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("机构名称");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("班级数量");  
        cell.setCellStyle(style);  
  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            ClassReport cr = (ClassReport) list.get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue(cr.getOrganization());  
            row.createCell((short) 1).setCellValue(cr.getCount());  
        }  
        // 第六步，将文件存到指定位置  
        return wb;
    }
    
    /**
     * 年纪报表导出
     * @param list
     * @return
     */
    public static HSSFWorkbook ExportGrade(List list){
    	// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("年级报表");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("机构名称");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("年级");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);  
        cell.setCellValue("学生数量");  
        cell.setCellStyle(style);
  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            Student cr = (Student) list.get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue(cr.getName());  
            row.createCell((short) 1).setCellValue(cr.getGrade());
            row.createCell((short) 2).setCellValue(cr.getId());
        }  
        // 第六步，将文件存到指定位置  
        return wb;
    }
    
    
    public static HSSFWorkbook ExportStudent(List list){
    	// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("年级报表");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("机构名称");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("班级名称");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);  
        cell.setCellValue("学生姓名");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);  
        cell.setCellValue("签到时间");  
        cell.setCellStyle(style);
  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            Trace cr = (Trace) list.get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue(cr.getOrganizationName());  
            row.createCell((short) 1).setCellValue(cr.getClassName());
            row.createCell((short) 2).setCellValue(cr.getStudentName());
            row.createCell((short) 3).setCellValue(cr.getSigninDate().substring(0,19));;  
        }  
        // 第六步，将文件存到指定位置  
        return wb;
    }
    
    
    public static String FileUpload(String newFileName,MultipartFile filedata,HttpServletRequest request){
		 String picDir="";
		 String filePath="";
		 try {
			Properties ps=CommonUtils.getProperties();
			 picDir= ps.getProperty("filePath")+"/".trim();
			 String saveFilePath=picDir;
			 
			 File fileDir=new File(saveFilePath);
			 if(!fileDir.exists()){
				 fileDir.mkdirs();
			 }
			 FileOutputStream out=new FileOutputStream(saveFilePath+newFileName);
			 out.write(filedata.getBytes());
			 filePath=ps.getProperty("linuxPath")+"/".trim()+newFileName;
			 out.flush();
			 out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return filePath;
	 }
    
    
    public static void main2(String[] args) throws UnsupportedEncodingException {
		String mdn = "18210890192";
		String message = "【亿美软通】您好，您的验证码是" + System.currentTimeMillis();
		message = URLEncoder.encode(message, "UTF-8");
		String code = "888";
		long seqId = System.currentTimeMillis();
		String param = "cdkey=" + sn + "&password=" + 714985 + "&phone=" + mdn + "&message=" + message + "&addserial=" + code + "&seqid=" + seqId;
		String url = baseUrl + "sendsms.action";
		String ret = SDKHttpClient.sendSMS(url,param);
		System.out.println("发送结果:" + ret);
			
	}
    
    
    public static void main(String[] args) {
		for (int i = 0; i < 400; i++) {
			String num=getCode();
			if(num.length()<6){
				System.out.println("小于6位"+num);
			}else if(num.length()>6){
				System.out.println("大于6位"+num);
			}
			System.out.println(num);
		}
	}
    
  
}
