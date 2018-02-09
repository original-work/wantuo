<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>排行榜</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>css/paihangbang.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
   
</head>
<script type="text/javascript">
	var basePath='<%=basePath%>';
	var pageSize = 10;
	function  paiming(obj){
		$("#paiming").attr("href",basePath+"organization/paiMingDetails?loginAccounts="+obj.val());
	}
</script>
<script type="text/javascript" src="<%=basePath%>lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/paihangbang.js"></script>
<body>
	<nav class="breadcrumb">
    	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 排行榜 <a class="btn btn-success radius r mr-20" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a></nav>
        <div class="cl pd-5 bg-1 bk-gray mt-20">
        	<span class="l">
                <a class="btn btn-primary radius"  id="paiming"> 排 名 </a>
            </span>
           	<span class="r">共有数据：<strong id="totalSize">0</strong> 条</span>
        </div>
        <div class="mt-20">
            <div>
              <form>
                <table class="table table-border table-bordered table-bg table-hover">
                    <thead>
                        <tr class="text-c">
                         	<th></th>
                            <th>排名</th>
                            <th>机构账号</th>
                            <th>机构名称</th>
                            <th>机构全称</th>
                            <th>详细地址</th>
                            <th>联系人</th>
                            <th>联系方式</th>
                            <th>星级</th>
                            <th>认证</th>
                            <th>授权状态</th>
                            <th>审核状态</th>
                        </tr>
                    </thead>
                    <tbody id="jgtr"></tbody>
                </table>
              </form>
            </div>
            <div class="clear con-top"></div>
            <div id="DataTables_Table_0_info" class="dataTables_info" role="status" aria-live="polite">显示 <span id="pageTail">1</span>页，共 <span id="pageCount">1</span>页</div>
                       <div id="DataTables_Table_0_paginate" class="dataTables_paginate paging_simple_numbers">
                <a id="DataTables_Table_0_previous" class="paginate_button previous disabled" aria-controls="DataTables_Table_0" data-dt-idx="0" tabindex="0" onclick="flip('previous')">上一页</a>
	                <span id="currentPage">1</span>
                <a id="DataTables_Table_0_next" class="paginate_button next disabled" aria-controls="DataTables_Table_0" data-dt-idx="2" tabindex="0" onclick="flip('next')">下一页</a>
            </div>

        </div>
</body>
</html>
