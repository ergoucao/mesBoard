function setHeader(xhr){ // XmlHttpRequest
    xhr.setRequestHeader("Authorization",window.localStorage.token);
}

function checkLocalStorage(){
    $.ajax({
        'url' : 'check',
        'success' : function(data){
            if(data.code == 200){
                window.localStorage.token = data.token;
                alert(data.data);
            }else{
                alert(data.msg);
            }
        },
        'beforeSend' : setHeader
    });
}