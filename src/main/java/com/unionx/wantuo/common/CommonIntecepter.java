package com.unionx.wantuo.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.unionx.wantuo.utils.AesUtil;


public class CommonIntecepter implements HandlerInterceptor  {
    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String   url  = request.getScheme()+"://"; //请求协议 http 或 https    
		url+=request.getHeader("host");  // 请求服务器    
		url+=request.getRequestURI(); 
		// 工程名      
        String str=request.getQueryString();
        if(str.substring(0, 7).equals("content")){
        	 String[] decrptQuery= str.split("=");
     		 String decrptQuerystring=AesUtil.Decrypt(decrptQuery[1],"q1w2e3r4t5y6u7i8");
     		 String requestUrlAndParams=url+"?"+decrptQuerystring;
     		   // request.getRequestDispatcher(requestUrlAndParams).forward(request, response);
     	    	response.sendRedirect(requestUrlAndParams);
     	
        }else{        	
        	return true;
        }
        
        return true;
        
	}
        

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}


}
