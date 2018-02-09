<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>活动详情</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<%=basePath%>css/H-ui.min.css" rel="stylesheet"type="text/css" />
<link href="<%=basePath%>css/H-ui.admin.css" rel="stylesheet"type="text/css" />
<link href="<%=basePath%>lib/icheck/icheck.css" rel="stylesheet"type="text/css" />
<link href="<%=basePath%>lib/Hui-iconfont/1.0.1/iconfont.css"rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=basePath%>lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="<%=basePath%>lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>js/H-ui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/H-ui.admin.js"></script>
<title>活动详情</title>
<style type="text/css">
<!--
#localImag {
	width: 150px;
	float: left;
}

.localImgjj {
	height: 160px;
	width: 350px;
	resize: none;
	float: left;
	padding: 10px;
	margin-left: 15px;
}

.col-5 {
	width: 70%;
}

#goBack{
	margin: 0px;
	padding: 0px;
}
-->
</style>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal">
			<div class="row cl">
				<label class="form-label col-3">活动名称：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" id="activityName" name="activityName" value="${activity.activityName }">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">位置：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text"id="location" name="location" value="${activity.location }">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">缩略图：</label>
				<div class="formControls col-5">
					<div id="localImag">
						<img id="thumbnailPath" name="thumbnailPath"
							src="${activity.thumbnailPath}"
							style="display: block; width: 150px; height: 180px;" />
					</div>
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">详细图：</label>
				<div class="formControls col-5">
					<div id="localImag">
						<img id="largePath" name="largePath"
							src="${activity.largePath }"
							style="display: block; width: 200px; height: 240px;" />
					</div>
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl" style="padding-left: 420px;padding-top: 10px;" id="goBack">
				<div class="formControls col-5">
					<div id="fanhui">
							<a href="<%=basePath%>views/huodongguanli.jsp" class="btn btn-primary radius">返回</a>
					</div>
				</div>
				<div class="col-4"></div>
			</div> 
			<!--ddd-->
			<div class="row cl" style=" height:20px;"></div>
		</form>
	</div>
</body>
</html>