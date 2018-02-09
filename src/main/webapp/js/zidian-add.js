$(function(){
	$("#type").val(window.parent.document.getElementById("type").value);
	$("#typeName").val(window.parent.document.getElementById("typeName").value);
});

function addSysCode(){
	$.ajax({
		ansyc:false,
		type:'post',
		url:basePath+"syscode/adminAddSysCode",
		dataType:'json',
		data:{"name":$("#name").val(),"parentId":$("#type").val(),"type":window.parent.document.getElementById("lx").value},
		success:function(result){
			alert(result.message);
			parent.window.queryList();
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
			parent.window.location.reload(); 
		}
	});
}