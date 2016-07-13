<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Page-Title -->
<div class="row">
    <div class="col-sm-12">
        <h4 class="pull-left page-title">日志</h4>
        <ol class="breadcrumb pull-right">
            <li><a href="/">回到首页</a></li>
            <li class="active">日志</li>
        </ol>
    </div>
</div>


<div class="col-lg-4">
    <div class="panel panel-border panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">异常统计</h3>
        </div>
        <!-- 展示canvas -->
        <div class="panel-body">
            <canvas id="myChart" width="300" height="200"></canvas>
        </div>
    </div>
</div>

<div class="col-lg-8">
    <div class="panel panel-border panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Bar chart</h3>
        </div>
        <div class="panel-body">
            <%--<canvas id="barChart" width="600" height="319"></canvas>--%>
            <canvas id="barChart" width="600" height="189"></canvas>
        </div>
    </div>
</div>

<div class="col-md-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Contextual Table</h3>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>event_id</th>
                            <th>时间</th>
                            <th>信息</th>
                            <th>logger_name</th>
                            <th>参数0</th>
                            <th>参数1</th>
                            <th>参数2</th>
                            <th>参数3</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="active">
                            <td>1</td>
                            <td>1</td>
                            <td>2</td>
                            <td>3</td>
                        </tr>
                        <tr class="success">
                            <td>3</td>
                            <td>Column content</td>
                            <td>Column content</td>
                            <td>Column content</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>Column content</td>
                            <td>Column content</td>
                            <td>Column content</td>
                        </tr>
                        <tr class="danger">
                            <td>9</td>
                            <td>Column content</td>
                            <td>Column content</td>
                            <td>Column content</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-border panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">详细信息</h3>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-small-font table-bordered table-striped table-hover" width="100%">
                        <thead>
                        <tr>
                            <th width='5%'>event_id</th>
                            <th width='5%'>时间</th>
                            <th width='5%'>信息</th>
                            <th width='5%'>logger_name</th>
                            <th width='20%'>参数0</th>
                            <th width='20%'>参数1</th>
                            <th width='20%'>参数2</th>
                            <th width='20%'>参数3</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="Log" varStatus="vs">
                            <tr>
                                <th><span class="co-name">${Log.event_id}</span></th>
                                <td>${Log.timestmp}</td>
                                <td>${Log.formatted_message}</td>
                                <td>${Log.logger_name}</td>
                                <td>${Log.arg0}</td>
                                <td>${Log.arg1}</td>
                                <td>${Log.arg2}</td>
                                <td>${Log.arg3}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- end row -->

<script src="/assets/js/Chart.min.js"></script>

<script type="text/javascript">
    // 设置参数
    var data = {
        labels: [
            "Controller异常",
            "自定义类异常",
            "其他异常"
        ],
        datasets: [
            {
                data: [${LogUtilsCount}, ${LogAspectCount}, ${otherCount}],
                backgroundColor: [
                    "#FF6384",
                    "#36A2EB",
                    "#FFCE56"
                ],
                hoverBackgroundColor: [
                    "#FF6384",
                    "#36A2EB",
                    "#FFCE56"
                ]
            }]
    };


    // Get the context of the canvas element we want to select
    var ctx = document.getElementById("myChart").getContext("2d");
    var myPieChart = new Chart(ctx, {
        type: 'pie',
        data: data
        // options: options
    });


    var barData = {
        labels: ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
        datasets: [
            {
                label: "异常",
                fillColor: "rgba(109,179,63,0.5)",
                strokeColor: "rgba(109,179,63,0.8)",
                highlightFill: "rgba(109,179,63,0.75)",
                highlightStroke: "rgba(109,179,63,1)",
                data: [65, 59, 80, 81, 56, 55, 40]
            },
            {
                label: "警告",
                fillColor: "rgba(151,187,205,0.5)",
                strokeColor: "rgba(151,187,205,0.8)",
                highlightFill: "rgba(151,187,205,0.75)",
                highlightStroke: "rgba(151,187,205,1)",
                data: [28, 48, 40, 19, 86, 27, 90]
            }
        ]
    };

    //Get context with jQuery - using jQuery's .get() method.
    var barChart = $("#barChart").get(0).getContext("2d");
    //This will get the first returned node in the jQuery collection.
    var mybarChart = new Chart(barChart, {
        type: 'bar',
        data: barData
        // options: options
    });

</script>

<!-- dashboard -->
<script src="/assets/plugins/dashboard/jquery.dashboard.js"></script>
