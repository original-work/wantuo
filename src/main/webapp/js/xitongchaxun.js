function queryList(){
	var json={
			"login_accounts":$("#login_accounts").val(),
			"page":parseInt($("#currentPage").html()),
			"rows":parseInt($("#pageSize").val())
	};
	$.ajax({
		ansyc:false,
		type:"post",
		url:basePath+"manage/queryByList",
		dataType:"json",
		data:json,
		success:function(result){
			var count=result.count;
			var list=result.manageList;
			$(".CountSize").html("共有数据："+count+"条");
			tbodyTHML(list);
			totalPage(count);
			//$("#pageCount").val(count);
		},error:function(result){
			
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
		html+=" <tr class=\"text-c\">"+
                "<td><input type=\"checkbox\" name=\"id\"  id=\"id\" value="+list[i].id+"></td>"+
                "<td>"+list[i].login_accounts+"</td>"+
                "<td>"+list[i].manage_name+"</td>"+
                "<td>"+list[i].last_login_date.substr(0,19)+"</td>"+
        		"<td class=\"f-14 td-manage\"> <a style=\"text-decoration:none\"  class=\"ml-5\" onclick=\"article_edit('编辑','xitong-edit.jsp','800')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\"></i>编辑</a> <a style=\"text-decoration:none\" class=\"ml-5\" onclick=\"caozuo($(this))\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\"></i>删除</a></td>"+
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



/*删除*/
function deleteById(){
	var idStrs=$("table input[type='checkbox']:checked");
	var ids='';
	if(idStrs.length>0){
		for (var int = 0; int < idStrs.length; int++){
			
			ids+=idStrs[int].value+",";
		}
		if(confirm("删除不可恢复！")==true){
			$.ajax({
				ansyc:false,
				type:'post',
				url:basePath+'manage/deleteManageById',
				dataType:'json',
				data:{'idStrs':ids},
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


function caozuo(b){
	var a=b.parent().parent().children().first().children().val();
	if(confirm("删除不可恢复！")==true){
		$.ajax({
			ansyc:false,
			type:'post',
			url:basePath+'manage/deleteManageById',
			dataType:'json',
			data:{'idStrs':a},
			success:function(result){
				queryList();
				alert(result.message);
			}
		});
	}
}
