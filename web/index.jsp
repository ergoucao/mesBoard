<%--
  Created by IntelliJ IDEA.
  User: cxm
  Date: 2020/3/31
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
      $(function () {
        var username=false;
        var password=false;
        var passwordSure=false;
      //  用户名的校证
        $(":text:eq(0)").blur(function () {
            if ($(this).val()=="")
            {
              $(this).next().css("color","red").html("X");
              username=false;
            }
            else
            {
              $(this).next().css("color","green").html("√");
              username=true;
            }
        })
      //  密码的验证
        $(":password:eq(0)").blur(function () {
        //      js中正则写法
          if (!$(this).val().match(/^\w{6,12}$/))
          {
            $(this).next().css("color","red").html("X");
            password=false;
          }
          else
          {
            $(this).next().css("color","green").html("√");
            password=true;
          }
        })
      //  确认密码
        $(":password:eq(1)").blur(function () {
          if ($(this).val()==""||$(this).val()!=$(":password:eq(0)").val())
          {
            $(this).next().css("color","red").html("X");
            passwordSure=false;
          }
          else
          {
            $(this).next().css("color","green").html("√");
            passwordSure=true;
          }
        })
        $(":submit").click(function () {
          if (username==false||passwordSure==false||password==false||$(":file").val()=="")
          {
            alert("请添加完整信息");
            return false;
          }
        })
      })
    </script>
  </head>
  <body>
    <form action="regist" method="post" enctype="multipart/form-data">
      用户名：<input type="text" name="uName"/><span></span><br/>
      密码：<input type="password" name="uPassword"/><span></span><br/>
      确认密码：<input type="password" name="uPasswordSure"/><span></span><br/>
      邮箱：<input type="email" name="uMail"><br/>
<%--      头像：<input type="file" name="uPhoto"/><br/>--%>
      <input type="submit" value="注册">
    </form>
  </body>
</html>
