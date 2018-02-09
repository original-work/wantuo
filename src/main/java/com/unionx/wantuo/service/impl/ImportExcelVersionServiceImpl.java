package com.unionx.wantuo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.OrganizationMapper;
import com.unionx.wantuo.dao.SchoolMapper;
import com.unionx.wantuo.model.Organization;
import com.unionx.wantuo.model.School;
import com.unionx.wantuo.service.ImportExcelService;
import com.unionx.wantuo.utils.ExcelVersionUtil;

@Service
public class ImportExcelVersionServiceImpl extends BaseServiceImpl<Organization> implements ImportExcelService{

	@Autowired
	private OrganizationMapper organizationMapper;
	
	
	@Autowired
	private SchoolMapper sm;
	/**
	 * 读取excel  存到数据库
	 */
	@Override
	public void importExcel(String file) {
		Date date = new Date();
		InputStream is = null;
		ExcelVersionUtil excelVersionUtil = new ExcelVersionUtil();
		Workbook workbook = null;
		try {
			 /** 判断文件的类型，是2003还是2007 */  
            boolean isExcel2003 = true; 
            if (ExcelVersionUtil.isExcel2007(file)){ 
                isExcel2003 = false;  
            }  
			 /** 调用本类提供的根据流读取的方法 */  
            is = new FileInputStream(new File(file));
           
            if(isExcel2003){
            	workbook = new HSSFWorkbook(is);
            }else{
            	workbook = new XSSFWorkbook(is);
            }
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workbook.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }

                int firsti = sheet.getFirstRowNum();
                int lasti = sheet.getLastRowNum();

                // 读取首行 即,表头
//                Row firstRow = sheet.getRow(firsti);
//                for (int i = firstRow.getFirstCellNum(); i <= firstRow.getLastCellNum(); i++) {
//                    Cell cell = firstRow.getCell(i);
//                    String cellValue = excelVersionUtil.getCellValue(cell, true);
//                }
                System.out.println("");
                //定义数据库表字段名
                String location = "";
                String locatinName = "";
                String bairro = "";
                String bairroName = "";
                String address = "";
                String loginAccounts = "";
                String email = "";
                String organizatio_type = "";
                String organizatio_type_name = "";
                String organization_abbreviation = "";
                String organization = "";
                String organization_contacts = "";
                String id_card_image = "";
                String business_license_image = "";
                String coordinate_x = "";
                String coordinate_y = "";
                String phone = "";
                String id_card = "";
                
                
                // 读取数据行
                for (int i = firsti + 1; i <= lasti; i++) {
                    Row currentRow = sheet.getRow(i);// 当前行
                    int j = currentRow.getFirstCellNum(); // 首列
//                    int lastColumnIndex = currentRow.getLastCellNum();// 最后一列

                        location = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        locatinName = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        bairro = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        bairroName = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        address = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        loginAccounts = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        email = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        organizatio_type = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        organizatio_type_name = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        organization_abbreviation = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        organization = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        organization_contacts = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        id_card_image = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        business_license_image = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        coordinate_x = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        coordinate_y = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        phone = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        id_card = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                        System.out.println("name:"+loginAccounts);
                        Organization og = new Organization();
                        og.setLocation(Integer.valueOf(location));
                        og.setLocationName(locatinName);
                        og.setBairro(Integer.valueOf(bairro));
                        og.setBairroName(bairroName);
                        og.setAddress(address);
                        og.setLoginAccounts(loginAccounts);
                        og.setEmail(email);
                        og.setOrganization(organization);
                        og.setOrganizationAbbreviation(organization_abbreviation);
                        og.setOrganizatioType(Integer.valueOf(organizatio_type));
                        og.setOrganizationContacts(organization_contacts);
                        og.setOrganizatioTypeName(organizatio_type_name);
                        og.setIdCard(id_card);
                        og.setIdCardImage(id_card_image);
                        og.setBusinessLicenseImage(business_license_image);
                        og.setPhone(phone);
                        og.setCoordinateX(coordinate_x);
                        og.setCoordinateY(coordinate_y);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String date2 = dateFormat.format(date);
                        og.setCreateDate(date2);
                        og.setUpdateDate(date2);
                       
                        organizationMapper.insertSelective(og);
                    }
                    System.out.println("");
                }

        } catch (Exception e) {
            e.printStackTrace();
        } 

	}
	@Override
	public void ImportSchoolExcel(String file) {
		// TODO Auto-generated method stub
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		InputStream is = null;
		ExcelVersionUtil excelVersionUtil = new ExcelVersionUtil();
		Workbook workbook = null;
		try {
			 /** 判断文件的类型，是2003还是2007 */  
	        boolean isExcel2003 = true; 
	        if (ExcelVersionUtil.isExcel2007(file)){ 
	            isExcel2003 = false;  
	        }  
			 /** 调用本类提供的根据流读取的方法 */  
	        is = new FileInputStream(new File(file));
	       
	        if(isExcel2003){
	        	workbook = new HSSFWorkbook(is);
	        }else{
	        	workbook = new XSSFWorkbook(is);
	        }
	        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
	            Sheet sheet = workbook.getSheetAt(numSheet);
	            if (sheet == null) {
	                continue;
	            }

	            int firsti = sheet.getFirstRowNum();
	            int lasti = sheet.getLastRowNum();

	            // 读取首行 即,表头
	            Row firstRow = sheet.getRow(firsti);
	            for (int i = firstRow.getFirstCellNum(); i <= firstRow.getLastCellNum(); i++) {
	                Cell cell = firstRow.getCell(i);
	                String cellValue = excelVersionUtil.getCellValue(cell, true);
	                System.out.print(" " + cellValue + "\t");
	            }
	            
	            
	            // 读取数据行
	            for (int i = firsti; i <= lasti; i++) {
	                Row currentRow = sheet.getRow(i);// 当前行
	                int j = currentRow.getFirstCellNum(); // 首列
	               // int lastColumnIndex = currentRow.getLastCellNum();// 最后一列
               	 	String id= excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
               	 	String location= excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);//默认最左边编号也算一列 所以这里得j++
                    String locationName= excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                    String bairro= excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                    String bairroName= excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                    String schoolName= excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                    String address= excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                    String property= excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                    String propertyName= excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
                    String fullName= excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
	                //String loginAccounts = excelVersionUtil.getCellValue(sheet.getRow(i).getCell(j++), true);
	                System.out.println(id);
	                
                    School s=new School();
                    s.setSchoolName(schoolName);
                    s.setLocation(Integer.parseInt(location));
                    s.setLocationName(locationName);
                    s.setBairro(Integer.parseInt(bairro));
                    s.setBairroName(bairroName);
                    s.setAddress(address);
                    s.setProperty(Integer.parseInt(property));
                    s.setPropertyName(propertyName);
                    s.setFullName(fullName);
                    s.setCreateDate(format.format(new Date()));
                    sm.insertSelective(s);
	                }
	            }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
	}
	
/*
	public static void main(String[] args) {
		String file="F:\\school\\SampleData.xlsx";
		
	}*/
}
