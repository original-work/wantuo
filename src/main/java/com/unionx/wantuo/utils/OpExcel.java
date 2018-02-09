package com.unionx.wantuo.utils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class OpExcel {
	 public void getMyXLS() throws Exception {
//	      ArrayList<Map<String,Object>> xlsMapList = new ArrayList<Map<String,Object>>();
	        File xlsOrxlsxFile = new File("D:\\type.xlsx");
	        if(!xlsOrxlsxFile.exists()){
	            return ;
	        }
	        try {
	            Workbook wb = WorkbookFactory.create(xlsOrxlsxFile);
	            int sheetNum = wb.getNumberOfSheets();
	            Sheet sheet = null;
	            for(int sheetIndex = 0;sheetIndex<sheetNum;sheetIndex++){//����sheet(index 0��ʼ)
	                System.out.println("sheet:"+sheetIndex);
	                sheet = wb.getSheetAt(sheetIndex);
	                Row row = null;
	                int firstRowNum = sheet.getFirstRowNum();
	                int lastRowNum = sheet.getLastRowNum();
	                String str = "";
	                for (int rowIndex = firstRowNum;rowIndex<=lastRowNum;rowIndex++ ) {//����row(�� 0��ʼ)
	                    row = sheet.getRow(rowIndex);
	                    if(null != row){
	                        int firstCellNum = row.getFirstCellNum();
	                        int lastCellNum = row.getLastCellNum();
	                        for (int cellIndex = firstCellNum; cellIndex < lastCellNum; cellIndex++) {//����cell���� 0��ʼ��
	                        	Cell cell = row.getCell(cellIndex, Row.RETURN_BLANK_AS_NULL);
	                            if (null != cell) {
	                                Object cellValue = null;//cellValue��ֵ
	                                switch (cell.getCellType()) {
	                                case Cell.CELL_TYPE_STRING:
	                                    System.out.println(cell.getRichStringCellValue()
	                                            .getString());
	                                    cellValue = cell.getRichStringCellValue()
	                                            .getString();
	                                    break;
	                                case Cell.CELL_TYPE_NUMERIC:
	                                    if (DateUtil.isCellDateFormatted(cell)) {
	                                        System.out.println(cell.getDateCellValue());
	                                        cellValue= cell.getDateCellValue();
	                                        //TODO ���԰����ڸ�ʽת��
	                                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	                                        String time = formatter.format(cellValue);
	                                        System.out.println("formater time:"+time);
	                                    } else {
	                                        System.out.println(cell.getNumericCellValue());
	                                        cellValue=cell.getNumericCellValue();
	                                    }
	                                    break;
	                                case Cell.CELL_TYPE_BOOLEAN:
	                                    System.out.println(cell.getBooleanCellValue());
	                                    cellValue = cell.getBooleanCellValue();
	                                    break;
	                                case Cell.CELL_TYPE_FORMULA:
	                                    System.out.println(cell.getCellFormula());
	                                    cellValue = cell.getCellFormula();
	                                    break;
	                                default:
	                                    System.out.println("not find match type.");
	                                }
	                                System.out.println("value:"+cellValue);
	                                str+=cellValue+",";
	                            } else {
	                                //TODO cell is null �� *** �������
	                               str+="*"+","; 
	                            }
	                        }//end cell
	                    }else{
	                        //TODO row is null
	                    }
	                    str="";
	                }//end row
	            }//end sheet
	        }  catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	public static void main(String[] args) {
	}
}
