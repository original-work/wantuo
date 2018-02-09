function queryList(){
	var json={
			"loginAccounts":$("#organization").val(),
			"page":parseInt($("#currentPage").html()),
			"rows":parseInt($("#pageSize").val())
	};
	$.ajax({
		ansyc:false,
		type:"post",
		url:basePath+"student/baobiaoList",
		dataType:"json",
		data:json,
		success:function(result){
			var count=result.data.count;
			var list=result.data.sList;
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
		html+="<tr>"+
                "<td>"+list[i].name+"</td>"+
                "<td>"+list[i].grade+"</td>"+
                "<td>"+list[i].id+"</td>"+
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
				url:basePath+'feedBack/deleteById',
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

function deleteId(id){
		if(confirm("删除不可恢复！")==true){
			$.ajax({
				ansyc:false,
				type:'post',
				url:basePath+'feedBack/deleteById',
				dataType:'json',
				data:{'idStrs':id},
				success:function(result){
					if(result.status=='0'){
						alert(result.message);
						queryList();
					}
				},error:function(){
					
				}
			});
		}
}



function Export(){
	window.location.href=basePath+"student/GradeBaobiao.do?loginAccounts="+$("#organization").val()+"&page="+parseInt($("#currentPage").html())+"&rows="+parseInt($("#pageSize").val());
};


function myPrint(){
	$("#gradeTable").attr("border","1px");
    var newWindow=window.open("打印窗口","_blank");
    var table=document.getElementById("gradeReport");
    var docStr =table.innerHTML;
    newWindow.document.write(docStr);
    newWindow.document.close();
    newWindow.print();
    $("#gradeTable").attr("border","0px");
    newWindow.close();
}