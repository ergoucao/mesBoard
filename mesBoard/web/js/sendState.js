function setHeader(xhr){ // XmlHttpRequest
    xhr.setRequestHeader("Authorization",window.localStorage.token);
}

$(document).ready(function () {

    var html="Hello，";
    $("#welcome").html(html);
    $.ajax({
        'url' : 'check',
        'success' : function(data){
            html=html+data.jsti;
            $("#welcome").html(html);
        },
        'beforeSend' : setHeader
    });
});