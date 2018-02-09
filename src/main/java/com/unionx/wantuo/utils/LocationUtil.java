package com.unionx.wantuo.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class LocationUtil {

	private static final String BAIDU_APP_KEY = "42b8ececa9cd6fe72ae4cddd77c0da5d";

	/**
	 * @Title: getLatitude 
	 * @Description: TODO(根据地址获取坐标) 
	 * @param @param address
	 * @author abner
	 * @return Map<String,String> 返回类型 
	 */
	public static Map<String, String> getLatitude(String address) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			address = URLEncoder.encode(address, "UTF-8");
			URL resjson = new URL("http://api.map.baidu.com/geocoder?address="
					+ address + "&output=json&key=" + BAIDU_APP_KEY);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					resjson.openStream()));
			String res;
			StringBuilder sb = new StringBuilder("");
			while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			in.close();
			String str = sb.toString();
//			System.out.println("return json:" + str);
			if (str != null && !str.equals("")) {
				int lngStart = str.indexOf("lng\":");
				int lngEnd = str.indexOf(",\"lat");
				int latEnd = str.indexOf("},\"precise");
				if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
					String lng = str.substring(lngStart + 5, lngEnd);
					String lat = str.substring(lngEnd + 7, latEnd);
					map.put("status",ConstantUtil.PHONE_SATATUS_SUCCESS);
					map.put("lng", lng);
					map.put("lat", lat);
				}
			}
		} catch (Exception e) {
			map.put("status",ConstantUtil.PHONE_SATATUS_ERROR);
		}
		return map;
	}

	public static void main(String args[]) {
		Map<String, String> map = LocationUtil.getLatitude("北京大学");
		if(ConstantUtil.PHONE_SATATUS_SUCCESS.equals(map.get("status"))){
			System.out.println(map.get("lng"));//y
			System.out.println(map.get("lat"));//x
		}
	}
}