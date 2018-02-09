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

<script type="text/javascript">
	var basePath='<%=basePath%>';
	//window.frames["iframeSon"].document
	//$("select").find("option:selected").text();
</script>
<script type="text/javascript" src="<%=basePath %>js/xuexiao-add.js"></script>
<title>添加用户</title>
</head>
<body>
<div class="pd-20">
  <form id="form1" class="form form-horizontal">
    <div class="row cl"><label class="form-label col-3" style="font-weight:bold;">学校基本信息</label></div>
    <div class="row cl">
      <label class="form-label col-3"><span class="c-red">*</span>学校名称：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" id="schoolName" name="schoolName" datatype="*2-16" nullmsg="用户名不能为空">
      </div>
      <div class="col-4"> </div>
    </div>
    <div class="row cl">
      <label class="form-label col-3"><span class="c-red">*</span>学校全称：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value="" placeholder="" id="fullName" name="fullName" datatype="*2-16" nullmsg="用户名不能为空">
      </div>
      <div class="col-4"> </div>
    </div>
    <div class="row cl">
      <label class="form-label col-3">省市：</label>
      <div class="formControls col-5"> <span class="select-box">
      		<input type="hidden" name="locationName" id="locationName">
        <select class="select" size="1" name="location" id="location" datatype="*" nullmsg="请选择所在城市！" onchange="changeData(this.value)">
       
        </select>
        </span> </div>
      <div class="col-4"> </div>
    </div>
    <div class="row cl">
      <label class="form-label col-3">所在地：</label>
      <div class="formControls col-5"> <span class="select-box">
      <input type="hidden" name="bairroName" id="bairroName">
        <select class="select" size="1" name="bairro" id="bairro" datatype="*" nullmsg="请选择所在城市！">
          
        </select>
        </span> </div>
      <div class="col-4"> </div>
    </div>
     <div class="row cl">
      <label class="form-label col-3"><span class="c-red">*</span>详细地址：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value="" placeholder="" id="address" name="address" datatype="*2-16" nullmsg="用户名不能为空">
      </div>
      <div class="col-4"> </div>
    </div>
    <div class="row cl">
      <label class="form-label col-3">联系人：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value="" placeholder="" id="schoolContacts" name="schoolContacts" datatype="*2-16" nullmsg="用户名不能为空">
      </div>
      <div class="col-4"> </div>
    </div>
    <div class="row cl">
      <label class="form-label col-3">联系方式：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value="" placeholder="" id="phone" name="phone" datatype="*2-16" nullmsg="用户名不能为空">
      </div>
      <div class="col-4"> </div>
    </div>
     <div class="row cl">
      <label class="form-label col-3">邮箱地址：</label>
      <div class="formControls col-5">
        <input type="text" class="input-text" value="" placeholder="" id="email" name="email" datatype="*2-16" nullmsg="用户名不能为空">
      </div>
      <div class="col-4"> </div>
    </div>
    <div class="row cl" style=" height:20px;"></div>
    <div class="row cl">
      <label class="form-label col-3"></label>
			<div class="ormControls col-5">
				<button onClick="addSchoolSon()" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 确定</button>
			   <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
    
     </form>
</div>
</div>

<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form1").Validform({
		tiptype:2,
		callback:function(form){
			form[0].submit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
});
</script>
</body>
</html>