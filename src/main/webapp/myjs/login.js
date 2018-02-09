$(function(){
		$("#form-member-add").Validform({
			btnSubmit:"#submit", 
			tiptype:4,
			callback:function(form){
				$.ajax({
					type:"POST",
			    	async: false,
			   	 	url:basePath+"login/manageLogin",
			   		data :{
			   			"login_accounts":$("#login_accounts").val(),
			   			"password":$("#password").val()
			   		},
			   		dataType: "json",
			   		success:function(data){
			   			if(data.status==0){
//			   				parent.layer.msg(data.message);
			   				window.location.href=basePath+'views/index.jsp';
//			   				setTimeout("window.location.href='/views/index.jsp'",200);
			   			}else{
				   			alert(data.message);
			   			}
			   		}
				});
			}
		});	
		$("#cancel").click(function(){
			$("#login_accounts").value="";
			$("#password").value="";
		});
	});