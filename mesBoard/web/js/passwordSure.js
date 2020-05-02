$(document).ready(function(){
    $('#ipwd').on('input propertychange', function() {                                
                               //input propertychange即实时监控键盘输入包括粘贴
        var pwd = $.trim($(this).val());                                          
                               //获取this，即ipwd的val()值，trim函数的作用是去除空格
        var rpwd = $.trim($("#i2pwd").val());
        var reg =new RegExp("^[0-9]*$");
        
        if (pwd.length<8)
        {
            $("#msg_pwd1").html("<font color='red'>密码必须为大于8位的数字和字母和其他字符组合</font>");
        }
        else if (/^\d+$/.test(pwd))
        {
            $("#msg_pwd1").html("<font color='red'>密码不能为纯数字</font>");
        }
		else 
		{
			$("#msg_pwd1").html("<font color='red'></font>");
		}
        if(rpwd!=""){
        if(pwd==""&&rpwd==""){                 
                //若都为空，则提示密码不能为空，为了用户体验（在界面上用required同时做了处理）
            $("#msg_pwd").html("<font color='red'>密码不能为空</font>");
        }
        else{
            if(pwd==rpwd){                                 //相同则提示密码匹配
            $("#msg_pwd").html("<font color='green'>两次密码匹配通过</font>");
            $("#btn_register").attr("disabled",false); //使按钮无法点击
            }else{                                          //不相同则提示密码匹配
            $("#msg_pwd").html("<font color='red'>两次密码不匹配</font>");
            $("#btn_register").attr("disabled",true);
        }
        }}
    })
})
 
//由于是两个输入框，所以进行两个输入框的几乎相同的判断
$(document).ready(function(){
        $('#i2pwd').on('input propertychange', function() {
            var pwd = $.trim($(this).val());
            var rpwd = $.trim($("#ipwd").val());
            if(pwd==""&&rpwd==""){
                $("#msg_pwd").html("<font color='red'>密码不能为空</font>");
            }
            else{
                if(pwd==rpwd){
                $("#msg_pwd").html("<font color='green'>两次密码匹配通过</font>");
                $("#btn_register").attr("disabled",false);
                }else{
                $("#msg_pwd").html("<font color='red'>两次密码不匹配</font>");
                $("#btn_register").attr("disabled",true);
            }
            }
        })
    })


$(document).ready(function(){
    $('#uName').on('input propertychange', function() {                                
                               //input propertychange即实时监控键盘输入包括粘贴
        var uName = $.trim($(this).val());  
		$("#msg_pwd2").html("<font color='red'>用户名不能为空</font>");
		                                       
        if (uName=="")
        	$("#msg_pwd2").html("<font color='red'>用户名不能为空</font>");
        else if(uName.length>20)
            $("#msg_pwd2").html("<font color='red'>用户名不能长度超过20</font>");
        else
        	$("#msg_pwd2").html("<font color='red'></font>");
    })
})


$(document).ready(function(){
    $('#uMail').on('input propertychange', function() {
        //input propertychange即实时监控键盘输入包括粘贴
        var uMail = $.trim($(this).val());
        var reg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
        if (uMail=="")
            $("#msg_uMail").html("<font color='red'>邮箱不能为空</font>");
        else if (!reg.test(uMail))
        {
            $("#msg_uMail").html("<font color='red'>邮箱格式错误</font>");
        }
        else
            $("#msg_uMail").html("<font color='red'></font>");
    })
})
