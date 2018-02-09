$(function(){
	$("#table1 tr",window.parent.document).each(function(){
		if($(this).children().first().children().is(':checked')){
			$("#typeName").val($(this).children().get(1).innerHTML);
			$("#id").val($(this).children().get(2).innerHTML);
			$("#name").val($(this).children().get(3).innerHTML);
		}
	});
});
function quxiao(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.$('.btn-refresh').click();
	parent.layer.close(index);
}


function updateById(){
	$.ajax({
		ansyc:false,
		type:'post',
		url:basePath+'syscode/updateById',
		dataType:'json',
		data:$("#form1").serializeArray(),
		success:function(result){
			alert(result.message);
			parent.window.queryList();
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
}
