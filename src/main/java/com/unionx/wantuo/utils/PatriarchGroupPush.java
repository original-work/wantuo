package com.unionx.wantuo.utils;

import java.util.List;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
public class PatriarchGroupPush {
    
	/*正式*/
  static String appId = "atuvKWWvWf5mYVW4Jydee6";
  static String appKey = "pqZAzdXdQ597SusN0Z1Ue6";
  static String masterSecret = "SHV1Zv4Ggu6goXGLR1rJu6";
	/*测试*/
   /* static String appId = "dJgPof7tMv7wgN87KDUVF1";
    static String appKey = "0YSXJ34h2X6QQ9O37BsZM2";
    static String masterSecret = "YL61aWccZZ5nb6m0SM7HZ8";*/
    static String url ="http://sdk.open.api.igexin.com/serviceex";
    /**
     * @Title: apnpush 
     * @Description: TODO ios群推推送  给家长 
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
//       List<String> dtl = new ArrayList<String>();
//       dtl.add(devicetoken);
//       dtl.add(devicetoken);
//       dtl.add(devicetoken1);
       System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
       IPushResult ret = push.pushAPNMessageToList(appId, contentId, dtl);
       System.out.println(ret.getResponse());
    }
}