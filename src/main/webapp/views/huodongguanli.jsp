﻿<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link href="<%=basePath%>css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=basePath%>lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>lib/layer/1.9.3/layer.js"></script>
    <script type="text/javascript" src="<%=basePath%>lib/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=basePath%>lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/H-ui.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/H-ui.admin.js"></script>
   <title></title>
	<script type="text/javascript">
		var basePath='<%=basePath%>';
		
	</script>
</head>
<body>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>学校管理  <a class="btn btn-success radius r mr-20" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="pd-20">
        <div class="text-c">
            <div class="textbox">
            	<form action="<%=basePath%>activity/QueryList" method="get" accept-charset="utf-8" onsubmit="document.charset='utf-8';">
	            	<ul>
	                    <li>
	                        <label>活动名称 </label><input type="text" name="activityName" id="activityName"/></li>
	                    <li>
	                	<li>
	                        <button type="submit" class="btn btn-success radius" id="Button1" name=""><i class="Hui-iconfont">&#xe665;</i>查询</button>
	                    </li>
	                </ul>
            	</form>
            </div>
        </div>
        <div class="claer"></div>
        <div class="cl pd-5 bg-1 bk-gray mt-20">
        	<span class="count">共有数据： ${activityList.size()}条</span>
        </div>
        <div class="mt-20">
            <div>
                <table class="table table-border table-bordered table-bg table-hover ">
                    <thead>
                        <tr class="text-c">
                            <th width="10%">位置</th>
                            <th width="30%">活动名称</th>
                            <th width="30%">创建日期</th>
                            <th width="30%">操作</th>
                        </tr>
                    </thead>
                    <tbody id="activityInfo">
                    	<c:forEach var="activity" items="${activityList}">
                    		<tr class="text-c">
	                           	<td>${activity.location }</td>
	                           	<td>${activity.activityName}</td>
	                           
	                           	<td>${fn:substring(activity.createDate, 0, 16)}</td>
	                    		<td class="f-14 td-manage">
	                            	<a style="text-decoration:none" class="ml-5" href="<%=basePath%>activity/findByOne?id=${activity.id}&num=2" title="编辑">编辑</a>
	                                <a style="text-decoration:none" class="ml-5" href="<%=basePath%>activity/findByOne?id=${activity.id}&num=1" title="查看">查看</a>
	                            </td>
                    		</tr>
                    	</c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
