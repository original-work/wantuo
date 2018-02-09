<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <meta charset="utf-8">
<link href="<%=basePath %>css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=basePath %>lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="<%=basePath %>lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="<%=basePath %>js/H-ui.admin.js"></script>
<script type="text/javascript" src="<%=basePath %>js/ajaxfileupload.js"></script>  
<title>批量上传</title>
<script type="text/javascript">
	var basePath='<%=basePath%>';
</script>
<body>
<div class="pd-20">
  <form id="form1" enctype="multipart/form-data">
   <div class="row cl">
      <label class="form-label col-3">导入文件：</label>
      <div class="formControls col-5"> <span class="btn-upload form-group">
         <input type="file" name="file" id="file">
        </span> </div>
      <div class="col-4"> </div>
    </div>
    <div class="row cl" style=" height:20px;"></div>
    <div class="row cl">
      <label class="form-label col-3"></label>
			<div class="ormControls col-5">
				<button onclick="ajaxFileUpload()" class="btn btn-default radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 确定</button>
			   <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
     </form>
</div>

<script type="text/javascript">  
function ajaxFileUpload() {  
    //开始上传文件时显示一个图片,文件上传完成将图片隐藏
    //$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
    //执行上传文件操作的函数
    $.ajaxFileUpload({
        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
        url:'<%=basePath %>organization/importOrganization',
        secureuri:false,                       //是否启用安全提交,默认为false
        fileElementId:'file',           //文件选择框的id属性
        dataType:'text',                       //服务器返回的格式,可以是json或xml等
        success:function(data){        //服务器响应成功时的处理函数
            data = data.replace("<PRE>", '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
            data = data.replace("</PRE>", '');
            data = data.replace("<pre>", '');
            data = data.replace("<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">", '');
            data = data.replace("</pre>", '');//本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
            alert(data);
        },
        error:function(data){ //服务器响应失败时的处理函数
            alert("导入失败！");
        }
    });
} 
  
</script>  
</body>
</html>