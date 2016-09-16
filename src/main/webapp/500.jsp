<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <link rel="shortcut icon" href="assets/images/show.png">

    <title>500</title>

    <%@ include file="/head.jsp" %>

</head>
<body>


<div class="wrapper-page">
    <div class="ex-page-content text-center">
        <h1>500</h1>
        <h2 class="font-light">Internal Server Error.</h2><br>
        <p>出了一个错误！这真是令人尴尬！ <a >错误代码500</a></p>

        <a class="btn btn-purple waves-effect waves-light" href="/"><i class="fa fa-angle-left"></i>返回首页</a>
        <script type="text/javascript">
            console.log("错误信息${MSG}");
            console.log("出错行${Line}");
            console.log("出错方法${Method}");
            <%--console.log("${detailed}")--%>
        </script>
    </div>


</div>


<%@ include file="/script.jsp" %>

</body>
</html>
