﻿<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
    
<style type="text/css">
.label-success{ cursor:pointer;}
.td-manage{ overflow:hidden;word-wrap:break-word;}
</style>
</head>
<script type="text/javascript">
  	 var basePath = '<%=basePath%>';
  	 var pageSize = 10;
</script>
<body>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>机构管理 
     <span class="c-gray en">&gt;</span> 机构信息 
      <a class="btn btn-success radius r mr-20" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新">
    <i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="pd-20">
        <div class="text-c">
            <div class="textbox">
                <ul>
                   	<li>
                       	<label>机构账号 </label><input id="loginAccounts" type="text" value="" />
                       </li>
                   	<li>
                       	<label>机构名称 </label><input id="organization" type="text" value="" />
                       </li>
                   	<li>
                        <label>审批状态 </label>
                        <select id="check">
                        	<option value="">请选择</option>
                        	<option value="1">未审批</option>
                        	<option value="2">已审批</option>
                        </select>
                       </li>
                   	<!-- <li>
                        <label>生效状态 </label>
                        <select id="warranty">
                        	<option value="">请选择</option>
                        	<option value="1">失效</option>
                        	<option value="2">生效</option>
                        </select>
                      </li> -->
                	<li>
                        <button type="button" onclick="queryByCondition()" class="btn btn-success radius" id="Button1" name=""><i class="Hui-iconfont">&#xe665;</i> 查询</button>
                    </li>
                </ul>
            </div>
        </div>
        <div class="claer"></div>
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="l">
               <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
                <a class="btn btn-primary radius" onclick="article_add('添加','jigou_add.jsp','1000','700')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加</a>
               <a href="javascript:;" onclick="article_add('导入','jigou_tolead.jsp','800','400')" class="btn btn-warning radius"><i class="Hui-iconfont">&#xe644;</i> 导入</a>
               <a class="btn btn-warning radius" id="dingwei" name="dingwei" onclick="dingwei()">定位</a>
            </span><span class="r">共有数据：<strong id="totalSize">0</strong> 条</span>
        </div>
        <div class="mt-20">
            <div class="tableBox">
                <table class="table table-border table-bordered table-bg table-hover " id="table1">
                    <thead>
                        <tr class="text-c">
                            <th width="25">
                                <input type="checkbox" name="" value=""></th>
                            <th width="120">机构账号</th>
                            <th width="100">机构名称</th>
                            <th width="150">机构全称</th>
                            <th width="120">省市</th>
                            <th width="120">区县</th>
                            <th width="200">具体地址</th>
                            <th width="120">性质</th>
                            <th width="100">联系人</th>
                            <th width="120">联系方式</th>
                            <th width="60">定位</th>
                            <th width="120">认证状态</th>
                            <th width="170">审批状态</th>
                            <th width="120">授权状态</th>
                            <th width="90">操作</th>
                        </tr>
                    </thead>
                    <tbody id="jgtr">
                    </tbody>
                </table>
            </div>
           <div class="clear con-top"></div>
            <div id="DataTables_Table_0_info" class="dataTables_info" role="status" aria-live="polite">显示 <span id="pageTail">1</span>页，共 <span id="pageCount">1</span>页</div>
                       <div id="DataTables_Table_0_paginate" class="dataTables_paginate paging_simple_numbers">
                <a id="DataTables_Table_0_previous" class="paginate_button previous disabled" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0" onclick="flip('previous')">上一页</a>
	                <span id="currentPage">1</span>
                <a id="DataTables_Table_0_next" class="paginate_button next disabled" aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0" onclick="flip('next')">下一页</a>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="<%=basePath%>js/jigouguanli.js"></script>
</html>
