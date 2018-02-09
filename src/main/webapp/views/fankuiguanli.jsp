﻿<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>反馈管理 <a class="btn btn-success radius r mr-20" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="pd-20">
        <div class="text-c">
            <div class="textbox">
                <ul>
                    <li>
                        <label>家长帐号</label><input type="text" value="" name="loginAccounts" id="loginAccounts"/></li>
                <li>
                        <button type="submit" class="btn btn-success radius" id="Button1" name="" onclick="query('select')"> 查询</button>
                    </li>
                </ul>
            </div>

        </div>
        <div class="claer"></div>
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="l">
               <a href="javascript:;" onclick="deleteById()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
            </span><span class="CountSize" style="float:right"></span>
        </div>
        <div class="mt-20">
            <div>
                <table class="table table-border table-bordered table-bg table-hover ">
                    <thead>
                        <tr class="text-c">
                            <th width="25">
                                <input type="checkbox" name="" value=""></th>
                            <th width="100">反馈时间</th>
                            <th width="80">家长账号</th>
                            <th width="120">反馈内容</th>
                            <th width="90">联系方式</th>
                           <th width="100">基本操作</th>
                        </tr>
                    </thead>
                    <tbody id="tbody">
                    </tbody>
                </table>
            </div>
                         <div class="clear con-top"></div>
            <div id="DataTables_Table_0_info" class="dataTables_info" role="status" aria-live="polite">显示 <span id="pageTail">1</span>页，共 <span id="pageCount">1</span>页</div>
            <div id="DataTables_Table_0_paginate" class="dataTables_paginate paging_simple_numbers">
                <a id="DataTables_Table_0_previous" class="paginate_button previous disabled" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0" onclick="query('previous')">上一页</a>
                <span>
                    <a class="paginate_button current bgcurg" id="currentPage">1</a>
                </span>
                <input type="hidden" name="rows" id="pageSize" value=10>
                <a id="DataTables_Table_0_next" class="paginate_button next disabled" aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0" onclick="query('next')">下一页</a>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="<%=basePath %>js/fankuiguanli.js"></script>
</html>
