package com.unionx.wantuo.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class TimerUtil {
	 	public static String dateToString(Date date){
	 		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	 	    return time.format(date); 
	 	}
		public static long fromdatetodate(String now, String createtime) {
			Date d1=StringToDate(now);
			Date d2=StringToDate(createtime);
			long l=d1.getTime()-d2.getTime();
			return l;
		}
		public static Date StringToDate(String time){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			try {
				Date date = sdf.parse(time);
				return date;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}