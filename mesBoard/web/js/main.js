function setHeader(xhr){ // XmlHttpRequest
    xhr.setRequestHeader("Authorization",window.localStorage.token);
}

function testLocalStorage(){
    $.ajax({
        'url' : 'testAll',
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