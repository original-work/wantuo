package com.unionx.wantuo.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import com.unionx.wantuo.utils.CommonUtils;

public class uploadTest {
	public static void main(String[] args) {
				
	}
	
	private boolean saveFile(HttpServletRequest request, MultipartFile file) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中  )
                String filePath = request.getSession().getServletContext()
                    .getRealPath("/") + "upload/" + file.getOriginalFilename();
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                // 转存文件
                file.transferTo(saveDir);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
	
	
	 public void filesUpload(MultipartFile[] files,HttpServletRequest request) {
	        if (files != null && files.length > 0) {
	            for (int i = 0; i < files.length; i++) {
	                MultipartFile file = files[i];
	                // 保存文件
	                saveFile(request, file);
	            }
	        }
	      /*  // 重定向
	        return "redirect:/upload/toUpload";*/
	    }
	 public boolean aa(String path,MultipartFile file){
		 try {
	            InputStream stream = file.getInputStream();
	            OutputStream bos = new FileOutputStream(path);
	            int bytesRead = 0;
	            byte[] buffer = new byte[8192];
	            while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
	                bos.write(buffer, 0, bytesRead);
	            }
	            bos.close();
	            stream.close();
	            return true;
	        } catch (FileNotFoundException e) {
	            return false;
	        } catch (IOException e) {
	            return false;
	        }
	 }
	 
	 
	 public static String FileUpload(String newFileName,MultipartFile filedata,HttpServletRequest request){
		 String picDir="";
		 String filePath="";
		 try {
			Properties ps=CommonUtils.getProperties();
			 picDir= ps.getProperty("filePath")+"/".trim();
			 String saveFilePath=picDir;
			 
			 File fileDir=new File(saveFilePath);
			 if(!fileDir.exists()){
				 fileDir.mkdirs();
			 }
			 FileOutputStream out=new FileOutputStream(saveFilePath+newFileName);
			 out.write(filedata.getBytes());
			 filePath=ps.getProperty("linuxPath")+"/".trim()+newFileName;
			 out.flush();
			 out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return filePath;
	 }
	 
	 
		
		/*@ResponseBody
		@RequestMapping(value="upload",method=RequestMethod.POST)
		public Rest upload(@RequestParam("file") MultipartFile[] files,HttpServletRequest request){
			Rest rest=new Rest();
			Map<Object, Object> map=new HashMap<Object,Object>();
			String filePath="";
	        if (files != null && files.length > 0) {
	            for (int i = 0; i < files.length; i++) {
	                MultipartFile file = files[i];
	                String fileName = file.getOriginalFilename();
	                String extensionName = fileName
	                        .substring(fileName.lastIndexOf(".") + 1);
	                String newFileName = String.valueOf(System.currentTimeMillis())
	                        + "." + extensionName;
	                filePath+=uploadTest.FileUpload(newFileName, file,request)+",";
	            }
	            map.put("filePath", filePath);
	        }
	        rest.setData(map);
			return rest;
		}*/
}
