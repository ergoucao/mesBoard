<%--
  Created by IntelliJ IDEA.
  User: cxm
  Date: 2020/3/31
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {
            $("a").click(function () {
                //parent() 父标签
                //prev() 前一个兄弟标签
                //jquery中规范,对象名以$开头
                var $td=$(this).parent().prev();
            //    html（）返回字符串
                $td.html(parseInt($td.html())+1);
            })
        })
    </script>
    <script type="text/javascript">

    </script>
</head>
<body>
<table border="1">
    <tr>
        <td>资料名称</td>
        <td>下载次数</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${list}" var="file">
        <tr>
            <td>${file.name}</td>
            <td>${file.count}</td>
            <td><a href="download?id=${file.id}&name=${file.name}">下载</a> </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
