function article_save_submit(){
	var type;
	$(":radio").each(function(){
	    if(this.checked == true){
	    	type=$(this).val();
	    }
	});
	if(type==null||type==''){
		alert("请选择推送类型！");
		return;
	}
	if($("#note").val()==null||$("#note").val()==''){
		alert("请填写推送内容！");
		return;
	}
	if($("#note").val().length>400){
		alert("推送内容不能大于400字！");
		return;
	}
	$.ajax({
		ansyc : false,
		type : 'post',
		url : basePath + 'onePush/onePushSave',
		dataType : 'json',
		data : {
			"type":type,
			"note":$("#note").val()
		},
		success : function(result) {
			alert("推送成功！");
		}
	});

}
function layer_close(){
	$("#note").val("");
}