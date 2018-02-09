package com.unionx.wantuo.utils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
public class TranStr {
	 public  static String changeCharset(String str) throws UnsupportedEncodingException{
		 return URLEncoder.encode(str, "utf-8");
	 }
}
