<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
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
</head>
<script type="text/javascript">
	var basePath='<%=basePath%>';
</script>
<script type="text/javascript" src="<%=basePath %>js/zidian-add.js"></script>
<body>
<div class="pd-20">
  <form class="form form-horizontal" id="form-member-add">
  <div class="row cl">
      <label class="form-label col-3">上级：</label>
      <div class="formControls col-5">
      <input type="hidden" class="input-text" id="type" name="type" readonly="readonly">
      <input type="text" class="input-text" id="typeName" name="typeName" readonly="readonly">
      </div>
      <div class="col-4"> </div>
    </div>
  <div class="row cl">
      <label class="form-label col-3">名称：</label>
      <div class="formControls col-5">
      <input type="text" class="input-text" id="name" name="name">
      </div>
      <div class="col-4"> </div>
    </div>
    <div class="row cl">
      <label class="form-label col-3"></label>
			<div class="ormControls col-5">
				<button onClick="addSysCode()" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 确定</button>
			   <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
    
     </form>
</div>
</body>
</html>