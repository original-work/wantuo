package com.unionx.wantuo.utils;
import java.io.FileInputStream;
import java.io.InputStream;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
public class ExcelOperater {
    public static void main(String[] args)    
    {   
        jxl.Workbook readwb = null;   
        try    
        {   
            //构建Workbook对象, 只读Workbook对象   
            //直接从本地文件创建Workbook   
            InputStream instream = new FileInputStream("E:/sqllite.xls");   
            readwb = Workbook.getWorkbook(instream);   
            //Sheet的下标是从0开始   
            //获取第一张Sheet表   
            Sheet readsheet = readwb.getSheet(0);   
            //获取Sheet表中所包含的总列数   
            int rsColumns = readsheet.getColumns();   
            //获取Sheet表中所包含的总行数   
            int rsRows = readsheet.getRows();   
            //获取指定单元格的对象引用   
            for (int i = 0; i < rsRows; i++)   
            {   
                for (int j = 0; j < rsColumns; j++)   
                {   
                    Cell cell = readsheet.getCell(j, i);   
                    System.out.print(cell.getContents() + " ");   
                }   
                System.out.println();   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            readwb.close();   
        }   
}   
}
