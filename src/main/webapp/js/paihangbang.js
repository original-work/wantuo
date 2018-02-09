$().ready(function(){
	load();
});   	 
function load(obj){
	var currentPage = obj;
	$.ajax({
		type: "post", 
		url: basePath+"/jigouguanli/queryList",
		dataType:"json",
		data:{"page":obj},
		success: function(data){
			var status = data.status;
			var totalSize = data.data.collectCount;
			if(status == "0"){
				var html="";
				for (var j=0;j<data.data.collectList.length;j++){
					var wsp_btn="";//未审批按钮代码
					var wrz_btn="";
					var sqy="";
					var check_status = data.data.collectList[j].check;
					var attestation = data.data.collectList[j].attestation;
					var warranty = data.data.collectList[j].warranty;
					//审批
					if(check_status=='1'){
						wsp_btn="未审批";
					}else if(check_status=='2'){
						wsp_btn="已审批";
						//认证
						if(attestation=='1'){
							wrz_btn="未认证";
							sqy="未生效";
						}else if(attestation=='2'){
							wrz_btn="已认证";
							if(warranty=='1'){
								sqy="失效";
							}else if(warranty=='2'){
								sqy="生效";
							}
						}
					}
					html +="<tr class='text-c'>";
					html +="<td><input type='checkbox' onclick='paiming($(this))' value='"+data.data.collectList[j].loginAccounts +"' name=''></td>";
					html +="<td>"+data.data.collectList[j].sort+"</td>";
					html +="<td>"+data.data.collectList[j].loginAccounts+"</td>";
					html +="<td>"+data.data.collectList[j].organizationAbbreviation+"</td>";
					html +="<td>"+data.data.collectList[j].organization+"</td>";
					html +="<td>"+data.data.collectList[j].address+"</td>";
					html +="<td>"+data.data.collectList[j].organizationContacts+"</td>";
					html +="<td>"+data.data.collectList[j].phone+"</td>";
					html +="<td>"+data.data.collectList[j].evaluate+"</td>";
					html +="<td class='f-14 td-manage' >"+wrz_btn;
					html +="<td class='f-14 td-manage' >"+sqy;
					html +="<td class='f-14 td-manage' >"+wsp_btn;
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
			$("#currnetPage").html(parseInt(currentPage)+1);
			$("#pageTail").html(parseInt(currentPage)+1);
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
