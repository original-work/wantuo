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
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,member-scalable=no" />
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
<title>添加用户</title>
<script type="text/javascript">
	var basePath='<%=basePath%>';
</script>
<script type="text/javascript" src="<%=basePath %>js/xitong-add.js"></script>
</head>
<body>
<div class="pd-20">
  <form id="form1" class="form form-horizontal">
    <div class="row cl">
      <label class="form-label col-3">管理员账号：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value=""  placeholder="只能输入数字和英文" id="login_accounts" name="login_accounts" datatype="*2-16" onblur="validationFomr(this.value,'phone')">
      </div>
      <div class="col-4"> </div>
    </div>
    <div class="row cl">
      <label class="form-label col-3">设置密码：</label>
      <div class="formControls col-5">
        <input type="password" class="input-text" value=""  placeholder="只能输入6-20个字母、数字、下划线  " id="password" name="password" datatype="*2-16" onblur="validationFomr(this.value,'password')">
      </div>
      <div class="col-4"> </div>
    </div>
      <div class="row cl">
      <label class="form-label col-3">确认密码：</label>
     
      <div class="formControls col-5">
        <input type="password" class="input-text" value="" placeholder="" id="readyPassword" name="readyPassword" datatype="*2-16" nullmsg="用户名不能为空">
      </div>
      <div class="col-4"> </div>
    </div>
      <div class="row cl">
      <label class="form-label col-3">管理员姓名：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value="" placeholder="" id="manage_name" name="manage_name" datatype="*2-16" nullmsg="用户名不能为空">
      </div>
      <div class="col-4"> </div>
    </div>
    <div class="row cl">
      <label class="form-label col-3"></label>
			<div class="ormControls col-5">
				<button onClick="addManage()" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 确定</button>
			   <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
    
     </form>
</div>
</body>
</html>