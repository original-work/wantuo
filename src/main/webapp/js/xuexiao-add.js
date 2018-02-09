function addSchoolSon(){
		if($("#schoolName").val()==null||$("#schoolName").val()==''){
			alert("学校名称不能为空！");
			return;
		}else if($("#fullName").val()==null||$("#fullName").val()==''){
			alert("学校全称不能为空！");
			return ;
		}else if($("#address").val()==null||$("#address").val()==''){
			alert("学校详细地址不能为空！");
			return;
		}
		if($("#location").val()!=null&&$("#location").val()!=''){
			$("#locationName").val($("#location").find("option:selected").text());
    	}
    	if($("#bairro").val()!=null&&$("#bairro").val()!='456'&&$("#bairro").val()!=''){
    		$("#bairroName").val($("#bairro").find("option:selected").text());
    	}
		$.ajax({
			ansyc:false,
			type:'post',
			url:basePath+'/school/saveSchoolBySeletc',
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