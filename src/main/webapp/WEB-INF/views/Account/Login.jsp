<%--
  Created by IntelliJ IDEA.
  User: izhangzhihao
  Date: 2016/5/18 0018
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spring MVC Login Demo</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <meta name="keywords" content="Kipy Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
    <link href="/app/Account/Login/Login.css" rel='stylesheet' type='text/css' />
    <!--webfonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:700,300,600,800,400' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Marvel:400,700' rel='stylesheet' type='text/css'>
    <!--//webfonts-->
</head>
<body>
<h1>Spring MVC Login Demo</h1>
<div class="login-form">
    <h2>User Login</h2>
    <div class="form-info">
        <form action="/Account/Login" method="post">
            <input type="text" class="email" placeholder="UserName"  name="UserName" required=""/>
            <input type="password" class="password" placeholder="Password" name="Password" required=""/>
            <p><a href="#">Forgot password?</a></p>
            <ul class="login-buttons">
                <li><input type="submit" value="登陆"/></li>
                <li><a href="#" class="hvr-sweep-to-left">注册</a></li>
                <div class="clear"> </div>
            </ul>
        </form>
    </div>
</div>
<!--copyrights-->
<div class="copyrights">
    <%--<p>Template by <a href="http://www.moke8.com/" target="_blank">moke8</a></p>--%>
</div>
<!--/copyrights-->
</body>
