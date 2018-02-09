$(function(){
	var idCardImage='';
	var blImage='';
	$("#table1 tr",window.parent.document).each(function(){
		//$("#manage_name").val($(this).children().get(2).innerHTML);
		if($(this).children().first().children().is(':checked')){
			$("#id").val($(this).children().first().children().val());
			$("#loginAccounts").val($(this).find("td").eq(1).find("a").html());
			$("#organizationAbbreviation").val($(this).children().get(2).innerHTML);
			$("#organization").val($(this).children().get(3).innerHTML);
			$("#locationName").val($(this).children().get(4).innerHTML);
			$("#bairroName").val($(this).children().get(5).innerHTML);
			$("#address").val($(this).children().get(6).innerHTML);
			$("#phone").val($(this).children().get(9).innerHTML);
			$("#organizationContacts").val($(this).children().get(8).innerHTML);
			$("#phone").val($(this).children().get(9).innerHTML);
			//$("#location").find("option").text($(this).children().get(17).innerHTML).prop("selected",true);
			$("#bairro").val($(this).children().get(17).innerHTML);
			//$("#bairro").find("option").text($(this).children().get(18).innerHTML).prop("selected",true);
			$("#organizatioType").val($(this).children().get(20).innerHTML);
			idCardImage=$(this).children().get(18).innerHTML;
			blImage=$(this).children().get(19).innerHTML;
			city($(this).children().get(16).innerHTML);
			changeData($(this).children().get(16).innerHTML,$(this).children().get(17).innerHTML);
			jigouxingzhi($(this).children().get(20).innerHTML);
			$("#introduce").val($(this).children().get(21).innerHTML);
		}
	});
	$.ajax({
		ansyc:false,
		url:basePath+"accessory/findPicture",
		type:'get',
		dataType:'json',
		data:{"pathNameStr":idCardImage+","+blImage},
		success:function(result){
			console.log(result.data.path);
			$("#preview").attr("src","http://"+result.data.path.split(',')[0]);
			$("#preview1").attr("src","http://"+result.data.path.split(',')[1]);
		}
	});
	
});
function quxiao(){
	parent.window.location.reload(); 
	layer_close();
}

function updateById(){
	if($("#location").val()!=null&&$("#location").val()!=''){
		$("#locationName").val($("#location").find("option:selected").text());
	}
	if($("#bairro").val()!=null&&$("#bairro").val()!=''){
		$("#bairroName").val($("#bairro").find("option:selected").text());
	}
	if($("#organizatioType").val()!=null&&$("#organizatioType").val()!=''){
		$("#organizatioTypeName").val($("#organizatioType").find("option:selected").text());
	}
	$.ajax({
		ansyc:false,
		type:'post',
		url:basePath+'organization/updateHouTaiOrg',
		dataType:'json',
		data:$("#form1").serializeArray(),
		success:function(result){
			alert(result.message);
			parent.window.location.reload(); 
			layer_close();
		}
	});
}
//市 列表默认上海
function city(obj){
	$.ajax({
		type: "post", 
		url: basePath+"syscode/phoneQueryByList",
		dataType:"json",
		data:{"parentId":1},
		success: function(data){
			var status = data.status;
			var list = data.data.sysCodeList;
			var html = "";
			if(status == "0"){
				for(var i=0;i<list.length;i++){
					if(list[i].id==obj){
						html += "<option selected=\"selected\" value='"+list[i].id+"'>"+list[i].name+"</option>";
						continue;
					}
					html += "<option value='"+list[i].id+"'>"+list[i].name+"</option>";
				}
				document.getElementById("location").innerHTML=html;
			}else{
				alert(data.message);
			}
		},
		error:function(data){
			alert("程序错误");
		}
	});
}
//默认上海的区
function area(){
	$.ajax({
		type: "post", 
		url: basePath+"syscode/phoneQueryByList",
		dataType:"json",
		data:{"parentId":3},
		success: function(data){
			var status = data.status;
			var list = data.data.sysCodeList;
			var html = "";
			if(status == "0"){

			}else{
				alert(data.message);
			}
			$("#bairro").append(html);
		},
		error:function(data){
			alert("程序错误");
		}
	});
}
function changeData(obj,obj2){
	 var ctrl2=document.getElementById("bairro");
    for(var i=0;i<ctrl2.options.length;) 
    { 
        ctrl2.removeChild(ctrl2.options[i]); 
    }
	$.ajax({
		type: "post", 
		url: basePath+"/syscode/phoneQueryByList",
		dataType:"json",
		data:{"parentId":obj},
		success: function(data){
			var status = data.status;
			var list = data.data.sysCodeList;
			var html = "";
			if(status == "0"){
				for(var i=0;i<list.length;i++){
					if(list[i].id==obj2){
						html += "<option selected=\"selected\" value='"+list[i].id+"'>"+list[i].name+"</option>";
						continue;
					}
					html += "<option value='"+list[i].id+"'>"+list[i].name+"</option>";
				}
			}else{
				alert(data.message);
			}
			$("#bairro").append(html);
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

//机构性质列表
function jigouxingzhi(obj3){
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
					if(list[i].id==obj3){
						html += "<option selected=\"selected\" value='"+list[i].id+"'>"+list[i].name+"</option>";
						continue;
					}
					html += "<option value='"+list[i].id+"'>"+list[i].name+"</option>";
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