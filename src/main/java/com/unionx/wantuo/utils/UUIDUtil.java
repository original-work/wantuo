package com.unionx.wantuo.utils;
import java.util.UUID;
public class UUIDUtil {
	   public UUIDUtil() {  
	    }  
	    public static String getUUID() {  
	       UUID uuid = UUID.randomUUID();  
	        String str = uuid.toString();  
	       String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
	       String[] ss= temp.split("-"); 
	      /* return str+"-"+temp;  */
	      return ss[0];
	    }  
	    //获得指定数量的UUID  
	    public static String[] getUUID(int number) {  
	        if (number < 1) {  
	            return null;  
	        }  
	        String[] ss = new String[number];  
	        for (int i = 0; i < number; i++) {  
	           ss[i] = getUUID();  
	        }  
	        return ss;  
	    }  
	}  
