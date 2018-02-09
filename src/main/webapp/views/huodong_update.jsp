<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE>
<html>
<head>
<link href="<%=basePath%>css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=basePath%>lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="<%=basePath%>lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>js/H-ui.js"></script>
<script type="text/javascript" src="<%=basePath%>js/H-ui.admin.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
<title>添加用户</title>
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
-->
</style>
<script type="text/javascript">
var basePath = '<%=basePath%>';
	//下面用于图片上传预览功能
	function setImagePreview(avalue) {
		var docObj = document.getElementById("doc");

		var imgObjPreview = document.getElementById("preview");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '150px';
			imgObjPreview.style.height = '180px';
			//imgObjPreview.src = docObj.files[0].getAsDataURL();

			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			//IE下，使用滤镜
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("localImag");
			//必须设置初始大小
			localImagId.style.width = "150px";
			localImagId.style.height = "180px";
			//图片异常的捕捉，防止用户修改后缀来伪造图片
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("您上传的图片格式不正确，请重新选择!");
				return false;
			}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		return true;
	}

	function setImagePreview1(avalue) {
		var docObj = document.getElementById("doc1");

		var imgObjPreview = document.getElementById("preview1");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '150px';
			imgObjPreview.style.height = '180px';
			//imgObjPreview.src = docObj.files[0].getAsDataURL();

			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			//IE下，使用滤镜
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("localImag1");
			//必须设置初始大小
			localImagId.style.width = "150px";
			localImagId.style.height = "180px";
			//图片异常的捕捉，防止用户修改后缀来伪造图片
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("您上传的图片格式不正确，请重新选择!");
				return false;
			}
			imgObjPreview.style.display = 'none';
			document.selection.empty();
		}
		return true;
	}

	function sctp() {
		if ($("#doc").val() != null & $("#doc").val() != '') {
			$.ajaxFileUpload({
				//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
				url : basePath + 'FileUpload/activityPicture',
				secureuri : false, //是否启用安全提交,默认为false
				fileElementId : 'doc', //文件选择框的id属性
				dataType : 'text', //服务器返回的格式,可以是json或xml等
				success : function(data) { //服务器响应成功时的处理函数
					data = data.replace("<PRE>", ''); //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
					data = data.replace("</PRE>", '');
					data = data.replace("<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">", '');
					data = data.replace("<pre>", '');
					data = data.replace("</pre>", '');//本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
					console.log();
					$("#thumbnailPath").val(data);
				},
				error : function(data) { //服务器响应失败时的处理函数

				}
			});
		}
		if ($("#doc1").val() != null & $("#doc1").val() != '') {
			$.ajaxFileUpload({
				//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
				url : basePath + 'FileUpload/activityPicture',
				secureuri : false, //是否启用安全提交,默认为false
				fileElementId : 'doc1', //文件选择框的id属性
				dataType : 'text', //服务器返回的格式,可以是json或xml等
				success : function(data) { //服务器响应成功时的处理函数
					data = data.replace("<PRE>", ''); //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
					data = data.replace("</PRE>", '');
					data = data.replace("<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">", '');
					data = data.replace("<pre>", '');
					data = data.replace("</pre>", '');//本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
					$("#largePath").val(data);
				},
				error : function(data) { //服务器响应失败时的处理函数
				}
			});
		}
	}

	function saveinfo(){
		$.ajax({
			ansyc:false,
			type:'POST',
			url:basePath+'activity/updateById',
			dataType:'json',
			data:$("#Form1").serialize(),
			success:function(result){
				alert(result.message);
				window.location.href=basePath+'activity/QueryList'; 
			}
		});
	}
	
	function go(){
		window.location.href=basePath+'views/huodongguanli.jsp'; 
	}
</script>
</head>
<body>
	<div class="pd-20">
		<form class="form form-horizontal" id="Form1">
			<input type="hidden" id="id" name="id" value="${activity.id}" />
			<input type="hidden" name="thumbnailPath" id="thumbnailPath" />
			<input type="hidden" name="largePath" id="largePath" />
			<div class="row cl">
				<label class="form-label col-3">活动名称：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${activity.activityName}" id="activityName" name="activityName">
				</div>
				<div class="col-4"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">位置：</label>
				<div class="formControls col-5">
					<input type="text" class="input-text" value="${activity.location}"
						id="location" name="location">
				</div>
				<div class="col-4"></div>
			</div>
			<!-- 图片 -->
			<div style="width:80%;height:40%;padding-left: 290px;" class="row cl">
				<div style="width:50%;height:100px;float:left">
					<div class="row cl" style="height:50%;width:100%;float:left;">
					<label class="form-label col-3">缩略图：</label>
					<div class="formControls col-5">
						<div id="localImag">
							<img id="preview" src="${activity.thumbnailPath}" width="150"
							height="180" style="display: block; width: 150px; height: 180px;">
							<input type="file" name="file" id="doc" style="width:150px;" onchange="javascript:setImagePreview();">
						</div>
						<!-- <textarea class="localImgjj">
				        	
	        			</textarea> -->
	        		</div>
				</div>
				<div class="row cl" style="height:50%;width:100%;">
					<label class="form-label col-3">详细图：</label>
					<div id="localImag1">
						<img id="preview1" src="${activity.largePath}" width="150"
							height="180" style="display: block; width: 150px; height: 180px;">
					</div>
					<input type="file"name="file" id="doc1" style="width:150px;" onchange="javascript:setImagePreview1();">
					<label class="form-label col-3"></label>
				</div>
				</div>
				<div style="float:left;height:100%;width:50%;margin-left: 0px">
       					说明：
       				<dl>
      						1、广场只能添加6个活动；
      						<br/>
      						2、上传的活动图片的名称不包含有汉字、特殊符号、下划线、加号；
      						<br/>
      						3、每个活动对应的缩略图高宽比例不一样，请参考下面的比例说明上传图片：
       				</dl>
       				<dt>
       					<img alt="操作提示" src="<%=basePath%>images/bili.png">
       				</dt>
	        	</div>
			</div>
			<!-- 图片 -->
			<div class="ormControls col-5" style="padding-left:417px;margin-top: 0px">
					<button onClick="sctp();" type="button">
						<i class="Hui-iconfont">&#xe632;</i>上传图片
					</button>
				</div>
			<div class="row cl" style="height:20px;"></div>
			<div class="row cl">
				<label class="form-label col-3"></label>
				<div class="ormControls col-5">
					<button class="btn btn-primary radius" type="button" id="save" onclick="saveinfo()">
						<i class="Hui-iconfont">&#xe632;</i> 确定
					</button>
					<button onClick="go()" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>