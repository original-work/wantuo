function queryList(){
	var json={
			"organizationAccounts":$("#organizationAccounts").val(),
			"evaluatePerson":$("#evaluatePerson").val(),
			"evaluate":parseInt($("#evaluate").val()),
			"pageIndex":parseInt($("#currentPage").html()),
			"pageSize":parseInt($("#pageSize").val())
	};
	$.ajax({
		ansyc:false,
		type:"post",
		url:basePath+"evaluate/adminQueryByList",
		dataType:"json",
		data:json,
		success:function(result){
			var count=result.data.count;
			var list=result.data.evaluateList;
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
		if(page<total){
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
		html+="<tr class=\"text-c\">"+
                           "<td>"+
                             "<input type=\"checkbox\" value="+list[i].id+"></td>"+
                           "<td>"+list[i].organizationAccounts+"</td>"+
                           "<td>"+list[i].evaluate+"</td>"+
                           "<td>"+list[i].evaluatePerson+"</td>"+
                           "<td>"+list[i].evaluateDetails+"</td>"+
                           "<td class=\"f-14 td-manage\"><a style=\"text-decoration:none\" class=\"ml-5\" onClick=\"deleteById($(this))\"  title=\"删除\"><i class=\"Hui-iconfont\">&#xe6e2;</i>删除</a></td>"+
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


function deleteById(b){
	var a=b.parent().parent().children().first().children().val();
	if(confirm("删除不可恢复！")==true){
		$.ajax({
			ansyc:false,
			type:'post',
			url:basePath+'evaluate/deleteById',
			dataType:'json',
			data:{'idStr':a},
			success:function(result){
				alert(result.message);
				queryList();
			}
		});
	}
}


function deleteByIdStr(){
	var idStr=$("table input[type='checkbox']:checked");
	var ids='';
	if(idStr.length>0){
		for (var int = 0; int < idStr.length; int++){
			ids+=idStr[int].value+",";
		}
		if(confirm("删除不可恢复！")==true){
			$.ajax({
				ansyc:false,
				type:'post',
				url:basePath+'evaluate/deleteById',
				dataType:'json',
				data:{'idStr':ids},
				success:function(result){
					alert(result.message);
					queryList();
				},error:function(){
					
				}
			});
		}
	}else{
		alert("请选择要删除的信息");
	}
}