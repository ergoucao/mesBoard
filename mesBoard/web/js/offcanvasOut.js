var tempArr="";

function setHeader(xhr){ // XmlHttpRequest
    xhr.setRequestHeader("Authorization",window.localStorage.token);
}

function  allQues() {
    var temp=location.search.substring(1,location.search.length);
    // alert(temp);
    var tempArr=temp.split("&");
    // alert(tempArr);
    // var uName=tempArr[0].split("uName=");
    // alert(uName);
    var param=tempArr+"";
    // alert(param);
    // $("#r1c1").html("测试使用");
    $.ajax({
        'url' : 'question',
        'data': param,
        'type': "post",
        'success' : function(data){
            // var jsonarray= $.parseJSON(data);
            var html="";
            var temp="";
            for (var i=0;i<data.length;i++)
            {
                var tem_answer;
                if (data[i].answer==null) {
                    tem_answer = "未回复";
                }
                else
                {
                    tem_answer=data[i].answer;
                }

                    temp=tempArr+"&questionId="+data[i].id;
                    html=html+"\n          <div class=\"col-xs-6 col-lg-4\" >\n" ;

                    if (data[i].questioner!="")
                    {
                        html=html+"            <p>提问者"+(i+1)+":"+data[i].questioner+"</p>\n";
                    }
                     html=html+   "            <p>问题"+(i+1)+":"+data[i].question+"</p>\n" +
                        "            <p>回复："+tem_answer+"</p>\n" +
                        "            <p><a class=\"btn btn-default\" href=\"answer.html?"+temp+"\""+"role=\"button\">View details &raquo;</a></p>\n" +
                        "          </div><!--/.col-xs-6.col-lg-4-->"
            }
            $("#question").html(html);
            //
            // alert(data[0].question);
        },
        beforeSend: setHeader
    });
}

function  alrDelete() {
    var temp=location.search.substring(1,location.search.length);
    // alert(temp);
    var tempArr=temp.split("&");
    // alert(tempArr);
    // var uName=tempArr[0].split("uName=");
    // alert(uName);
    var param=tempArr+"";
    // alert(param);
    // $("#r1c1").html("测试使用");
    $.ajax({
        'url' : 'question',
        'data': param,
        'type': "post",
        'success' : function(data){
            // var jsonarray= $.parseJSON(data);
            var html="";
            var temp="";
            for (var i=0;i<data.length;i++)
            {
                if (data[i].delete==true)
                {
                    var tem_answer=data[i].answer;
                    if (data[i].answer==null)
                        tem_answer="未回复";

                    {}
                    temp=tempArr+"&questionId="+data[i].id;
                    html=html+"\n          <div class=\"col-xs-6 col-lg-4\" >\n";
                    if (data[i].questioner!="")
                    {
                        html=html+"            <p>提问者"+(i+1)+":"+data[i].questioner+"</p>\n";
                    }
                    html=html+
                        "            <p>问题"+(i+1)+":"+data[i].question+"</p>\n" +
                        "            <p>回复："+tem_answer+"</p>\n" +
                        "            <p><a class=\"btn btn-default\" href=\"answer.html?"+temp+"\""+"role=\"button\">View details &raquo;</a></p>\n" +
                        "          </div><!--/.col-xs-6.col-lg-4-->"
                }
            }
            $("#question").html(html);
            //
            // alert(data[0].question);
        },
        'beforeSend' : setHeader
    });
}

function  notAnswer() {
    var temp=location.search.substring(1,location.search.length);
    // alert(temp);
    var tempArr=temp.split("&");
    // alert(tempArr);
    // var uName=tempArr[0].split("uName=");
    // alert(uName);
    var param=tempArr+"";
    // alert(param);
    // $("#r1c1").html("测试使用");
    $.ajax({
        'url' : 'question',
        'data': param,
        'type': "post",
        'success' : function(data){
            // var jsonarray= $.parseJSON(data);
            var html="";
            var temp="";
            for (var i=0;i<data.length;i++)
            {
                if (data[i].answer==null)
                {
                    var tem_answer="未回复";
                    temp=tempArr+"&questionId="+data[i].id;
                    html=html+"\n          <div class=\"col-xs-6 col-lg-4\" >\n" +
                        "            <p>问题"+(i+1)+":"+data[i].question+"</p>\n" +
                        "            <p>回复："+tem_answer+"</p>\n" +
                        "            <p><a class=\"btn btn-default\" href=\"answer.html?"+temp+"\""+"role=\"button\">View details &raquo;</a></p>\n" +
                        "          </div><!--/.col-xs-6.col-lg-4-->"
                }
            }
            $("#question").html(html);
            //
            // alert(data[0].question);
        },
        'beforeSend' : setHeader
    });
}

