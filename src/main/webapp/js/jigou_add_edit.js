$().ready(function(){
	jigouxingzhi();
	city();
	area();
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
//市 列表默认上海
function city(){
	$.ajax({
		type: "post", 
		url: basePath+"syscode/phoneQueryByList",
		dataType:"json",
		data:{"parentId":1},
		success: function(data){
			var status = data.status;
			var list = data.data.sysCodeList;
			var html = "<option value='0'>请选择市</option>";
			if(status == "0"){
				for(var i=0;i<list.length;i++){
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
			var html = "<option value='0'>请选择区</option>";
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
function changeData(obj){
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
