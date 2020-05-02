function regist() {
    var uName=$("#uName").val();
    var uPassword=$("#ipwd").val();
    uPassword=hex_md5(uPassword);
    var uPasswordSure=hex_md5($("#i2pwd").val());
    uPasswordSure=hex_md5(uPasswordSure);
    var uMail=$("#uMail").val();
    var code=$("#code").val();
    var params="uName="+uName+"&uPassword="+uPassword+"&code="+code+"&uPasswordSure="+uPasswordSure+"&uMail="+uMail;
    $.ajax({
        'url':'regist',
        'data':params,
        type:"post",//请求方式
        'success':function(data)
        {
            if(data.code==200)
            {
                var token=data.token;
                var localStorage=window.localStorage;
                localStorage.token=token;
                alert(data.msg);
                top.location.href=data.add;
            }
            else{
                alert(data.msg);
                top.location.href=data.add;
            }
        }
    })
}



