$(function(){
		$("#table1 tr",window.parent.document).each(function(){
			if($(this).children().first().children().is(':checked')){
				$("#id").val($(this).children().first().children().val());
				$("#schoolName").val($(this).children().get(1).innerHTML);
				$("#fullName").val($(this).children().get(2).innerHTML);
				$("#locationName").val($(this).children().get(3).innerHTML);
				$("#bairroName").val($(this).children().get(4).innerHTML);
				$("#address").val($(this).children().get(5).innerHTML);
				$("#propertyName").val($(this).children().get(6).innerHTML);
				$("#schoolContacts").val($(this).children().get(8).innerHTML);
				$("#phone").val($(this).children().get(9).innerHTML);
				$("#email").val($(this).children().get(10).innerHTML);
				$("#location").val($(this).children().get(11).innerHTML);
				$("#bairro").val($(this).children().get(12).innerHTML);
				city($(this).children().get(11).innerHTML);
				changeData($(this).children().get(11).innerHTML,$(this).children().get(12).innerHTML);
			} 
		});
		
	});
	function quxiao(){
		parent.window.location.reload(); 
		layer_close();
	}

	function editSchool(){
		if($("#location").val()!=null&&$("#location").val()!=''){
			var locationName=$("#location").find("option:selected").text();
			$("#locationName").val(locationName);
		}
		if($("#bairro").val()!=null&&$("#bairro").val()!=''){
			var bairroName=$("#bairro").find("option:selected").text();
			$("#bairroName").val(bairroName);
		}
		$.ajax({
			ansyc:false,
			type:'post',
			url:basePath+'school/updateSchool',
			dataType:'json',
			data:$("#form1").serializeArray(),
			success:function(result){
				alert(result.message);
				parent.window.location.reload(); 
				layer_close();
			},error:function(result){

			}
		});
	}

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
