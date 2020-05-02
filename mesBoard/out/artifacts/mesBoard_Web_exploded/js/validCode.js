$(document).ready(function() {
    $.ajax({
        url : "validCode",
        dataType : "json",//数据格式
        type : "post",//请求方式
        async : false,//是否异步请求
        success : function(image) {   //如果请求成功，返回数据。
            var html = "";
            var url=image.url;
            $("#imgC").append("<img src='"+url+"width=\"80\" height=\"40\"'/>");
        },
    })
})


// function ValiCode() {
//         $.ajax({
//             url : "validCode",
//             dataType : "json",//数据格式
//             type : "post",//请求方式
//             async : false,//是否异步请求
//             success : function(data) {   //如果请求成功，返回数据。
//                 var html = "";
//                 for(var i=0;i<data.length;i++){    //遍历data数组
//                     var ls = data[i];
//                     html +="<span>测试："+ls.name+"</span>";
//                 }
//                 $("#test").html(html); //在html页面id=test的标签里显示html内容
//             },
//         })
// }