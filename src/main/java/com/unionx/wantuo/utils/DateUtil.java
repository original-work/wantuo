package com.unionx.wantuo.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtil {
	public String dateTranstoStringShort(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
    public String dateTranstoStringlLong(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return sdf.format(new Date());
	}
    public Date StringToDate(String  dateStr) throws ParseException{
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	Date date = formatter.parse(dateStr);
		return date;  
    }
}
