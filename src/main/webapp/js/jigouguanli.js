$().ready(function(){
	load();
});   	 
function load(obj){
	var currentPage = obj;
	var warranty = $("#warranty  option:selected").val();
	var check = $("#check  option:selected").val();
	var loginAccounts = $("#loginAccounts").val();
	var organization = $("#organization").val();
	var json = {"page":currentPage,
				"check":check,
				"loginAccounts":loginAccounts,
				"organizationAbbreviation":organization,
				"warranty":warranty
				};
	$.ajax({
		type: "post", 
		url: basePath+"/jigouguanli/getList",
		dataType:"json",
		data:json,
		success: function(data){
			console.log(data);
			var status = data.status;
			var totalSize = data.data.collectCount;
			if(status == "0"){
				var html="";
				for (var j=0;j<data.data.collectList.length;j++){
					var wsp_btn="";//未审批按钮代码
//					var ysp_btn="";//审批通过按钮代码
					var wrz_btn="";
//					var yrz_btn="";
					var sqy="";
//					var sqn="";
					var check_status = data.data.collectList[j].check;
					var attestation = data.data.collectList[j].attestation;
					var warranty = data.data.collectList[j].warranty;
					//审批
					if(check_status=='1'){
						/*wsp_btn="<a class='label label-success radius' style='background:#7B7B7B'>取消审批</a>" +*/
						wsp_btn="<a class='label label-success radius' onclick=\"spClick(1,"+data.data.collectList[j].id+","+data.data.collectList[j].loginAccounts+")\">审批</a>";
					}else if(check_status=='2'){
//						wsp_btn="<a class='label label-success radius' onclick='spClick(2,"+data.data.collectList[j].id+")'>取消审批</a>" +
//								"</br><a class='label label-success radius' style='background:#7B7B7B'>审批通过</a>";
						wsp_btn="审批通过";
						//认证
						if(attestation=='1'){
							/*wrz_btn="<a class='label label-success radius' style='background:#7B7B7B'>取消认证</a>" +*/
							wrz_btn="<a class='label label-success radius' onclick='rzClick(1,"+data.data.collectList[j].id+","+data.data.collectList[j].loginAccounts+")'>认证</a>";
						}else if(attestation=='2'){
//							wrz_btn="<a class='label label-success radius' onclick='rzClick(2,"+data.data.collectList[j].id+","+data.data.collectList[j].loginAccounts+")'>取消认证</a>" +
//									"</br><a class='label label-success radius' style='background:#7B7B7B'>认证通过</a>";
							wrz_btn="认证通过";
							if(warranty=='1'){
								sqy="<a class='label label-success radius' style='background:#7B7B7B'>失效</a>" +
										"</br><a class='label label-success radius' onclick='sqClick(1,"+data.data.collectList[j].id+","+data.data.collectList[j].loginAccounts+")'>生效</a>";
							}else if(warranty=='2'){
								sqy="<a class='label label-success radius' onclick='sqClick(2,"+data.data.collectList[j].id+","+data.data.collectList[j].loginAccounts+")'>失效</a>" +
										"</br><a class='label label-success radius' style='background:#7B7B7B'>生效</a>";
							}
						}
					}

					html +="<tr class='text-c'>";
					html +="<td><input type='checkbox' value='"+data.data.collectList[j].id+"' name=''></td>";
					html +="<td><a onClick=\"article_o('编辑','jigouxiangqing.jsp?loginAccounts="+data.data.collectList[j].loginAccounts+"','1000','700')\">"+data.data.collectList[j].loginAccounts+"</a></td>";
					html +="<td>"+data.data.collectList[j].organizationAbbreviation+"</td>";
					html +="<td>"+data.data.collectList[j].organization+"</td>";
					html +="<td>"+data.data.collectList[j].locationName+"</td>";
					html +="<td>"+data.data.collectList[j].bairroName+"</td>";
					html +="<td>"+data.data.collectList[j].address+"</td>";
					html +="<td>"+data.data.collectList[j].organizatioTypeName+"</td>";
					html +="<td>"+data.data.collectList[j].organizationContacts+"</td>";
					html +="<td>"+data.data.collectList[j].phone+"</td>";
					if(data.data.collectList[j].coordinateX>0){
						html +="<td>已定位</td>";
					}else{
						html +="<td>未定位</td>";
					}
					html +="<td class='f-14 td-manage' >"+wrz_btn;
					html +="<td class='f-14 td-manage' >"+wsp_btn;
					html +="<td style='display: none;' id="+data.data.collectList[j].id+">"+data.data.collectList[j].check+"</td>";
					html +="<td class='f-14 td-manage' >"+sqy;
					html +="<td style='display: none;' id='checkids'>"+data.data.collectList[j].id+"</td>";
					html +="<td style='display: none;' id='location'>"+data.data.collectList[j].location+"</td>";
					html +="<td style='display: none;' id='bairro'>"+data.data.collectList[j].bairro+"</td>";
					html +="<td style='display: none;' id='bairro'>"+data.data.collectList[j].idCardImage+"</td>";
					html +="<td style='display: none;' id='bairro'>"+data.data.collectList[j].businessLicenseImage+"</td>";
					html +="<td style='display: none;' id='organizatioType'>"+data.data.collectList[j].organizatioType+"</td>";
					html +="<td style='display: none;' id='introduce'>"+data.data.collectList[j].introduce+"</td>";
					html +="<td class=\"f-14 td-manage\"> <a style=\"text-decoration:none\" onClick=\"article_edit('编辑','jigou-edit.jsp','1000','700')\" class=\"ml-5\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\"></i>编辑</i></a></td>";
					html +="</tr>";
				}
				$("#jgtr").html(html);
				$("#totalSize").html(totalSize);
				$("#currentPage").html(currentPage);
				totalPage(totalSize);
			}else{
				alert("查询失败!");
			}
		},
		error:function(data){
			alert("程序错误");
		}
	});
}

