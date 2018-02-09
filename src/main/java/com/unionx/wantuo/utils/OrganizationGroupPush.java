package com.unionx.wantuo.utils;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
public class OrganizationGroupPush {
    
	/*正式*/
  static String appId = "53NxC9HIv27jt0EH4LCUm";
  static String appKey = "TqdTlxdaAg5CLvJ5a4rhq";
  static String masterSecret = "fwYVscOF099WyhqzWHYzX6";
	/*测试*/
  /*  static String appId = "mdQNCtTzlb70jzCqQI4u43";
    static String appKey = "JYd49ugwx762hImEBIdwp1";
    static String masterSecret = "rHKH54GBbw6AXoZjpqzMn9";*/
    static String url ="http://sdk.open.api.igexin.com/serviceex";
    /**
     * @Title: apnpush 
     * @Description: TODO ios群推推送  给机构 
	 * @param @param dtl devicetokenList
	 * @param @param body
	 * @param @param badge
     * @param @throws Exception 设定文件 
     * @author abner
     * @return void 返回类型 
     * @throws
     */
    public static void apnpush(List<String> dtl,String body,int badge,String payload) throws Exception {
        IGtPush push = new IGtPush(url, appKey, masterSecret);
        APNTemplate t = new APNTemplate();
        APNPayload apnpayload = new APNPayload();
        apnpayload.setSound("");//声音
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setTitle("爱晚拖");//标题
        alertMsg.setBody(body);//内容
        alertMsg.setTitleLocKey("爱晚拖");//标题
        alertMsg.setActionLocKey("爱晚拖");//来自
        apnpayload.setAlertMsg(alertMsg);
        apnpayload.setBadge(badge);
        apnpayload.addCustomMsg("payload",payload);
        t.setAPNInfo(apnpayload);  
        ListMessage message = new ListMessage();
        message.setData(t);
        String contentId = push.getAPNContentId(appId, message);
        System.out.println(contentId);
//        List<String> dtl = new ArrayList<String>();
//        dtl.add(devicetoken);
//        dtl.add(devicetoken);
//        dtl.add(devicetoken1);
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        IPushResult ret = push.pushAPNMessageToList(appId, contentId, dtl);
        System.out.println(ret.getResponse());
     }
    
    
	public static void main(String args[]) {
		try {
		List<String> dtl = new ArrayList<String>();
		dtl.add("94a6c2985f0e6b2d33bf34a8d65a51c8e5c0eda962943e4831c84ce5d43b02d0");
			apnpush(dtl, appId, 100000000, appId);
		} catch (Exception e) {
		}
	}
 }