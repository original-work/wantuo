
/*页面初始化获取数据*/
function queryList(){
	/*var locationName=$("#location").val();
	if(locationName==null||locationName==''){
		locationName=null;
	}else{
		locationName=$("#locationName").find("option:selected").text();
	}*/
	var json={
			"schoolName":$("#schoolName").val(),
			"location":$("#location").val(),
			"page":parseInt($("#currentPage").html()),
			"rows":parseInt($("#pageSize").val())
	};
	$.ajax({
		ansyc:false,
		type:"post",
		url:basePath+"/school/QueryByList",
		dataType:"json",
		data:json,
		success:function(result){
			var count=result.data.count;
			var list=result.data.schoolList;
			$(".CountSize").html("共有数据："+count+"条");
			$("#pageCount").val(count);
			tbodyTHML(list);
			totalPage(count);
		},error:function(result){
			
		}
	});
}
/*分页逻辑处理*/
function query(mark){
	var page=$("#currentPage").html();
	var total=$("#pageCount").html();
	if(mark=='previous'){
		if(1<parseInt(page)){
			$("#currentPage").html(parseInt(page)-1);
			$("#pageTail").html(parseInt(page)-1);
			 queryList();
		}else{
			alert("当前页已是第一页");
		}
	}else if(mark=='next'){
		if(parseInt(page)<parseInt(total)){
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
		var dingwei='';
		if(list[i].coordinateX>0){
			dingwei='已定位';
		}else{
			dingwei='未定位';
		}
		html+=" <tr class=\"text-c\">"+
                "<td><input type=\"checkbox\" name=\"id\"  id=\"id\" value="+list[i].id+"></td>"+
                "<td>"+list[i].schoolName+"</td>"+
                "<td>"+list[i].fullName+"</td>"+
                "<td>"+list[i].locationName+"</td>"+
                "<td>"+list[i].bairroName+"</td>"+
                "<td>"+list[i].address+"</td>"+
                "<td>"+list[i].propertyName+"</td>"+
                "<td>"+dingwei+"</td>"+
                "<td style=\"display:none;\">"+list[i].schoolContacts+"</td>"+
                "<td style=\"display:none;\">"+list[i].phone+"</td>"+
                "<td style=\"display:none;\">"+list[i].email+"</td>"+
                "<td style=\"display:none;\">"+list[i].location+"</td>"+
                "<td style=\"display:none;\">"+list[i].bairro+"</td>"+
        		"<td class=\"f-14 td-manage\"> <a style=\"text-decoration:none\"  class=\"ml-5\" onclick=\"article_edit('编辑','xuexiao-edit.jsp','800')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\"></i>编辑</a> <a style=\"text-decoration:none\" class=\"ml-5\" onclick=\"caozuo($(this))\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\"></i>删除</a></td>"+
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
				url:basePath+'school/deleteBySchoolId',
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
function caozuo(b){
	var a=b.parent().parent().children().first().children().val();
	if(confirm("删除不可恢复！")==true){
		$.ajax({
			ansyc:false,
			type:'post',
			url:basePath+'school/deleteBySchoolId',
			dataType:'json',
			data:{'idStrs':a},
			success:function(result){
				queryList();
				alert(result.message);
			}
		});
	}
}





function dingwei(){
	var pathStr='';
	var idStr='';
	$(":checkbox").each(function(){
	    if(this.checked == true){
	    	idStr+=$(this).parent().parent().children().first().children().val()+",";
	    	var path1=$(this).parent().parent().children().get(3).innerHTML;
	    	var path2=$(this).parent().parent().children().get(4).innerHTML;
	    	var path3=$(this).parent().parent().children().get(5).innerHTML;
	    	pathStr+=path1+path2+path3+"~`";
	    }
	 });
	if(idStr==null||idStr==''||pathStr==null||pathStr==''){
		alert("请选择要定位的学校");
	}else{
		$.ajax({
			ansyc:false,
			type:'post',
			url:basePath+'school/schoolDingWei',
			dataType:'json',
			data:{"pathStr":pathStr,"idStr":idStr},
			success:function(result){
				alert(result.message);
				queryList();
			}
		});
	}
}