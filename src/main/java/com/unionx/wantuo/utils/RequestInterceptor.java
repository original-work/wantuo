package com.unionx.wantuo.utils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
public class RequestInterceptor extends HandlerInterceptorAdapter {
	// private static final String[] IGNORE_URI = {"/login.jsp", "/Login/","/lib","/css","/js","/images","/image"};
	@Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  
            Object arg2) throws Exception {  
        String url = request.getRequestURL().toString();
        System.out.println(url);
        response.sendRedirect("/wantuo/login.jsp");
        /*     for (String s : IGNORE_URI) {
            if (url.contains(s)) {
            	 System.out.println("zouni");
                return true;
            }else{
            }
        }
        UserServiceDTD usd = (UserServiceDTD) request.getSession().getAttribute("user");
        if(usd!=null){
        	return true;
        }
        response.sendRedirect("/wantuo/wantuo/login.jsp");*/
    	 return false;
    }  
    @Override  
    public void afterCompletion(HttpServletRequest arg0,  
            HttpServletResponse arg1, Object arg2, Exception arg3)  
            throws Exception {  
    }  
    @Override  
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,  
            Object arg2, ModelAndView arg3) throws Exception {  
    }  
}