function  alrAnswer() {
    var temp=location.search.substring(1,location.search.length);
    // alert(temp);
    var tempArr=temp.split("&");
    // alert(tempArr);
    // var uName=tempArr[0].split("uName=");
    // alert(uName);
    var param=tempArr+"";
    // alert(param);
    // $("#r1c1").html("测试使用");
    $.ajax({
        'url' : 'question',
        'data': param,
        'type': "post",
        'success' : function(data){
            // var jsonarray= $.parseJSON(data);
            var html="";
            var temp="";
            for (var i=0;i<data.length;i++)
            {
                if (data[i].answer!=null)
                {
                    temp=tempArr+"&questionId="+data[i].id;
                    html=html+"\n          <div class=\"col-xs-6 col-lg-4\" >\n" +
                        "            <p>问题"+(i+1)+":"+data[i].question+"</p>\n" +
                        "            <p>回复："+data[i].answer+"</p>\n" +
                        "            <p><a class=\"btn btn-default\" href=\"answer.html?"+temp+"\""+"role=\"button\">View details &raquo;</a></p>\n" +
                        "          </div><!--/.col-xs-6.col-lg-4-->"
                }
            }
            $("#question").html(html);
            //
            // alert(data[0].question);
        },
        'beforeSend' : setHeader
    });
}
//
$(document).ready(function () {
    var html="<a href=\"#\" class=\"list-group-item active\">用户列表</a>";
    //设置路径
    $.ajax({
        url:'getUsersList',
        type: 'post',
        success : function (data) {
            if (data.length>0)
            {
                for (var i=0;i<data.length;i++)
                {
                    html=html+
                        "<a href=\"index.html?uName="+data[i].uName+"\" class=\"list-group-item\">"+data[i].uName+"</a>";
                }
            }
            else
            {
                alert("获取用户失败");
            }
            $("#usersList").html(html);
        },
        // 'beforeSend' : setHeader()
    });
});


$(document).ready(function () {
    var temp=location.search.substring(1,location.search.length);
    // var html="Welcome to "+decodeURI(name)+"'s message board";
    // $("#userName").html(html);
    var picture=document.getElementById("picture");
    //设置路径
    $.ajax({
        url:'getPhoto',
        type: 'post',
        data: temp,
        success : function (data) {
            if (data.code==200)
            {
                //使用用户上传的图片。
                picture.src=data.add;
            }
            else
            {
                //使用默认图片。
                picture.src="image/0.png";
            }
        },
        'beforeSend' : setHeader
    });
    // picture.src="image/0.png";
    // picture.src="D:\upload\47536aea-77c6-4d79-8f30-75fded0c51708JHR444@F{1E3N637YETFFE.png";
});

$(document).ready(function () {
    // var temp=location.search.substring(1,location.search.length);
    // var tempArr=temp.split("&");
    // var uName=tempArr[0].split("uName=");
    var html="";
    $.ajax({
        'url' : 'check',
        'success' : function(data){
            if (data.code==undefined)
            {
                alert("未登录，不能发起提问!");
            }
            else
            {
                if(data.code == 200){
                    html="我的提问箱（"+data.jsti+"）";
                    alert(html);
                    $("#userName").html(html);
                    window.localStorage.token = data.token;
                    alert(data.msg);
                }else{
                    alert("未登录，不能发起提问!!");
                }
            }
        },
        'beforeSend' : setHeader
    });

});

function checkState(){
    $.ajax({
        'url' : 'check',
        'success' : function(data){
            if(data.code == 200){
                window.localStorage.token = data.token;
                alert(data.msg);
            }else{
                alert(data.msg);
            }
        },
        'beforeSend' : setHeader
    });
}

$(document).ready(function () {
    var temp=location.search.substring(1,location.search.length);
    // alert(temp);
    var tempArr=temp.split("&");
    // alert(tempArr);
    // var uName=tempArr[0].split("uName=");
    // alert(uName);
    var param=decodeURI(tempArr)+"";
    alert(param);
    // $("#r1c1").html("测试使用");
    $.ajax({
        'url' : 'question',
        'data': param,
        'type': "post",
        'success' : function(data){
            var html="";
            var temp="";
            // var jsonarray= $.parseJSON(data);
            for (var i=0;i<data.length;i++)
            {
                temp=tempArr+"&questionId="+data[i].id;
                html=html+"\n          <div class=\"col-xs-6 col-lg-4\" >\n";

                if (data[i].questioner!="")
                {
                    html=html+"            <p>提问者"+(i+1)+":"+data[i].questioner+"</p>\n";
                }
                 html=html+   "            <p>问题"+(i+1)+":"+data[i].question+"</p>\n" +
                    "            <p>回复："+data[i].answer+"</p>\n" +
                    "            <p><a class=\"btn btn-default\" href=\"answer.html?"+temp+"\""+"role=\"button\">View details &raquo;</a></p>\n" +
                    "          </div><!--/.col-xs-6.col-lg-4-->"
            }
            if (data.length<=0)
            {
                html="用户处于封禁状态或无提问";
            }
            $("#question").html(html);
            //
            // alert(data[0].question);
        },
        'beforeSend' : setHeader
    });
});


function ask() {
    // var param=$("#userName").val();
    // param="uName="+param;
    var temp=location.search.substring(1,location.search.length);
    var tempArr=temp.split("&");
    tempArr=decodeURI(tempArr);
    var param=tempArr;
    alert(tempArr);
    // alert("test0001");
    $.ajax({
        url:'ask',
        data:param+"",
        type:"post",
        success:function (data) {
            if (data.code==200)
            {
                window.localStorage.token = data.token;
                alert(data.add);
                top.location.href=data.add;
            }
            else
            {
                if (data.msg==undefined)
                    alert("请登录。")
                else
                    alert(data.msg);
            }
        },
        'beforeSend' : setHeader
    });
}
