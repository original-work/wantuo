package com.unionx.wantuo.utils;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;

public class PatriarchGerenPush {

	/*正式*/
  static String appId = "atuvKWWvWf5mYVW4Jydee6";
  static String appKey = "pqZAzdXdQ597SusN0Z1Ue6";
  static String masterSecret = "SHV1Zv4Ggu6goXGLR1rJu6";
	/*测试*/
	/*static String appId = "dJgPof7tMv7wgN87KDUVF1";
	static String appKey = "0YSXJ34h2X6QQ9O37BsZM2";
	static String masterSecret = "YL61aWccZZ5nb6m0SM7HZ8";*/
	static String url = "http://sdk.open.api.igexin.com/serviceex";
	/**
	 * @Title: apnpush 
	 * @Description: TODO ios 个推个家长
	 * @param @param dt devicetoken
	 * @param @param body
	 * @param @param badge
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