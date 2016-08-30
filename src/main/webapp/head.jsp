<link href="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.css" rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/core.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/components.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/pages.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/menu.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/responsive.css" rel="stylesheet" type="text/css">

<script src="${pageContext.request.contextPath}/assets/js/modernizr.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>

<%--进度条--%>
<script>
    paceOptions = {
        initialRate: 0.7,
        minTime: 1000,
        maxProgressPerFrame: 1,
        restartOnRequestAfter:true,
        restartOnPushState:true,
        ajax:true
    }
</script>
<script src="${pageContext.request.contextPath}/assets/js/pace.min.js"></script>
<link href="${pageContext.request.contextPath}/assets/css/PaceJSMinimalTheme.css" rel="stylesheet">
<script type="text/javascript">
    Pace.on("done", function(){
        $(".cover").fadeOut(500);
    });
</script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/assets/js/html5shiv.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/respond.min.js"></script>
<![endif]-->
