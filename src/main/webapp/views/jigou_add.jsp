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
<script type="text/javascript" src="<%=basePath %>js/ajaxfileupload.js"></script>  
<script type="text/javascript">
var basePath = '<%=basePath%>';
</script>
<script type="text/javascript">
//下面用于图片上传预览功能
function setImagePreview(avalue) {
var docObj=document.getElementById("doc");

var imgObjPreview=document.getElementById("preview");
if(docObj.files &&docObj.files[0])
{
//火狐下，直接设img属性
imgObjPreview.style.display = 'block';
imgObjPreview.style.width = '150px';
imgObjPreview.style.height = '180px';
//imgObjPreview.src = docObj.files[0].getAsDataURL();

//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
}
else
{
//IE下，使用滤镜
docObj.select();
var imgSrc = document.selection.createRange().text;
var localImagId = document.getElementById("localImag");
//必须设置初始大小
localImagId.style.width = "150px";
localImagId.style.height = "180px";
//图片异常的捕捉，防止用户修改后缀来伪造图片
try{
localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
}
catch(e)
{
alert("您上传的图片格式不正确，请重新选择!");
return false;
}
imgObjPreview.style.display = 'none';
document.selection.empty();
}
return true;
}

function setImagePreview1(avalue) {
	var docObj=document.getElementById("doc1");

	var imgObjPreview=document.getElementById("preview1");
	if(docObj.files &&docObj.files[0])
	{
	//火狐下，直接设img属性
	imgObjPreview.style.display = 'block';
	imgObjPreview.style.width = '150px';
	imgObjPreview.style.height = '180px';
	//imgObjPreview.src = docObj.files[0].getAsDataURL();

	//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
	imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
	}
	else
	{
	//IE下，使用滤镜
	docObj.select();
	var imgSrc = document.selection.createRange().text;
	var localImagId = document.getElementById("localImag1");
	//必须设置初始大小
	localImagId.style.width = "150px";
	localImagId.style.height = "180px";
	//图片异常的捕捉，防止用户修改后缀来伪造图片
	try{
	localImagId.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
	localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
	}
	catch(e)
	{
	alert("您上传的图片格式不正确，请重新选择!");
	return false;
	}
	imgObjPreview.style.display = 'none';
	document.selection.empty();
	}
	return true;
	}
</script>
<title>添加用户</title>
</head>
<script type="text/javascript" src="<%=basePath%>js/Validation.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jigou-add.js"></script>
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
			        <input type="text"  class="input-text" autocomplete="off" value="" placeholder="正确输入手机号" id="loginAccounts" name="loginAccounts" datatype="*2-16" onblur="validationFomr(this.value,'phone')">
			      </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>帐号密码：</label>
			      <div class="formControls col-5">
			        <input type="password" class="input-text" value="" placeholder="只能输入6-20个字母、数字、下划线  " id="password" name="password" onblur="validationFomr(this.value,'password')" >
			      </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>机构名称：</label>
			      <div class="formControls col-5">
			        <input type="text" class="input-text" value="" placeholder="" id="organizationAbbreviation" name="organizationAbbreviation" datatype="*2-16" nullmsg="用户名不能为空">
			      </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>机构全称：</label>
			      <div class="formControls col-5">
			        <input type="text" class="input-text" value="" placeholder="" id="organization" name="organization" datatype="*2-16" nullmsg="用户名不能为空">
			      </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3">机构类型：</label>
			      <input type="hidden" name="organizatioTypeName" id="organizatioTypeName">
			      <div class="formControls col-5"> <span class="select-box">
			        <select class="select" size="1" name="organizatioType" id="organizatioType" datatype="*" nullmsg="请选择所机构类型！">
			        </select>
			        </span> </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>所在市：</label>
			      <div class="formControls col-5"> <span class="select-box">
			      	<input type="hidden" name="locationName" id="locationName">
			        <select  onchange="changeData(this.value)" class="select" size="1" name="location" id="location" datatype="*" nullmsg="请选择所在城市！">
			          
			        </select>
			        </span> </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>所在区：</label>
			      <div class="formControls col-5"> <span class="select-box">
			      <input type="hidden" name="bairroName" id="bairroName">
			        <select class="select" size="1" name="bairro" id="bairro" placeholder="请选先选择市">
			          
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
			        <input type="text" class="input-text" value="" placeholder="" id="organizationContacts" name="organizationContacts" datatype="*2-16" nullmsg="用户名不能为空">
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
			        <input type="text" class="input-text" value="" placeholder="输入正确邮箱" id="email"  name="email" datatype="*2-16" nullmsg="用户名不能为空">
			      </div>
			      <div class="col-4"> </div>
		    </div>
    		<div class="row cl" style=" height:20px;"></div>
     </form>

           <div class="row cl">
		      	<label class="form-label col-3"></label>
		      	<div id="localImag"><img id="preview" src="" width="150" height="180" style="display: block; width: 150px; height: 180px;"></div>
		      	<label class="form-label col-3"></label>
		     身份证： <input type="file" name="file" id="doc"style="width:150px;" onchange="javascript:setImagePreview();">
    		</div>

	    	<div class="row cl">
	      		 <label class="form-label col-3"></label>
		         <div id="localImag1"><img id="preview1" src="" width="150" height="180" style="display: block; width: 150px; height: 180px;"></div>
		  <label class="form-label col-3"></label>   
		      营业执照：<input type="file" name="file" id="doc1" style="width:150px;" onchange="javascript:setImagePreview1();">
	    	</div>
	    	<div class="row cl">
	      		<label class="form-label col-3"></label>
				<div class="ormControls col-5">
					<button onClick="sctp();" type="button"><i class="Hui-iconfont">&#xe632;</i>上传图片</button>
				</div>
			</div>
			<div></div>
	    	<div class="row cl">
	      		<label class="form-label col-3"></label>
				<div class="ormControls col-5">
					<button onClick="addOrganization();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 确定</button>
				  <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>

	</div>
</body>
</html>