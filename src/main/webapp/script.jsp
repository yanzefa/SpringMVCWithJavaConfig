<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    var resizefunc = [];
</script>

<!-- jQuery -->
<!--<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>-->
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<!-- 检测浏览器和操作系统 -->
<script src="${pageContext.request.contextPath}/assets/js/detect.js"></script>
<!-- 让移动设备click事件没有延迟-->
<script src="${pageContext.request.contextPath}/assets/js/fastclick.min.js"></script>
<!-- 滚动条插件-->
<script src="${pageContext.request.contextPath}/assets/js/jquery.slimscroll.min.js"></script>
<!-- 单出对话框-->
<script src="${pageContext.request.contextPath}/assets/js/jquery.blockUI.min.js"></script>
<!--类Material Design 的圆形波浪（涟漪）点击特效插件-->
<script src="${pageContext.request.contextPath}/assets/js/waves.min.js"></script>
<!--动画特效插件-->
<script src="${pageContext.request.contextPath}/assets/js/wow.min.js"></script>
<!--滚动条插件-->
<script src="${pageContext.request.contextPath}/assets/js/jquery.nicescroll.js"></script>
<!--定位跳转插件-->
<script src="${pageContext.request.contextPath}/assets/js/jquery.scrollTo.min.js"></script>

<!--这个是所有页面共享的逻辑，比如左边的菜单栏逻辑-->
<script src="${pageContext.request.contextPath}/assets/js/jquery.app.js"></script>

<!-- moment js -->
<script src="${pageContext.request.contextPath}/assets/plugins/moment/moment.min.js"></script>

<!-- counters -->
<script src="${pageContext.request.contextPath}/assets/plugins/waypoints/jquery.waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/counterup/jquery.counterup.min.js"></script>

<!-- sweet alert -->
<script src="${pageContext.request.contextPath}/assets/plugins/sweetalert/sweetalert.min.js"></script>


<script type="text/javascript">
    jQuery(document).ready(function ($) {
        $('.counter').counterUp({
            delay: 100,
            time: 1200
        });
    });
</script>
