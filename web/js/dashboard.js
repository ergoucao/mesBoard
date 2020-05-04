var usersData;

function banC()
{
    var temp=location.search.substring(1,location.search.length);
    // alert(temp);
    var tempArr=temp.split("&");
    // alert(tempArr);
    // var uName=tempArr[0].split("uName=");
    // alert(uName);
    var banText=$("#banText").val();
    var param=decodeURI(tempArr)+"&ban=true&banText="+banText;
    $.ajax({
        url:'giveAnserCom',
        data:param,
        type:'post',
        'success':function (data) {
            if (data.code==200)
            {
                alert("处理成功");
                top.location.href=data.add;
            }
            else
            {
                alert("处理失败");
            }
        },
        'beforeSend' : setHeader
    })
}

function notBanC()
{
    var temp=location.search.substring(1,location.search.length);
    // alert(temp);
    var tempArr=temp.split("&");
    // alert(tempArr);
    // var uName=tempArr[0].split("uName=");
    // alert(uName);
    var banText=$("#banText").val();
    var param=decodeURI(tempArr)+"&ban=false&banText="+banText;
    $.ajax({
        url:'giveAnserCom',
        data:param,
        type:'post',
        'success':function (data) {
            if (data.code==200)
            {
                alert("处理成功");
                top.location.href=data.add;
            }
            else
            {
                alert("处理失败");
            }
        },
        'beforeSend' : setHeader
    })
}

function  changeToManage() {
    top.location.href=window.location.href;
}

function dealC(id)
{
    top.location.href="dealC.html?id="+id;
}

function dealComplain() {
    var deaComHmtl="       <th>ID</th>\n" +
        "                  <th>举报人</th>\n" +
        "                  <th>被举报人</th>\n" +
        "                  <th>举报理由</th>\n" +
        "                  <th>处理结果</th>";
    $("#dealCom").html(deaComHmtl);
    $.ajax({
        url:'dealComplain',
        data:'',
        type:'post',
        'success': function(data) {
            var html;
            for (var i=0;i<data.length;i++)
            {
                html=html+"<tr>\n" +
                    "<td ><a onclick=\"dealC("+"'"+data[i].id+"'"+")\">"+data[i].id+"</a></td>\n" +
                    "<td>"+data[i].reporter+"</td>\n" +
                    "<td>"+data[i].informant+"</td>\n" +
                    "<td>"+data[i].reason+"</td>\n" +
                    "<td>"+data[i].dealAnswer+"</td>\n" +
                    "</tr>";
            }
            $("#userManage").html(html);
        },
        'beforeSend' : setHeader
    })
}


function userDetail(uName)
{
    top.location.href="userDetail.html?uName="+uName;
}



$(document).ready(function () {
    $.ajax({
        'url' : 'getUses',
        'data': "",
        'type': "post",
        'success' : function(data){

            if (data.length<=0)
            {
                alert("请登录");
                top.location.href="login.html";
            }
            usersData=data;
            // var jsonarray= $.parseJSON(data);
            var html="";
            var temp="";
            for (var i=0;i<data.length;i++)
            {
                var tdId="ban"+i;
                html=html+"<tr>\n" +
                    "<td ><a onclick=\"userDetail("+"'"+usersData[i].uName+"'"+")\">"+usersData[i].uId+"</a></td>\n" +
                    "<td>"+usersData[i].uName+"</td>\n" +
                    "<td id="+tdId.toString()+"><a onclick=\"changeBan("+usersData[i].ban+","+i+","+"'"+usersData[i].uName+"'"+")\">"+usersData[i].ban+"</a></td>\n" +
                    "<td>"+usersData[i].questionNum+"</td>\n" +
                    "<td>"+usersData[i].askNum+"</td>\n" +
                    "<td>"+usersData[i].answerNum+"</td>\n" +
                    "</tr>";
            }
            $("#userManage").html(html);
            //
            // alert(data[0].question);
        },
        'beforeSend' : setHeader
    });
});

function setHeader(xhr){ // XmlHttpRequest
    xhr.setRequestHeader("Authorization",window.localStorage.token);
}

function  search() {
    var input=$("#searchInput").val();
    alert(input);
    var html="";
    for (var i=0;i<usersData.length;i++)
    {
        // alert(usersData[i].uName);
        if (usersData[i].uName==input||usersData[i].uMail==input)
        {
            var tdId="ban"+i;
            html="<tr>\n" +
                "<td ><a onclick=\"userDetail("+usersData[i].uId+")\">"+usersData[i].uId+"</a></td>\n" +
                "<td>"+usersData[i].uName+"</td>\n" +
                "<td id="+tdId.toString()+"><a onclick=\"changeBan("+usersData[i].ban+","+i+","+usersData[i].uName+")\">"+usersData[i].ban+"</a></td>\n" +
                "<td>"+usersData[i].questionNum+"</td>\n" +
                "<td>"+usersData[i].askNum+"</td>\n" +
                "<td>"+usersData[i].answerNum+"</td>\n" +
                "</tr>";
        }
    }
    $("#userManage").html(html);
}

function  changeBan(ban,i,uName) {
    var html="";
    var temBan;
    alert("更改封禁状态");
    if (ban==true)
    {
        temBan=false;
    }
    else
    {
        temBan=true;
    }
    var param="isBan="+temBan+"&uName="+uName;
    html="<a onclick=\"changeBan("+temBan+","+i+")\">"+temBan+"</a>";
    // alert(temH.toString());
    $.ajax({
        'url' : 'changeBan',
        'data': param,
        'type': "post",
        'success' : function(data){
            if (data.code=200)
            {
                window.localStorage.token = data.token;
                $("#ban"+i+"").html(html);
            }
            else
            {
                alert("修改失败");
            }
        },
        'beforeSend' : setHeader
    });
}