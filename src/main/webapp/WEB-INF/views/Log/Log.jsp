<%--suppress HtmlUnknownAttribute --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<script type="text/javascript">
    $(document).ready(
            function () {
                var sizePerPage = 10;//每页多少条
                function Paging(pageNumber, pageSize) {
                    $.ajax({
                        type: "Get",
                        url: "/Log/getLogByPage/pageNumber/" + pageNumber + "/pageSize/" + pageSize,
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        success: function (data) {
                            console.log(data);
                            var listResults = data["results"];

                            var pagination = $("#pagination");
                            pagination.empty();
                            var previousPage = data["previousPage"];
                            var currentPage = data["currentPage"];
                            var pageCount = data["pageCount"];
                            var nextPage = data["nextPage"];
                            //拼接上一页
                            if (previousPage < 1 || previousPage >= currentPage) {//上一页不可达
                                pagination.append("<li class='disabled paging'><a pageNumber='1'>&laquo;</a></li>");
                            } else {
                                pagination.append("<li class='paging'><a pageNumber=" + previousPage + ">&laquo;</a></li>");
                            }

                            //拼接页码
                            for (var page = 1; page <= pageCount; page++) {
                                if (page == currentPage) {
                                    pagination.append("<li class='active paging'><a pageNumber=" + page + ">" + page + "</a></li>")
                                } else {
                                    pagination.append("<li class='paging'><a pageNumber=" + page + ">" + page + "</a></li>")
                                }
                            }

                            //拼接下一页
                            if (nextPage < 1 || nextPage <= currentPage) {//下一页不可达
                                pagination.append("<li class='disabled paging'><a pageNumber=" + nextPage + ">&raquo;</a></li>");
                            } else {
                                pagination.append("<li class='paging'><a pageNumber=" + nextPage + ">&raquo;</a></li>");
                            }

                            $(".paging").click(function () {
                                var selectedPageNumber = this.getElementsByTagName("a")[0].getAttribute("pageNumber");
                                //console.lg(this.getElementsByTagName("a")[0].innerHTML);
                                Paging(selectedPageNumber, sizePerPage);
                            });

                            $("#LogTableBody").empty();
                            for (var j = 0; j < data["totalCount"]; j++) {
                                var result = listResults[j];
                                try {
                                    //noinspection JSUnresolvedVariable
                                    $("#LogTableBody")
                                            .append("<tr>")
                                            .append("<th><span class='co-name'>" + result.event_id + "</span></th>")
                                            .append("<td>" + result.timestmp + "</td>")
                                            .append("<td>" + result.formatted_message + "</td>")
                                            .append("<td>" + result.logger_name + "</td>")
                                            .append("<td>" + result.arg0 + "</td>")
                                            .append("<td>" + result.arg1 + "</td>")
                                            .append("<td>" + result.arg2 + "</td>")
                                            .append("<td>" + result.arg3 + "</td>")
                                            .append("</tr>");
                                } catch (e) {
                                    console.log(e);
                                }
                            }

                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            swal({
                                title: "出错了！",
                                text: "错误信息" + XMLHttpRequest.status,
                                type: "error",
                                confirmButtonText: "知道了"
                            });
                        }
                    });
                }

                //页面加载时自动填充第一页，每页十条
                Paging(1, sizePerPage);
            }
    );
</script>

<!-- Page-Title -->
<div class="row">
    <div class="col-sm-12">
        <h4 class="pull-left page-title">日志</h4>
        <ol class="breadcrumb pull-right">
            <li><a href="${pageContext.request.contextPath}/">回到首页</a></li>
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
                        <tbody id="LogTableBody">

                        </tbody>
                    </table>
                    <ul class="pagination" id="pagination">

                    </ul>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- end row -->

<script src="${pageContext.request.contextPath}/assets/js/Chart.min.js"></script>

<%--suppress JSUnusedAssignment --%>
<script type="text/javascript">
    var infoData;
    $.ajax({
        type: "Get",
        url: "/Log/getLogInfo",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            infoData = data;
            console.log("infoData:");
            console.log(infoData)
        },
        error: function (err) {
            console.log("Error:");
            console.log(err);
        }
    });
    // 设置参数
    var data = {
        labels: [
            "Controller异常",
            "自定义类异常",
            "其他异常"
        ],
        datasets: [
            {
                data: [infoData["LogUtilsCount"], infoData["LogAspectCount"], infoData["otherCount"]],
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
<script src="${pageContext.request.contextPath}/assets/plugins/dashboard/jquery.dashboard.js"></script>
