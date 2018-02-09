function sx(name){
	$("table input[type='checkbox']:checked").parent().parent().children('td').eq(3).text(name);
}
function queryList(){

	if($("#type").val()==0){
		var json={
				"type":$("#type").val(),
				"pageIndex":parseInt($("#currentPage").html()),
				"pageSize":parseInt($("#pageSize").val())
		};
	}else{
		var json={
				"parentId":$("#type").val(),
				"pageIndex":parseInt($("#currentPage").html()),
				"pageSize":parseInt($("#pageSize").val())
		};
	}
	$.ajax({
		ansyc:false,
		type:"post",
		url:basePath+"syscode/QueryByList",
		dataType:"json",
		data:json,
		success:function(result){
			var count=result.data.count;
			var list=result.data.sysCodeList;
			$(".CountSize").html("共有数据："+count+"条");
			$("#pageCount").val(count);
			tbodyTHMLg(list);
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
		html+=" <tr class=\"text-c\">"+
                "<td><input type=\"checkbox\" name=\"id\"  id=\"id\" value="+list[i].id+"></td>"+
                "<td>"+$("#typeName").val()+"</td>"+
                "<td>"+list[i].id+"</td>"+
                "<td>"+list[i].name+"</td>"+
        		"<td class=\"f-14 td-manage\"> <a style=\"text-decoration:none\"  class=\"ml-5\" onclick=\"article_edit('编辑','zidian-edit.jsp','800')\" href=\"javascript:;\" title=\"编辑\"><i class=\"Hui-iconfont\"></i>编辑</a> <a style=\"text-decoration:none\" class=\"ml-5\" onclick=\"caozuo($(this))\" href=\"javascript:;\" title=\"删除\"><i class=\"Hui-iconfont\"></i>删除</a></td>"+
        	 "</tr>";
	}
	tbody.innerHTML=html;
}

function tbodyTHMLg(list){
	var html1='';
	if($("#type").val()==0){
		for (var i = 0; i < list.length; i++) {
			html1+="<li class=\"li01\" id=\"dakaili"+list[i].id+"\"><span onclick=\"dakai('" + list[i].id + "','"+list[i].name+"')\" >"+list[i].name+"</span></li>";
		}	
		all.innerHTML=html1;
	}else{
		html1 +="<ul class=\"li_01\" name=\""+$("#type").val()+"\"  style=\"display: block;\">";
		for (var i = 0; i < list.length; i++) {
			html1+="<li class=\"li01\" id=\"dakaili"+list[i].id+"\"><span onclick=\"dakai('" + list[i].id + "','"+list[i].name+"')\" >"+list[i].name+"</span></li>";
		}	
		html1 +="</ul>";
		var id="dakaili"+$("#type").val();
		$("#"+id).html("<span onclick=\"dakai('" +  $("#type").val() + "','"+ $("#typeName").val()+"')\" >"+ $("#typeName").val()+"</span>"+html1);
	}


}
function dakai(i,name){
	if($("#lx").val()==0){
		$("#lx").val(i);
	}
	$("#type").val(i);
	$("#typeName").val(name);
	queryList();
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
				url:basePath+'syscode/deleteSysCode',
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
			url:basePath+'syscode/deleteSysCode',
			dataType:'json',
			data:{'idStrs':a},
			success:function(result){
				queryList();
				alert(result.message);
			}
		});
	}
}
function article_edit(title, url,w, h) {
	 $("#table1 tr").click(function(){
		 $(this).children().children().first().attr("checked", true);
	 });
   layer_show(title,url,w,h);
}
function article_add(title, url, w, h) {
    layer_show(title,url,w,h);	
 }