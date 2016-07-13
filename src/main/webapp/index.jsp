<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="shortcut icon" href="assets/images/show.png">

    <title>首页</title>

    <%@ include file="head.jsp" %>

</head>


<body class="fixed-left">

<div id="wrapper">

    <%@ include file="topbar.jsp" %>

    <%@ include file="left_side_menu.jsp" %>

    <div class="content-page">
        <div class="content">
            <div class="container" id="mainPage">

                <%@ include file="Dashboard.jsp" %>

            </div>
        </div>

        <%@ include file="footer.jsp" %>

    </div>

</div>

<%@ include file="script.jsp" %>

</body>
</html>