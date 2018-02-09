<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>机构详情</title>
</head>
<script type="text/javascript">
	var basePath='<%=basePath %>';
	function update(){
		json={"sort":$("#sort").val(),"evaluate":$("#evaluate").val(),"loginAccounts":$("#loginAccounts").val()};
		$.ajax({
			ansyc:false,
			type:'post',
			url:basePath+'organization/updateOrg',
			dataType:'json',
			data:json,
			success:function(result){
				alert(result.message);
				if(result.status==0){
					window.location.href=basePath+'views/paihangbang.jsp'; 
				}
			}
		});
	}
	
	function quxiao(){
		window.location.href=basePath+'views/paihangbang.jsp'; 
	}
</script>
<body>
	<div class="pd-20">
  	<form class="form form-horizontal" id="form1">
    		<div class="row cl">
    			<label class="form-label col-3" style="font-weight:bold;">修改机构排名信息</label>
    		</div>
    		<div class="row cl">
			      <label class="form-label col-3">排名：</label>
			      <div class="formControls col-5">
			        <input type="text"  class="input-text" value="${org.sort}" id="sort" name="sort" >
			      </div>
			      <div class="col-4"> </div>
		    </div>
    		 <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>机构帐号：</label>
			      <div class="formControls col-5">
			        <input type="text"  class="input-text" value="${org.loginAccounts }" id="loginAccounts" name="loginAccounts" readonly="readonly" >
			      </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>机构名称：</label>
			      <div class="formControls col-5">
			        <input type="text" class="input-text"  id="organizationAbbreviation" name="organizationAbbreviation" value="${org.organizationAbbreviation }" readonly="readonly">
			      </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>机构全称：</label>
			      <div class="formControls col-5">
			        <input type="text" class="input-text"  id="organization" name="organization" value="${org.organization }" readonly="readonly">
			      </div>
			      <div class="col-4"> </div>
		    </div>
		     <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>详细地址：</label>
			      <div class="formControls col-5">
			        <input type="text" class="input-text" value="${org.address }" id="address" name="address" readonly="readonly">
			      </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>联系人：</label>
			      <div class="formControls col-5">
			        <input type="text" class="input-text"  id="organizationContacts" name="organizationContacts" value="${org.organizationContacts }" readonly="readonly">
			      </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>联系方式：</label>
			      <div class="formControls col-5">
			        <input type="text" class="input-text"  id="phone" name="phone" value="${org.phone }" readonly="readonly">
			      </div>
			      <div class="col-4"> </div>
		    </div>
		     <div class="row cl">
			      <label class="form-label col-3">星级：</label>
			      <div class="formControls col-5">
			        <input type="text" class="input-text"  id="evaluate"  name="evaluate" value="${org.evaluate }" >
			      </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>认证：</label>
			      <div class="formControls col-5">
			      	<c:choose>
			      		<c:when test="${org.attestation==1 }">
			      			<input type="text" class="input-text"  id="attestation"  name="attestation" value="未认证" readonly="readonly">
			      		</c:when>
			      		<c:when test="${org.attestation==2 }">
			      			<input type="text" class="input-text"  id="attestation"  name="attestation" value="已认证" readonly="readonly">
			      		</c:when>
			      	</c:choose>
			      </div>
			      <div class="col-4"> </div>
		    </div>
		    <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>授权状态：</label>
			      <div class="formControls col-5">
			      	<c:choose>
			      		<c:when test="${org.warranty==1 }">
			      			<input type="text" class="input-text"  id="warranty"  name="warranty" value="失效" readonly="readonly">
			      		</c:when>
			      		<c:when test="${org.warranty==2 }">
			      			<input type="text" class="input-text"  id="warranty"  name="warranty" value="失效" readonly="readonly">
			      		</c:when>
			      		<c:otherwise>
			      			<input type="text" class="input-text"  id="warranty"  name="warranty" value="未生效" readonly="readonly">
			      		</c:otherwise>
			      	</c:choose>
			      </div>
			      <div class="col-4"> </div>
		    </div>
		      <div class="row cl">
			      <label class="form-label col-3"><span class="c-red">*</span>审核状态：</label>
			      <div class="formControls col-5">
			      	<c:choose>
			      		<c:when test="${org.check==1 }">
			      			<input type="text" class="input-text"  id="check"  name="check" value="未审核" readonly="readonly">
			      		</c:when>
			      		<c:when test="${org.check==2 }">
			      			<input type="text" class="input-text"  id="check"  name="check" value="已审核" readonly="readonly">
			      		</c:when>
			      	</c:choose>
			      </div>
			      <div class="col-4"> </div>
		    </div>
    		<div class="row cl" style=" height:20px;"></div>
    		<div class="row cl">
	      		<label class="form-label col-3"></label>
				<div class="ormControls col-5">
					<button onClick="update()" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 确定</button>
				   <button onClick="quxiao()" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
     </form>
	</div>
</body>
</html>