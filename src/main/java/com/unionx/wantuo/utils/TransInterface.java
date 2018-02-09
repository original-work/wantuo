package com.unionx.wantuo.utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class TransInterface {
	public static String getMsg(String jsonStr,String path){
		URL url = null;
		HttpURLConnection httpurlconnection = null;
		String str=null;
		try{
			url = new URL(path);
			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setRequestMethod("POST");
			String userid = jsonStr;
			httpurlconnection.getOutputStream().write(userid.getBytes());
			httpurlconnection.getOutputStream().flush();
			httpurlconnection.getOutputStream().close();
			int code = httpurlconnection.getResponseCode();
			//http状态码，200正常
			System.out.println("code   " + code);
			java.io.InputStream in= httpurlconnection .getInputStream();
			java.io.BufferedReader breader =new BufferedReader(new InputStreamReader(in , "utf-8"));
			str=breader.readLine();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(httpurlconnection!=null)
				httpurlconnection.disconnect();
		}
		return str;
	}
	public static String convertStreamToString(InputStream is)
	  {
		 StringBuilder sb = new StringBuilder();
	    try
	    {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
	 	    String line = null;
	      while ((line = reader.readLine()) != null) {
	        sb.append(line + "\n");
	      }
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      try
	      {
	        is.close();
	      }
	      catch (Exception e1)
	      {
	        e1.printStackTrace();
	      }
	    }
	    finally
	    {
	      try
	      {
	        is.close();
	      }
	      catch (Exception e)
	      {
	        e.printStackTrace();
	      }
	    }
	    return sb.toString();
	  }
	public static void download(HttpServletRequest request, HttpServletResponse response, String storeName,
			String contentType) throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");  
	    BufferedInputStream bis = null;  
	    BufferedOutputStream bos = null;  
	    //获取项目根目录
	    String ctxPath = request.getSession().getServletContext()  
	        .getRealPath("");  
	    //获取下载文件路径
	    String downLoadPath = ctxPath+"/uploads/"+ storeName;  
	  System.out.println(ctxPath+"!!!");
	    //获取文件的长度
	    long fileLength = new File(downLoadPath).length();  
	    //设置文件输出类型
	    response.setContentType("application/octet-stream");  
	    response.setHeader("Content-disposition", "attachment; filename="  
	        + new String(storeName.getBytes("utf-8"), "ISO8859-1")); 
	    //设置输出长度
	    response.setHeader("Content-Length", String.valueOf(fileLength));  
	    //获取输入流
	    bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
	    //输出流
	    bos = new BufferedOutputStream(response.getOutputStream());  
	    byte[] buff = new byte[2048];  
	    int bytesRead;  
	    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	      bos.write(buff, 0, bytesRead);  
	    }  
	    //关闭流
	    bis.close();  
	    bos.close();  
	  }  
}
