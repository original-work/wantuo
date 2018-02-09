$(function(){
	$("#table1 tr",window.parent.document).each(function(){
		if($(this).children().first().children().is(':checked')){
			$("#id").val($(this).children().first().children().val());
			$("#login_accounts").val($(this).children().get(1).innerHTML);
			$("#manage_name").val($(this).children().get(2).innerHTML);
		}
	});
});
function quxiao(){
	parent.window.location.reload(); 
	layer_close();
}

function updateById(){
	$.ajax({
		ansyc:false,
		type:'post',
		url:basePath+'manage/updateBySelect',
		dataType:'json',
		data:$("#form1").serializeArray(),
		success:function(result){
			alert(result.message);
			parent.window.location.reload(); 
			layer_close();
		}
	});
}