package com.unionx.wantuo.utils;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.unionx.wantuo.model.Manage;
public class LoginFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		    HttpServletRequest httprequest = (HttpServletRequest)request;
		    HttpServletResponse httpresponse = (HttpServletResponse)response;
		    HttpSession session = httprequest.getSession();
		    Manage userInfo =(Manage)session.getAttribute("userinfo");
		    String uri = httprequest.getRequestURI();
            if(uri.contains("/css/")){
		    	chain.doFilter(request, response);	
		    }else if(uri.contains("/myjs/")){
		    	chain.doFilter(request, response);	
		    }else if(uri.contains("/lib/")){
		    	chain.doFilter(request, response);
		    }else if(uri.contains("/js/")){
		    	chain.doFilter(request, response);
		    }else if(uri.contains("/images/")){
		    	chain.doFilter(request, response);
		    }else if(uri.contains("/login.jsp")){
		    	chain.doFilter(request, response);
		    }else  if(uri.contains("/manageLogin")){
		    	chain.doFilter(request, response);
		    }else if(uri.contains("/fenxiang.html")){
		    	chain.doFilter(request, response);
		    }else if(uri.contains("/phoneImg.html")){
		    	chain.doFilter(request, response);
		    }else if(uri.contains("/phone")){
		    	chain.doFilter(request, response);
		    }else{
		    	if(userInfo==null){
		    		httprequest.setAttribute("info", "登陆!!");
		    		httprequest.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(httprequest, httpresponse);
		    	}else{
		    		chain.doFilter(request, response);
		    	}
		    }
	}
	@Override
	public void destroy() {}
}
