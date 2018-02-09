function queryList(){
	var json={
			"signinDateBegin":$("#signinDateBegin").val(),
			"signinDateEnd":$("#signinDateEnd").val(),
			"login_accounts":$("#login_accounts").val(),
			"pageIndex":parseInt($("#currentPage").html()),
			"pageSize":parseInt($("#pageSize").val())
	};
	$.ajax({
		ansyc:false,
		type:"post",
		url:basePath+"systemLog/queryParamList",
		dataType:"json",
		data:json,
		success:function(result){
			var count=result.count;
			var list=result.sysLogList;
			$(".count").html("共有数据："+count+"条");
			tbodyTHML(list);
			totalPage(count);
		}
	});
}

/*分页逻辑处理*/
function query(mark){
	var page=$("#currentPage").html();
	var total=$("#pageCount").html();
	if(mark=='previous'){
		if(1<page){
			$("#currentPage").html(parseInt(page)-1);
			$("#pageTail").html(parseInt(page)-1);
			 queryList();
		}else{
			alert("当前页已是第一页");
		}
	}else if(mark=='next'){
		if(page-total<0){
			$("#currentPage").html(parseInt(page)+1);
			$("#pageTail").html(parseInt(page)+1);
			 queryList();
		}else{
			alert("当前页已是最后一页！");
		}
	}else if(mark=='select'){
		$("#currentPage").html(1);
		$("#pageSize").val(10);
		queryList();
	}
}


function tbodyTHML(list){
	var html='';
	var tbody=document.getElementById("tbody");
	for (var i = 0; i < list.length; i++) {
		html+="<tr>"+
	            	"<td>"+list[i].login_accounts+"</td>"+
	            	"<td>"+list[i].name+"</td>"+
	            	"<td>"+list[i].create_date.substr(0,19)+"</td>"+
	            	"<td>"+list[i].operation_module+"</td>"+
	            	"<td>"+list[i].operation_function+"</td>"+
	            "</tr>";
	}
	tbody.innerHTML=html;
}
function totalPage(count){
	var pageSize=$("#pageSize").val();
	var a=count%pageSize;
	if(a==0){
		$("#pageCount").html(parseInt(count/pageSize));
	}else if(a>0){
		$("#pageCount").html(parseInt(count/pageSize)+1);
	}else{
		$("#pageCount").html(1);
	}
}
$(function(){
	queryList();
});