function giveQues() {
    var param = location.search.substring(1, location.search.length);//通过url获取参数；
    param = param+"&qestion="+$("#questionText").val();
    alert("即将发表提问");
    $.ajax({
        url: 'giveQues',
        data: param,
        type: 'post',
        'success': function (data) {
            alert(data.msg);
            if (data.code == 200) {
                top.location.href=data.add;
            }
        },
        'beforeSend': setHeader
    })
}

function setHeader(xhr){ // XmlHttpRequest
    xhr.setRequestHeader("Authorization",window.localStorage.token);
}


function answer() {
    var param = location.search.substring(1, location.search.length);//通过url获取参数；
    param = param+"&qestion="+$("#questionText").val();
    $.ajax({
        url: 'answer',
        data: param,
        type: 'post',
        success: function (data) {
            alert(dat.msg);
            if (data.code = 200) {
                window.localStorage.token=data.token;
                top.location.href == "" + data.add;
            }
        },
        beforeSend: setHeader
    })
}