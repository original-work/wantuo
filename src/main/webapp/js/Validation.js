//正确输入手机号
function validationFomr(obj,obj2){
	if(obj2=='phone'){
		 if(!(/^1[3|4|5|7|8]\d{9}$/.test(obj))){ 
		        alert("手机号码有误，请重填");
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
	var phone=$("#loginAccounts").val();
	var password=$("#password").val();
	if(!(/^1[3|4|5|7|8]\d{9}$/.test(phone))){
		return false;
	}else if(!/^(\w){6,20}$/.exec(password)){
		return false;
	}else{
		return true;
	}
}