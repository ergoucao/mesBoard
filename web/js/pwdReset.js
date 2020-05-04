function pwdReset() {
    var uMail=$("#uMail").val();
    var params="uMail="+uMail;
    $.ajax({
        'url':'pwdReset',
        'data':params,
        type : "post",//请求方式
        'success':function(data)
        {
            if(data.code==200)
            {
                alert("已发送密码修改邮件至您的邮箱");
                top.location.href=data.add;
            }
            else{
                alert("邮箱错误");
            }
        }
    })
}