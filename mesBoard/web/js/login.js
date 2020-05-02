function setHeader(xhr){ // XmlHttpRequest
    xhr.setRequestHeader("Authorization",window.localStorage.token);
}

function login() {
    var uName=$("#uName").val();
    var uPassword=$("#uPassword").val();
    uPassword=hex_md5(uPassword);
    var code=$("#code").val();
    var params="uName="+uName+"&uPassword="+uPassword+"&code="+code;
    $.ajax({
        'url':'login',
        'data':params,
        type : "post",//请求方式
        'success':function(data)
        {
            if(data.code==200)
            {
                var token=data.token;
                var localStorage=window.localStorage;
                localStorage.token=token;
                alert("登录成功！！");
                top.location.href=data.add;
            }
            else{
                alert(data.msg);
            }
        },
        'beforeSend' : setHeader
    })
}


function  redMain(data) {
    $.ajax({
        'url':'main.html',
        type : "post",//请求方式
        'success':function(data)
        {
            if(data.code==200)
            {
                var token=data.token;
                var localStorage=window.localStorage;
                localStorage.token=token;
                top.location.href=="main.html";
            }
            else{
                alert(data.msg);
            }
        },
        'beforeSend' : setHeader
    })
}