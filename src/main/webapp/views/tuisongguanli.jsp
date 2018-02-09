<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link href="<%=basePath %>css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>css/H-ui.admin.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath %>lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/H-ui.js"></script> 
	<script type="text/javascript" src="<%=basePath %>js/H-ui.admin.js"></script>
    <title></title>
    <script type="text/javascript">
    var basePath = '<%=basePath%>';
    </script>
</head>
<body>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>推送管理 <a class="btn btn-success radius r mr-20" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a></nav>
   <div class="pd-20">
     <div class="pushBox"><h2>发送对象：</h2>
     <ul>
      <li><input type="radio" name="radio" value="1"/>所有机构</li>
      <li><input type="radio" name="radio" value="2"/>所有家长</li>
      <li><input type="radio" name="radio" value="3"/>所有用户</li>
     </ul>
     <h3>推送内容：</h3>
     <div class="textArea">
      <textarea id="note" rows="20" cols="200"></textarea>
     </div>
     <div class="row cl mTop">
     
      <button onClick="article_save_submit();" class="btn btn-primary radius" type="submit"> 发送</button>
			   <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;清空&nbsp;&nbsp;</button>
			
		</div>
     </div>
     
   </div>
</body>
<script type="text/javascript" src="<%=basePath%>js/tuisong.js"></script>
</html>
