/**
 * 
 */
	$("#loginForm").validate({
		rules : {
			"validate" : {
				required : true,
				remote : {
					type : "post",
					url : "codeValidate.action",
					dataType : "json",
					data : {
						validateCode : function() {
							return $("#validateCode").val();
						}
					}
				}
			}
		},
		messages : {
			"validate" : {
				required : ' 请输入验证码',
				remote : '验证码错误'
			}
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.next());
		}
	});