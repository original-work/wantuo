function queryList(){
	var json={
			"loginAccounts":$("#loginAccounts").val(),
			"page":parseInt($("#currentPage").html()),
			"rows":parseInt($("#pageSize").val())
	};
	$.ajax({
		ansyc:false,
		type:"post",
		url:basePath+"Patriarch/QueryByList",
		dataType:"json",
		data:json,
		success:function(result){
			var count=result.data.count;
			var list=result.data.patriarchList;
			$(".CountSize").html("共有数据："+count+"条");
			$("#pageCount").val(count);
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
		var statusName='';
		var statusName2='';
		if(list[i].status==1){
			statusName='禁用';
			statusName2='启用';
		}
		if(list[i].status==2){
			statusName='启用';
			statusName2='禁用';
		}
		html+="<tr class=\"text-c\">"+
                "<td><input type=\"checkbox\" name=\"id\"  id=\"id\" value="+list[i].loginAccounts+"></td>"+
                "<td>"+list[i].loginAccounts+"</td>"+
                "<td>"+list[i].phone+"</td>"+
                "<td><a style=\"text-decoration:none\" title="+statusName+">"+statusName+"</a></td>"+
        		"<td class=\"f-14 td-manage\"> <a style=\"text-decoration:none\" onClick=\"caozuo($(this),'"+list[i].id+"')\" class=\"ml-5\" href=\"javascript:;\" title="+statusName2+"><i class=\"Hui-iconfont\">"+statusName2+"</i></a> <a style=\"text-decoration:none\" class=\"ml-5\"  href=\"javascript:;\" title=\"删除\" onClick=\"caozuo($(this),'"+list[i].id+"')\"><i class=\"Hui-iconfont\"></i>删除</a></td>"+
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
				url:basePath+'Patriarch/deleteById',
				dataType:'json',
				data:{'idStrs':ids},
				success:function(result){
					if(result.status=='0'){
						alert(result.message);
						queryList();
					}
				},error:function(){
					
				}
			});
		}
	}else{
		alert("请选择要删除的信息");
	}
}
	function caozuo(b,id){
		var a=b.attr("title");
		var num='';
		var loginAccounts=b.parent().parent().children().first().children().val();
		if(a=='禁用'){
			num='jinyong';
		}
		if(a=='启用'){
			num='qiyong';		
		}
		if(a=='删除'){
			num='shanchu';
		}
		$.ajax({
		ansyc:false,
		type:'post',
		url:basePath+'Patriarch/jinyongOrqiyong',
		dataType:'json',
		data:{"loginAccounts":loginAccounts,"num":num,"id":id},
		success:function(result){
			alert(result.message);
			queryList();
		}
	});
}

