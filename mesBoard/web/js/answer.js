
function giveComplain() {
    var param = location.search.substring(1, location.search.length);//通过url获取参数；
    param=param+"&complText="+$("#complText").val();
    $.ajax({
        url: 'complain ',
        data: param,
        type: 'post',
        success: function (data) {
            alert(data.msg);
            if (data.code = 200) {
                window.localStorage.token=data.token;
                top.location.href = "" + data.add;
            }
        },
        beforeSend: setHeader
    })
}

function  complain()
{
    var html="理由：<input type=\"text\" class=\"form-control\" rows=\"5\" placeholder=\"请输入举报理由\" id=\"complText\" name=\"complText\"/><br/><button</button>" +
             "<button type=\"button\" class=\"btn btn-warning\" onclick=\"giveComplain()\">提交理由</button>"
    $("#compl").html(html)
}


function blacklist()
{
    var param = location.search.substring(1, location.search.length);//通过url获取参数；
    $.ajax({
        url: 'blacklist ',
        data: param,
        type: 'post',
        success: function (data) {
            alert(data.msg);
            if (data.code = 200) {
                window.localStorage.token=data.token;
                top.location.href = "" + data.add;
            }
        },
        beforeSend: setHeader
    })
}

function dele(){
    var param = location.search.substring(1, location.search.length);//通过url获取参数；
    $.ajax({
        url: 'delete',
        data: param,
        type: 'post',
        success: function (data) {
            alert(data.msg);
            if (data.code = 200) {
                window.localStorage.token=data.token;
                top.location.href = "" + data.add;
            }
        },
        beforeSend: setHeader
    })
}

// function dele()
// {
//     var param = location.search.substring(1, location.search.length);//通过url获取参数；
//     $.ajax({
//         url: 'delete',
//         data: param,
//         type: 'post',
//         success: function (data) {
//             alert(data.msg);
//             if (data.code = 200) {
//                 window.localStorage.token=data.token;
//                 top.location.href = "" + data.add;
//             }
//         },
//         beforeSend: setHeader
//     })
// }


function answer() {
    var param = location.search.substring(1, location.search.length);//通过url获取参数；
    param = param+"&answer="+$("#answerText").val();
    $.ajax({
        url: 'answer',
        data: param,
        type: 'post',
        success: function (data) {
            alert(data.msg);
            if (data.code = 200) {
                window.localStorage.token=data.token;
                // top.location.href == "" + data.add;
            }
        },
        beforeSend: setHeader
    })
}

function setHeader(xhr){ // XmlHttpRequest
    xhr.setRequestHeader("Authorization",window.localStorage.token);
}

$(document).ready(function () {
    var temp=location.search.substring(1,location.search.length);
    // alert(temp);
    var tempArr=temp.split("&");
    // alert(tempArr);
    // var uName=tempArr[0].split("uName=");
    // alert(uName);
    var param=temp+"";
    alert(param);
    // $("#r1c1").html("测试使用");
    $.ajax({
        'url' : 'onequestion',
        'data': param,
        'type': "post",
        'success' : function(data){
            // var jsonarray= $.parseJSON(data);
            var html=data.question;
            $("#questionText").html(html);
            //
            // alert(data[0].question);
        },
    });
});
