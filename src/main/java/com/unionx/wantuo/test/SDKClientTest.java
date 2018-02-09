package com.unionx.wantuo.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.unionx.wantuo.test.bean.Mo;
import com.unionx.wantuo.test.bean.StatusReport;
import com.unionx.wantuo.test.httpclient.SDKHttpClient;

/**
 * 
 * @项目名称：EmayClientForHttpV1.0?
 * @类描述：??
 * @创建人：HL.W??
 * @创建时间：2015-9-9 下午5:29:46??
 * @修改人：HL.W??
 * @修改时间：2015-9-9 下午5:29:46??
 * @修改备注：
 */
public class SDKClientTest {

    public static String sn = "0SDK-EBB-6699-REQML";//软件序列号,请通过亿美销售人员获取
    public static String key = "4912";// 序列号首次激活时自己设定
    public static String password = "243485";// 密码,请通过亿美销售人员获取
    public static String baseUrl = "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/";
   public static void main(String[] args) throws UnsupportedEncodingException {
		String mdn = "18210890192";
		String message = "【亿美软通】您好，您的验证码是" + System.currentTimeMillis();
		message = URLEncoder.encode(message, "UTF-8");
		String code = "888";
		long seqId = System.currentTimeMillis();
		String param = "cdkey=" + sn + "&password=" + 243485 + "&phone=" + mdn + "&message=" + message + "&addserial=" + code + "&seqid=" + seqId;
		String url = baseUrl + "sendsms.action";
		String ret = SDKHttpClient.sendSMS(url,param);
		System.out.println("发送结果:" + ret);
			
	}

    public static void main2(String[] args) {
	try {
	    StartMenu();
	    while (true) {
		System.out.println("请输入序号进行操作");
		byte[] command = new byte[4];
		System.in.read(command);
		int operate = 0;
		try {
		    String commandString = new String(command);
		    commandString = commandString.replaceAll("\r\n", "").trim();
		    operate = Integer.parseInt(commandString);
		} catch (Exception e) {
		    System.out.println("命令输入错误！！！");
		}
		String param = "";
		switch (operate) {
		case 1:
			param = "cdkey="+ sn +"&password="+ password;
		    String url = baseUrl + "regist.action";
		    String ret = SDKHttpClient.regist(url, param);
		    System.out.println("注册结果:" + ret);
		    break;
		case 2:
		    param = "cdkey=" + sn + "&password=" + key;
		    url = baseUrl + "querybalance.action";
		    String balance = SDKHttpClient.getBalance(url, param);
		    System.out.println("当前余额:" + balance);
		    break;
		case 3:
		    String mdn = "18210890192";
		    String message = "测试成功";
		    message = URLEncoder.encode(message, "UTF-8");
		    String code = "888";
		    long seqId = System.currentTimeMillis();
		    param = "cdkey=" + sn + "&password=" + key + "&phone=" + mdn + "&message=" + message + "&addserial=" + code + "&seqid=" + seqId;
		    url = baseUrl + "sendtimesms.action";
		    ret = SDKHttpClient.sendSMS(url, param);
		    System.out.println("发送结果:" + ret);

		    break;
		case 4:
		    param = "cdkey=" + sn + "&password=" + key;
		    url = baseUrl + "getmo.action";
		    List<Mo> mos = SDKHttpClient.getMos(url, sn, key);
		    System.out.println("获取上行数量：" + mos.size());
		    break;
		case 5:
		    param = "cdkey=" + sn + "&password=" + key;
		    url = baseUrl + "getreport.action";
		    List<StatusReport> srs = SDKHttpClient.getReports(url, sn, key);
		    System.out.println("获取报告数量：" + srs.size());
		    break;
		case 6:
		    System.exit(0);
		default:
		    System.out.println("没有该命令 " + operate);
		    break;
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static void StartMenu() {
	int i = 1;
	System.out.println(i + "、激活序列号,初次使用、已注销后再次使用时调用该方法.");
	i += 1;
	System.out.println(i + "、余额查询");
	i += 1;
	System.out.println(i + "、发送带有信息ID的短信,可供查询状态报告");
	i += 1;
	System.out.println(i + "、获取上行短信");
	i += 1;
	System.out.println(i + "、获得下行短信状态报告");
	i += 1;
	System.out.println(i + "、关闭程序");
    }
}
