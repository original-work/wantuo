<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'pingjiaguanli.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
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
</head>
<script type="text/javascript">
	var basePath='<%=basePath%>';
</script>
<script type="text/javascript" src="<%=basePath%>js/pingjiaguanli.js"></script>
<body>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>评价管理  <a class="btn btn-success radius r mr-20" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="pd-20">
        <div class="text-c">
            <div class="textbox">
                <ul>
                    <li>
                        <label>机构账号</label><input type="text" value="" name="organizationAccounts" id="organizationAccounts"/></li>
                        <li>
                        <label>评价人名称</label><input type="text" value="" name="evaluatePerson" id="evaluatePerson"/></li>
                  	<li>
                        <label>评价星级</label>
	                        <select name="evaluate" id="evaluate">
	                        	<option value="5">五星</option>
	                            <option value="4">四星</option>
	                            <option value="3">三星</option>
	                            <option value="2">二星</option>
	                            <option value="1">一星</option>
	                        </select>
                       	</li>     
                	<li>
                        <button type="button" class="btn btn-success radius" onclick=" query('select')"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
                    </li>
                </ul>
            </div>
        </div>
        <div class="claer"></div>
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="l">
               <a class="btn btn-danger radius" onclick="deleteByIdStr()"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
            	</span><span class="count" style="float:right"></span>
        </div>
        <div class="mt-20">
            <div>
                <table class="table table-border table-bordered table-bg table-hover ">
                    <thead>
                        <tr class="text-c">
                            <th width="50">
                                <input type="checkbox" name="" value=""></th>
                            <th width="140">机构账号</th>
                            <th width="150">评价星级</th>
                            <th width="150">评价人</th>
                            <th width="300">评价内容</th>
                           <th width="140">基本操作</th>
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
</html>
