function addManage(){
	if($("#login_accounts").val()==null||$("#login_accounts").val()==''){
		alert("用户名不能为空！");
		return ;
	}
	if($("#password").val()==null||$("#password").val()==''){
		alert("帐号密码不能为空！");
		return ;
	}
	if($("#readyPassword").val()==null||$("#readyPassword").val()==''){
		alert("确认密码不能为空！");
		return ;
	}else{
		if($("#readyPassword").val()!=$("#password").val()){
			alert("两次输入密码不相同");
			return;
		}
	}
	if(isOk()){
		$.ajax({
			ansyc:false,
			type:'post',
			url:basePath+'manage/insertMange',
			dataType:'json',
			data:$("#form1").serializeArray(),
			success:function(result){
				if(result.status=='0'||result.status=='1'){
					alert(result.message);
					parent.window.location.reload();
					var index = parent.layer.getFrameIndex(window.name);
					parent.$('.btn-refresh').click();
					parent.layer.close(index);	 
				}
			}
		});
	}else{
		alert("数据填写有误，请重新填写！");
	}
	
}

function validationFomr(obj,obj2){
	if(obj2=='phone'){
		 if(obj.match(/^[0-9a-zA-Z]*$/)==null||obj.match(/^[0-9a-zA-Z]*$/)==''){ 
		        alert("用户名输入格式有误，请重填");
		        return ;
		    }
	}else if(obj2=='password'){
		var patrn=/^(\w){6,20}$/;    
		if (!patrn.exec(obj)){
			alert("密码格式不正确！");
			 return;
		}
	} 
}
function isOk(){
	var phone=$("#login_accounts").val();
	var password=$("#password").val();
	if(!(/^[a-zA-z]\w{3,15}$/.test(phone))){
		return false;
	}else if(!/^(\w){6,20}$/.exec(password)){
		return false;
	}else{
		return true;
	}
}