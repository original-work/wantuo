<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String loginAccounts=request.getParameter("loginAccounts").toString();
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
var basePath = '<%=basePath%>';
var loginAccounts='<%=loginAccounts%>';
$(function(){
	$.ajax({
		ansyc:false,
		type:'get',
		url:basePath+'organization/findDetails',
		dataType:'json',
		data:{"loginAccounts":loginAccounts},
		success:function(result){
			var org=result.org;
			$("#loginAccounts").val(org.loginAccounts);
			//$("#password").val(org.password);
			$("#organizationAbbreviation").val(org.organizationAbbreviation);
			$("#organization").val(org.organization);
			$("#organizatioTypeName").val(org.organizatioTypeName);
			$("#locationName").val(org.locationName);
			$("#bairroName").val(org.bairroName);
			$("#address").val(org.address);
			$("#organizationContacts").val(org.organizationContacts);
			$("#phone").val(org.phone);
			$("#email").val(org.email);
			$.ajax({
				ansyc:false,
				url:basePath+"accessory/findPicture",
				type:'get',
				dataType:'json',
				data:{"pathNameStr":org.idCardImage+","+org.businessLicenseImage},
				success:function(result){
					$("#preview").attr("src","http://"+result.data.path.split(',')[0]);
					$("#preview1").attr("src","http://"+result.data.path.split(',')[1]);
				}
			});
		}
	});
});
</script>

<title>机构详情</title>
</head>
<%-- <script type="text/javascript" src="<%=basePath%>js/Validation.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jigou-add.js"></script> --%>
<body>
	<div class="pd-20">
  	<form class="form form-horizontal" id="form1">
  	<input type="hidden" id="idCardImage" name="idCardImage">
  	<input type="hidden" id="businessLicenseImage" name="businessLicenseImage">
    		<div class="row cl">
    			<label class="form-label col-3" style="font-weight:bold;">机构基本信息</label>
    		</div>
    		 <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>机构帐号：</label>
			      <div class="formControls col-5">
			        <input type="text"  class="input-text" autocomplete="off" id="loginAccounts" name="loginAccounts" datatype="*2-16" >
			      </div>
			      <div class="col-4"> </div>
		    </div>
		   <!--  <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>帐号密码：</label>
			      <div class="formControls col-5">
			        <input type="password" class="input-text" id="password" name="password" >
			      </div>
			      <div class="col-4"> </div>
		    </div> -->
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>机构名称：</label>
			      <div class="formControls col-5">
			        <input type="text" class="input-text"  id="organizationAbbreviation" name="organizationAbbreviation" datatype="*2-16" nullmsg="用户名不能为空">
			      </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>机构全称：</label>
			      <div class="formControls col-5">
			        <input type="text" class="input-text"  id="organization" name="organization" datatype="*2-16" nullmsg="用户名不能为空">
			      </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3">机构类型：</label>
			      <div class="formControls col-5"> <span class="select-box">
			      	<input name="organizatioTypeName" id="organizatioTypeName"  class="input-text" type="text"  >
			       
			        </span> </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>所在市：</label>
			      <div class="formControls col-5"> <span class="select-box">
			      	<input name="locationName" id="locationName"  class="input-text" type="text"  >
			        </span> </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>所在区：</label>
			      <div class="formControls col-5"> <span class="select-box">
			      <input type="text" name="bairroName" id="bairroName" class="input-text" >
			        </span> </div>
			      <div class="col-4"> </div>
		    </div>
		     <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>详细地址：</label>
			      <div class="formControls col-5">
			        <input type="text" class="input-text"placeholder="" id="address" name="address" >
			      </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3">联系人：</label>
			      <div class="formControls col-5">
			        <input type="text" class="input-text" placeholder="" id="organizationContacts" name="organizationContacts" datatype="*2-16" nullmsg="用户名不能为空">
			      </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3">联系方式：</label>
			      <div class="formControls col-5">
			        <input type="text" class="input-text" placeholder="" id="phone" name="phone" datatype="*2-16" nullmsg="用户名不能为空">
			      </div>
			      <div class="col-4"> </div>
		    </div>
		     <div class="row cl">
			      <label class="form-label col-3">邮箱地址：</label>
			      <div class="formControls col-5">
			        <input type="text" class="input-text"  id="email"  name="email" datatype="*2-16" nullmsg="用户名不能为空">
			      </div>
			      <div class="col-4"> </div>
		    </div>
    		<div class="row cl" style=" height:20px;"></div>
     </form>

           <div class="row cl">
		      	<label class="form-label col-3"></label>
		      	<div id="localImag"><img id="preview" src="" width="150" height="180" style="display: block; width: 150px; height: 180px;"></div>
		      	<label class="form-label col-3"></label>
		      
		     身份证： </div>
	    	<div class="row cl">
	      		 <label class="form-label col-3"></label>
		         <div id="localImag1"><img id="preview1" src="" width="150" height="180" style="display: block; width: 150px; height: 180px;"></div>
		  <label class="form-label col-3"></label>   
		      营业执照：
	    	</div>
	    		    	<div class="row cl">
	      		<label class="form-label col-3"></label>
				<!-- <div class="ormControls col-5">
				<button onClick="sctp();" type="button"><i class="Hui-iconfont">&#xe632;</i> 上传图片</button>
				</div> -->
			</div>
			<div></div>
	    	<!-- <div class="row cl">
	      		<label class="form-label col-3"></label>
				<div class="ormControls col-5">
					<button onClick="addOrganization();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 确定</button>
				  <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div> -->

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