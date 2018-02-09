<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link href="<%=basePath %>css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>css/style.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath %>lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="<%=basePath %>lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>lib/layer/1.9.3/layer.js"></script>
    <script type="text/javascript" src="<%=basePath %>lib/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=basePath %>lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/H-ui.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/H-ui.admin.js"></script>
    <title></title>
</head>
<script type="text/javascript">
	var basePath='<%=basePath%>';
</script>
<script type="text/javascript" src="<%=basePath%>js/xitongchaxun.js"></script>
<body>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>机构管理  <span class="c-gray en">&gt;</span> 机构信息  <a class="btn btn-success radius r mr-20" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <div class="pd-20">
        <div class="text-c">
            <div class="textbox">
                <ul>
                    <li>
                        <label>管理员账号</label><input type="text" value="" name="login_accounts" id="login_accounts"/></li>
                	<li>
                        <button type="submit" class="btn btn-success radius" id="Button1" name="" onclick="query('select')"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
                    </li>
                </ul>
            </div>
        </div>
        <div class="claer"></div>
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="l">
               <a  onclick="deleteById()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
               <a class="btn btn-primary radius" onclick="article_add('添加','xitong-add.jsp','800','600')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加</a>  
            </span><span class="CountSize" style="float:right"></span>
        </div>
        <div class="mt-20">
            <div>
                <table class="table table-border table-bordered table-bg table-hover" id="table1">
                    <thead>
                        <tr class="text-c">
                            <th width="25">
                                <input type="checkbox" name="" value=""></th>
                            <th width="120">管理员账号</th>
                            <th width="100">管理员姓名</th>
                            <th width="150">最后一次登陆时间</th>
                           <th width="120">基本操作</th>
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
    <script type="text/javascript">
        $('.table-sort').dataTable({
            "aaSorting": [[1, "desc"]],//默认第几个排序
            "bStateSave": true,//状态保存
            "aoColumnDefs": [
              //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
              { "orderable": false, "aTargets": [0, 8] }// 制定列不参与排序
            ]
        });
		 /*
		 -添加*/
        function article_add(title, url, w, h) {
           layer_show(title,url,w,h);	
        }
        /*-编辑*/
        function article_edit(title, url, id, w, h) {
        	 $("#table1 tr").click(function(){
        		 $(this).children().children().first().attr("checked", true);
        	 });
            var index = layer.open({
                type: 2,
                title: title,
                content: url
            });
            layer.full(index);
        }
    </script>
</body>
</html>
