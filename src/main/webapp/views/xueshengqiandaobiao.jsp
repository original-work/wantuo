<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>机构学生签到报表</title>
    <link href="<%=basePath%>css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>css/paihangbang.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=basePath%>lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jedate.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/H-ui.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/H-ui.admin.js"></script>
        <script type="text/javascript">
		var basePath='<%=basePath%>';

		function Export(){
			window.location.href=basePath+"/trace/ExportStudent.do?organizationName="+$("#organizationName").val()+"&page="+parseInt($("#currentPage").html())+"&rows="+parseInt($("#pageSize").val())+"&className="+$("#className").val();
		}
		function myPrint(){
			$("#stutdentTable").attr("border","1px");
		    var newWindow=window.open("打印窗口","_blank");
		    var table=document.getElementById("studentReport");
		    var docStr =table.innerHTML;
		    newWindow.document.write(docStr);
		    newWindow.document.close();
		    newWindow.print();
		    $("#stutdentTable").attr("border","0px");
		    newWindow.close();
		}
	</script>
	
</head>
<body>
	<nav class="breadcrumb">
    	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 机构学生签到报表 <a class="btn btn-success radius r mr-20" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a></nav>
        <div class="pd-20">
        <div class="text-c">
            <div class="textbox">
                <ul>
                	<li>
                        <label>机构名称 </label>
                       <input type="text" id="organizationName" name="organizationName" />
                    </li>
                    <li>
                    	<label>班级名称</label>
                        <input type="text"  value="" id="className" name="className"/>
                    </li>
                     <li>
                    	<label>签到开始时间</label>
                        <input class="datainp" id="signinDateBegin" name="signinDateBegin" type="text" placeholder="请选择"/>
                    </li>
                    <li>
                    	<label>签到结束时间</label>
                        <input class="datainp" id="signinDateEnd" name="signinDateEnd" type="text" placeholder="请选择"/>
                    </li>
                	<li>
                         <button type="submit" class="btn btn-success radius" id="Button1" name="" onclick="query('select')"> 查询</button>
                    </li>
                </ul>
            </div>

        </div>
        <div class="claer"></div>
        <div class="cl pd-5 bg-1 bk-gray mt-20">
        	<span class="l">
                <a class="btn btn-primary radius" onclick="Export()"> 导 出 </a>
                <a class="btn btn-primary radius" onclick="myPrint()"> 打 印 </a>
            </span>
            <span class="CountSize" style="float:right"></span>
        </div>
        <div class="mt-20">
            <div id="studentReport">
                <table class="table table-border table-bordered table-bg table-hover " id="stutdentTable">
                    <thead>
                        <tr class="text-c">
                            <th>机构全	称</th>
                            <th>班级名称</th>
                            <th>学生姓名</th>
                            <th>签到时间</th>
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
<script type="text/javascript" src="<%=basePath %>js/xueshengqiandao.js"></script>
<script type="text/javascript">
    jeDate({
		dateCell:"#signinDateBegin",
		format:"YYYY-MM-DD",
		isinitVal:false,
		isTime:true, //isClear:false,
		minDate:"2014-09-19 00:00",
		okfun:function(val){alert(val)}
	});
    jeDate({
		dateCell:"#signinDateEnd",
		format:"YYYY-MM-DD",
		isinitVal:false,
		isTime:true, //isClear:false,
		minDate:"2014-09-19 00:00",
		okfun:function(val){alert(val)}
	});
</script>
</html>