function totalPage(count){
	var a=count%10;
	if(a==0){
		$("#pageCount").html(parseInt(count/pageSize));
	}else if(a>0){
		$("#pageCount").html(parseInt(count/pageSize)+1);
	}else{
		$("#pageCount").html(1);
	}
}

function flip(obj){
	var totalSize = $("#totalSize").html();
	var yushu = parseInt(parseInt(totalSize)%pageSize)
	var totalPage;
	if(0 == yushu){
		totalPage = parseInt(totalSize)/pageSize;
	}else{
		totalPage = parseInt(parseInt(totalSize)/pageSize)+1;
	}
	var currentPage = $("#currentPage").html();
	if("previous" == obj){
		if(parseInt(currentPage)-1 == 0){
			alert("当前已是第一页！");
		}else{
			load(parseInt(currentPage)-1);
			$("#currnetPage").html(parseInt(currentPage)-1);
			$("#pageTail").html(parseInt(currentPage)-1);
		}
		
	}else if("next" == obj){
		if(parseInt(currentPage) == totalPage){
			alert("当前已是最后一页！");
		}else{
			load(parseInt(currentPage)+1);
			$("#pageTail").html(parseInt(currentPage)+1);
		}
	}
	
}

function sqClick(obj,id,login){
	//obj:1取消审批2审批通过
	var json;
	if(obj == 1){
		json = {"id":id,"num":obj,"login":login};
	}else if(obj == 2){
		json = {"id":id,"num":obj,"login":login};
	}
	$.ajax({
		type: "post", 
		url: basePath+"/changeStatus/warrantySuccess",
		dataType:"json",
		data:json,
		success: function(data){
			console.log(data);
			var status = data.status;
			if(status == "0"){
				alert(data.message);
				
				load();
			}else{
				alert(data.message);
			}
		},
		error:function(data){
			alert("程序错误");
		}
	});
}
//审核-认证
function spClick(obj,id,loginAccounts){
	//obj:1取消审批2审批通过
	var json;
	if(obj == 1){
		json = {"id":id,"num":obj};
	}else if(obj == 2){
		json = {"id":id,"num":obj};
	}
	$.ajax({
		type: "post", 
		url: basePath+"/changeStatus/checkSuccess",
		dataType:"json",
		data:json,
		success: function(data){
			console.log(data);
			var status = data.status;
			if(status == "0"){
				alert(data.message);
				
				load();
			}else{
				alert(data.message);
			}
		},
		error:function(data){
			alert("程序错误");
		}
	});
}
//认证
function rzClick(obj,id,login){
	//obj:1取消认证2认证通过
	var json;
	if(obj == 1){
		json = {"id":id,"num":obj,"login":login};
	}else if(obj == 2){
		json = {"id":id,"num":obj,"login":login};
	}
	$.ajax({
		type: "post", 
		url: basePath+"/changeStatus/attestationSuccess",
		dataType:"json",
		data:json,
		success: function(data){
			console.log(data);
			var status = data.status;
			if(status == "0"){
				alert(data.message);
				load();
			}else{
				alert(data.message);
			}
		},
		error:function(data){
			alert("程序错误");
		}
	});
}
function datadel(){
	var list = $("table input[type='checkbox']:checked");
	var ids = "";
	for(var i=0;i<list.length;i++){
		var id = list[i].value+"";	
		if(id != ""){
			ids+=","+id;
		}
	}
	if(ids != ""){
		if(confirm("删除不可恢复！")==true){
			$.ajax({
				type: "post", 
				url: basePath+"/changeStatus/datadel",
				dataType:"json",
				data:{"ids":ids},
				success: function(data){
					console.log(data);
					var status = data.status;
					if(status == "0"){
						alert(data.message);
						load();
					}else{
						alert(data.message);
					}
				}
			});
		}
	}else{
		alert("请选择删除选项！");
	}
	

}
//查询
function queryByCondition(){
	load();
}


//

$('.table-sort').dataTable({
    "aaSorting": [[1, "desc"]],//默认第几个排序
    "bStateSave": true,//状态保存
    "aoColumnDefs": [
      { "orderable": false, "aTargets": [0, 8] }// 制定列不参与排序
    ]
});
 /*添加*/
function article_add(title, url, w, h) {
   layer_show(title,url,w,h);	
}
/*编辑*/
function article_edit(title, url, id, w, h) {
	 $("#table1 tr").click(function(){
		 $(this).children().children().first().attr("checked", true);
	 });
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
/*查看*/
function article_o(title, url, id, w, h) {
	 $("#table1 tr").click(function(){
		 $(this).children().children().first().attr("checked", true);
	 });
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}


function dingwei(){
	var pathStr='';
	var idStr='';
	$(":checkbox").each(function(){
	    if(this.checked == true){
	    	idStr+=$(this).parent().parent().children().first().children().val()+",";
	    	var path1=$(this).parent().parent().children().get(4).innerHTML;
	    	var path2=$(this).parent().parent().children().get(5).innerHTML;
	    	var path3=$(this).parent().parent().children().get(6).innerHTML;
	    	pathStr+=path1+path2+path3+"~`";
	    }
	 });
	if(idStr==null||idStr==''||pathStr==null||pathStr==''){
		alert("请选择要定位的机构");
	}else{
		$.ajax({
			ansyc:false,
			type:'post',
			url:basePath+'organization/organizationDingWei',
			dataType:'json',
			data:{"pathStr":pathStr,"idStr":idStr},
			success:function(result){
				alert(result.message);
				load();
			}
		});
	}

}