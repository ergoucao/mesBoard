$(function(){
                $("#image").click(function(){
            $("#uploadfile").click();
            })
            $("#uploadfile").change(function(){
            	var animateimg = $("#file").val(); //获取上传的图片名 带//
		    
			    var files=this.files[0];    //获取文件信息
			    var fileSize = files.size;  //获取上传的文件大小
			    var maxSize = 1048576;      //最大1MB
			    
         	    if(parseInt(fileSize) >= parseInt(maxSize))
			    {
        			alert('上传的文件不能超过1MB');
      				return false;
      			}
              	var files=this.files[0];    //获取文件信息
                if(files!=null)
                {
                	// alert("show");
                    var reader=new FileReader();  //调用FileReader
	                reader.onload = function(e) {
	                    console.log(e); //查看对象
	                    console.log(this.result);//要的数据 这里的this指向FileReader（）对象的实例reader
	                    image.setAttribute("src", this.result)
	                }
                	reader.readAsDataURL(files); //将文件读取为 DataURL(base64)
                    // alert("上传成功");
                }
                // else{
                //     alert("上传失败");
                // }
            })
})

function setHeader(xhr){ // XmlHttpRequest
    xhr.setRequestHeader("Authorization",window.localStorage.token);
}


function givefile(){
//		var formdata=new FormData($("#formf").[0]);//获取文件法一
		//var formdata=new FormData( ); 
		//formdata.append("file" , $("#file")[0].files[0]);//获取文件法二
	  var form=document.getElementById("formf");
      var formdata=new FormData(form);
       $.ajax({
           type : 'post',
           url : 'upfile',
           data : formdata,
           mimeType:"multipart/form-data",
           dataType:"text",
           cache : false,
           contentType: false,
           processData : false, // 不处理发送的数据，因为data值是Formdata对象，不需要对数据做处理
           'success' : function(data){
                alert("上传成功");
           		// if (data.code==200)
                // {
                //     alert("上传成功");
                // }
           		// else
                // {
                //     alert("上传失败");
                // }
           },
           beforeSend: setHeader
    })
}

$(document).ready(function(){
    $('#Name').on('input propertychange', function() {
        //input propertychange即实时监控键盘输入包括粘贴
        var uName = $.trim($(this).val());
        $("#UNameCheck").html("<font color='red'>用户名不能为空</font>");
        if (uName=="")
            $("#UNameCheck").html("<font color='red'>用户名不能为空</font>");
        else if(uName.length>20)
            $("#UNameCheck").html("<font color='red'>用户名不能长度超过20</font>");
        else
            $("#UNameCheck").html("<font color='red'></font>");
    })
})




function chaName() {
    var uName=$("#Name").val();
    var param="changeName="+uName;
    $.ajax({
        type : 'post',
        url : 'chaName',
        data : param,
        'success' : function(data){
            var localStorage=window.localStorage;
            localStorage.token=data.token;
            alert(data.msg);
        },
        beforeSend: setHeader
    })
}

function chaPwd()
{
    var pwdC=$("#pwdC").val();
    pwdC=hex_md5(pwdC);
    var pwdC1=$("#pwdC1").val();
    pwdC1=hex_md5(pwdC1);
    var pwdC2=$("#pwdC2").val();
    pwdC2=hex_md5(pwdC2);
    var param="uPassword="+pwdC+"&uPasswordCha="+pwdC1;
    $.ajax({
        type : 'post',
        url : 'chaPwd',
        data : param,
        'success' : function(data){
            if (data.code==200)
            {
                var localStorage=window.localStorage;
                localStorage.token=data.token;
            }
            alert(data.msg);
        },
        beforeSend: setHeader
    })
}

$(document).ready(function(){
    $('#pwdC1').on('input propertychange', function() {
        //input propertychange即实时监控键盘输入包括粘贴
        var pwd = $.trim($(this).val());
        //获取this，即ipwd的val()值，trim函数的作用是去除空格
        var rpwd = $.trim($("#pwdC2").val());
        var reg =new RegExp("^[0-9]*$");

        if (pwd.length<8)
        {
            $("#passwordCheck").html("<font color='red'>密码必须为大于8位的数字和字母和其他字符组合</font>");
        }
        else if (/^\d+$/.test(pwd))
        {
            $("#passwordCheck").html("<font color='red'>密码不能为纯数字</font>");
        }
        else
        {
            $("#passwordCheck").html("<font color='red'></font>");
        }
        if(rpwd!=""){
            if(pwd==""&&rpwd==""){
                //若都为空，则提示密码不能为空，为了用户体验（在界面上用required同时做了处理）
                $("#msg_pwd").html("<font color='red'>密码不能为空</font>");
            }
            else{
                if(pwd==rpwd){                                 //相同则提示密码匹配
                    $("#passwordCheck").html("<font color='green'>两次密码匹配通过</font>");
                    $("#changePwd").attr("disabled",false); //使按钮无法点击
                }else{                                          //不相同则提示密码匹配
                    $("#passwordCheck").html("<font color='red'>两次密码不匹配</font>");
                    $("#changePwd").attr("disabled",true);
                }
            }}
    })
})

//由于是两个输入框，所以进行两个输入框的几乎相同的判断
$(document).ready(function(){
    $('#pwdC2').on('input propertychange', function() {
        var pwd = $.trim($(this).val());
        var rpwd = $.trim($("#pwdC1").val());
        if(pwd==""&&rpwd==""){
            $("#msg_pwd").html("<font color='red'>密码不能为空</font>");
        }
        else{
            if(pwd==rpwd){
                $("#passwordCheck").html("<font color='green'>两次密码匹配通过</font>");
                $("#changePwd").attr("disabled",false);
            }else{
                $("#passwordCheck").html("<font color='red'>两次密码不匹配</font>");
                $("#changePwd").attr("disabled",true);
            }
        }
    })
})



function changeEmail()
{
    var uPassword=$("#uPassword").val();
    uPassword=hex_md5(uPassword);
    var uMail=$("#uMail").val();
    var param="uPassword="+uPassword+"&uMail="+uMail;

    $.ajax({
        url : "changeEmail",
        data : param,
        type : "post",
        success : function(data) {
            if (data.code==200)
            {
                alert("修改成功您将收到一份激活邮件");
            }
            else
            {
                alert("激活失败");
            }
        },
        beforeSend : setHeader
    })
}


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


function openAsk() {
    var param="acceptQues=true";
    $.ajax({
        url : "changeAcceptQues",
        data : param,
        type : "post",
        success : function(data) {
            if (data.code==200)
            {
                alert(data.msg);
            }
            else
            {
                alert("更改失败");
            }
        },
        beforeSend : setHeader
    })
}

function closeAsk() {
    var param="acceptQues=false";
    $.ajax({
        url : "changeAcceptQues",
        data : param,
        type : "post",
        success : function(data) {
            if (data.code==200)
            {
                alert(data.msg);
            }
            else
            {
                alert("更改失败");
            }
        },
        beforeSend : setHeader
    })
}