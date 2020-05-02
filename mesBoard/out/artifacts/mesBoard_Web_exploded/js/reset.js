var paramobj = {};
var parameterURL={};
window.onload = function () {
    //此时parameterURL为:name=张三&age=25
    parameterURL = location.search.substring(1, location.search.length);
    //此时paramArr为一个数组:["name=张三","age=25"]
    // var paramArr = parameterURL.split("&");
    // var key,value,temp;
    // //定义2个变量接收key，value并存到paramobj中
    // for (i = 0; i < paramArr.length; i++) {
    //     temp = paramArr[i].split("=");
    //     if (temp.length === 1) {
    //         paramobj[temp[0]] = "";
    //     }
    //     else if(temp.length>1){
    //         for (j = 0; j < temp.length; j++) {
    //             paramobj[temp[0]] = decodeURIComponent(temp[1]);
    //         }
    //     }
    // }
    // //此时参数已全部保存至paramobj中,
    // var showStr="";
    // for (var a in paramobj) {
    //     showStr += (a + ":" + paramobj[a]);
    // }
    // document.getElementById("showParam").innerText = parameterURL;
}

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
            $("#msg_pwd1").html("<font color='red'>密码不能为空</font>");
        }
        else{
            if(pwd==rpwd){
                $("#msg_pwd1").html("<font color='green'>两次密码匹配通过</font>");
                $("#btn_register").attr("disabled",false);
            }else{
                $("#msg_pwd1").html("<font color='red'>两次密码不匹配</font>");
                $("#btn_register").attr("disabled",true);
            }
        }
    })
})
//这里显示到div
function reset(){
    var uPassword=$("#ipwd").val();
    uPassword=hex_md5(uPassword);
    var params=parameterURL+"&pwdReset="+uPassword;
    $.ajax({
        url:'pwdDataReset',
        data:params,
        type:"post",
        'success':function (data) {
            if (data.code==200)
            {
                alert("重置成功");
                top.location.href=data.add;
            }
            else
            {
                alert(data.msg);
            }
        }
    })
}