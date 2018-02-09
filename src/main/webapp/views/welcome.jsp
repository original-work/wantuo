<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>我的桌面</title>
<style type="text/css">
.suc-title{ font-size:28px; text-align:center; font-weight:200; color:#ffa66c;}
.bgckBg{ text-align:center; display:block; margin-top:3%;}
</style>
<script>
/* $.ajaxSetup({
	    type: 'POST',
	    complete: function(xhr,status) {
	    	alert();
        var sessionStatus = xhr.getResponseHeader('sessionstatus');
	         if(sessionStatus == 'timeout') {
	             var top = getTopWinow();
	            var yes = confirm('由于您长时间没有操作, session已过期, 请重新登录.');
	             if (yes) {
	                 top.location.href = '/skynk/index.html';            
	             }
	         }
	     }
	 }); */
</script>
</head>
<body>
<div class="pd-20" style="padding-top:20px;">
  <p class="f-20 text-success suc-title">欢迎登录爱晚托后台管理系统</p>
   <div class="bgckBg"><img src="<%=basePath%>images/loginimg.jpg"/></div>
</div>
<script type="text/javascript" src="<%=basePath%>lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/H-ui.js"></script>
</body>
</html>