package com.unionx.wantuo.utils;
public class StringUtil {
	public static String removeFirst(String str){
	//[{"message":"新的token","token":"06424332FFFFFFB234B172EF48D30399","code":0,"clientId":"06413EACFFFFFFB234B172EF39C4B918"}]
		String s = str.substring(1, str.length()-1);
		return s;
	}
public static void main(String[] args) {
	System.out.println(StringUtil.removeFirst("[{nihao:aa}]"));
}
}
