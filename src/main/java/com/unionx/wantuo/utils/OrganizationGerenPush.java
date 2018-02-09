package com.unionx.wantuo.utils;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
public class OrganizationGerenPush {
    
	/*正式*/
	static String appId = "53NxC9HIv27jt0EH4LCUm";
    static String appKey = "TqdTlxdaAg5CLvJ5a4rhq";
	static String masterSecret = "fwYVscOF099WyhqzWHYzX6";
	/*测试*/
   /* static String appId = "mdQNCtTzlb70jzCqQI4u43";
    static String appKey = "JYd49ugwx762hImEBIdwp1";
    static String masterSecret = "rHKH54GBbw6AXoZjpqzMn9";*/
    static String url ="http://sdk.open.api.igexin.com/serviceex";
    /**
     * @Title: apnpush 
     * @Description: TODO ios个推  给机构 
     * @param @param dtl
     * @param @throws Exception 设定文件 
     * @author abner
     * @return void 返回类型 
     * @throws
     */
	public static void apnpush(String dt,String body, int badge,String payload) throws Exception {
		IGtPush push = new IGtPush(url, appKey, masterSecret);
		APNTemplate t = new APNTemplate();
		APNPayload apnpayload = new APNPayload();
		apnpayload.setSound("");
		APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
		alertMsg.setTitle("爱晚拖");// 标题
		alertMsg.setBody(body);// 内容
		alertMsg.setTitleLocKey("爱晚拖");// 标题
		alertMsg.setActionLocKey("爱晚拖");// 来自
		apnpayload.setAlertMsg(alertMsg);
		apnpayload.setBadge(badge);
        apnpayload.addCustomMsg("payload",payload);
		t.setAPNInfo(apnpayload);
		SingleMessage sm = new SingleMessage();
		sm.setData(t);
		IPushResult ret0 = push.pushAPNMessageToSingle(appId, dt, sm);
		System.out.println(ret0.getResponse());
	}
}