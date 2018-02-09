 function addOrganization(){
    	if($("#organizationAbbreviation").val()==null||$("#organizationAbbreviation").val()==''){
    		alert("机构帐号不能为空");
    		return;
    	}else if($("#organization").val()==null||$("#organization").val()==''){
    		alert("机构全称不能为空");
    		return;
    	}
    	if($("#organizatioType").val()==null||$("#organizatioType").val()==''){
    		alert("机构类型不能为空");
    		return;
    	}else{
    		$("#organizatioTypeName").val($("#organizatioType").find("option:selected").text());
    	}
    	if($("#location").val()==null||$("#location").val()==''){
    		alert($("#location").val());
    		alert("机构所在省市不能为空");
    		return;
    	}else{
    		$("#locationName").val($("#location").find("option:selected").text());
    	}
    	if($("#bairro").val()==null||$("#bairro").val()=='456'||$("#bairro").val()==''){
    		alert("机构所在地区不能为空");
    		return;
    	}else{
    		$("#bairroName").val($("#bairro").find("option:selected").text());
    	}
    	if($("#address").val()==null||$("#address").val()==''){
    		alert("机构详细地址不能为空");
    		return;
    	}else if($("#loginAccounts").val()==null||$("#loginAccounts").val()==''){
    		alert("机构帐号不能为空");
    		return;
    	}else if($("#password").val()==null||$("#password").val()==''){
    		alert("机构密码不能为空");
    		return;
    	}
    	if(isOk()){
    		$.ajax({
        		ansyc:false,
        		type:'post',
        		url:basePath+'organization/InsertOrganization',
        		dataType:'json',
        		data:$("#form1").serializeArray(),
        		success:function(result){
        			alert(result.message);
        			parent.window.location.reload(); 
    				var index = parent.layer.getFrameIndex(window.name);
    				parent.$('.btn-refresh').click();
    				parent.layer.close(index);
        		}
        	});
    	}else{
    		alert("数据填写有错误，请重新填写");
    	}
    	
    }
//市 列表默认上海
 function city(){
 	$.ajax({
 		type: "post", 
 		url: basePath+"syscode/phoneQueryByList",
 		dataType:"json",
 		data:{"parentId":1},
 		success: function(data){
 			var list = data.data.sysCodeList;
 			var html = "<option value='123'>--请选择--</option>";
 				for(var i=0;i<list.length;i++){
 					html += "<option value='"+list[i].id+"'>"+list[i].name+"</option>";
 				}
 				document.getElementById("location").innerHTML=html;
 		},
 		error:function(data){
 			alert("程序错误");
 		}
 	});
 }
 function changeData(obj){
 	 var bairroHTML=document.getElementById("bairro");
 	 var html="<option value='456'>--请选择--</option>";
	 if(obj==null||obj==''){
		 
	 }else{
		    for(var i=0;i<bairroHTML.options.length;) 
		    { 
		    	bairroHTML.removeChild(bairroHTML.options[i]); 
		    }
		 $.ajax({
		 		type: "post", 
		 		url: basePath+"/syscode/phoneQueryByList",
		 		dataType:"json",
		 		data:{"parentId":obj},
		 		success: function(data){
		 			var list = data.data.sysCodeList;
	 				for(var i=0;i<list.length;i++){
	 					html+="<option value='"+list[i].id+"'>"+list[i].name+"</option>";
	 				}
	 				 bairroHTML.innerHTML=html;
		 		},
		 		error:function(data){
		 			alert("程序错误");
		 		}
		 	});
	 }
 }
$(function(){
	
	city();
	jigouxingzhi();
});
//机构性质列表
function jigouxingzhi(){
	$.ajax({
		type: "post", 
		url: basePath+"syscode/phoneQueryByList",
		dataType:"json",
		data:{"parentId":14},
		success: function(data){
			var status = data.status;
			var list = data.data.sysCodeList;
			var html = "<option value='0' selected>请选择类型</option>";
			if(status == "0"){
				for(var i=0;i<list.length;i++){
					html += "<option value='"+list[i].type+"'>"+list[i].name+"</option>";
				}
				$("#organizatioType").append(html);
			}else{
				alert(data.message);
			}
		},
		error:function(data){
			alert("程序错误");
		}
	});
}
function sctp(){
	if($("#doc").val()!=null&$("#doc").val()!=''){
	    $.ajaxFileUpload({
	        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
	        url:basePath+'FileUpload/oneFilesUpload',
	        secureuri:false,                       //是否启用安全提交,默认为false
	        fileElementId:'doc',           //文件选择框的id属性
	        dataType:'text',                       //服务器返回的格式,可以是json或xml等
	        success:function(data){        //服务器响应成功时的处理函数
	            data = data.replace("<PRE>", '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
	            data = data.replace("</PRE>", '');
	            data = data.replace("<pre>", '');
	            data = data.replace("<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">", '');
	            data = data.replace("</pre>", '');//本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
	            $("#idCardImage").val(data);
	        },
	        error:function(data){ //服务器响应失败时的处理函数

	        }
	    });
	}
	if($("#doc1").val()!=null&$("#doc1").val()!=''){
	    $.ajaxFileUpload({
	        //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
	        url:basePath+'FileUpload/oneFilesUpload',
	        secureuri:false,                       //是否启用安全提交,默认为false
	        fileElementId:'doc1',           //文件选择框的id属性
	        dataType:'text',                       //服务器返回的格式,可以是json或xml等
	        success:function(data){        //服务器响应成功时的处理函数
	            data = data.replace("<PRE>", '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
	            data = data.replace("</PRE>", '');
	            data = data.replace("<pre>", '');
	            data = data.replace("<pre style=\"word-wrap: break-word; white-space: pre-wrap;\">", '');
	            data = data.replace("</pre>", '');//本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
	            $("#businessLicenseImage").val(data);
	        },
	        error:function(data){ //服务器响应失败时的处理函数
	        }
	    });
	}
}
