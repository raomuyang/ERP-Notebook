<%--
  Created by IntelliJ IDEA.
  User: Raomengnan
  Date: 2016/11/19
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <section class="vbox">

        <section class="scrollable padder">

            <ul class="breadcrumb no-border no-radius b-b b-light pull-in">
                <li><a href="/admin/index.html"><i class="fa fa-home"></i> Home</a></li>
                <li class="active">仪表盘</li>
            </ul>

            <div class="m-b-md">
                <h3 class="m-b-none">控制面板</h3>
                <small>Welcome back, </small><small class="user-name">Noteman</small>
            </div>

            <%--总览--%>
            <section class="panel panel-default">
                <div class="row m-l-none m-r-none bg-light lter">

                    <div class="col-sm-6 col-md-3 padder-v b-r b-light">
                        <span class="fa-stack fa-2x pull-left m-r-sm">
                            <i class="fa fa-circle fa-stack-2x text-info"></i>
                            <i class="fa fa-male fa-stack-1x text-white"></i>
                        </span>
                        <a class="clear" href="#">
                            <span class="h3 block m-t-xs">
                                <strong class="user-numbers">52,000</strong></span>
                            <small class="text-muted text-uc">User Numbers</small>
                        </a>
                    </div>

                    <div class="col-sm-6 col-md-3 padder-v b-r b-light lt">
                        <span class="fa-stack fa-2x pull-left m-r-sm">
                            <i class="fa fa-circle fa-stack-2x text-warning"></i>
                            <i class="fa fa-bug fa-stack-1x text-white"></i>
                            <%--<span--%>
                                    <%--class="easypiechart pos-abt"--%>
                                    <%--data-percent="100"--%>
                                    <%--data-line-width="4"--%>
                                    <%--data-track-Color="#fff"--%>
                                    <%--data-scale-Color="false"--%>
                                    <%--data-size="50"--%>
                                    <%--data-line-cap='butt'--%>
                                    <%--data-animate="2000"--%>
                                    <%--data-target="#bugs"--%>
                                    <%--data-update="3000">--%>

                            <%--</span>--%>
                        </span>
                        <a class="clear" href="#">
                            <span class="h3 block m-t-xs">
                                <strong id="bugs">468</strong>
                            </span>
                            <small class="text-muted text-uc">文章数量</small>
                        </a>
                    </div>

                    <div class="col-sm-6 col-md-3 padder-v b-r b-light">
                        <span class="fa-stack fa-2x pull-left m-r-sm">
                            <i class="fa fa-circle fa-stack-2x text-danger"></i>
                            <i class="fa fa-fire-extinguisher fa-stack-1x text-white"></i>
                            <%--<span class="easypiechart pos-abt" data-percent="100" data-line-width="4" data-track-Color="#f5f5f5" data-scale-Color="false" data-size="50" data-line-cap='butt' data-animate="3000" data-target="#firers" data-update="5000"></span> --%>
                        </span>
                        <a class="clear" href="#">
                            <span class="h3 block m-t-xs">
                                <strong id="firers">359</strong>
                            </span>
                            <small class="text-muted text-uc">历史图片</small>
                        </a>
                    </div>

                    <div class="col-sm-6 col-md-3 padder-v b-r b-light lt">
                        <span class="fa-stack fa-2x pull-left m-r-sm">
                            <i class="fa fa-circle fa-stack-2x icon-muted"></i>
                            <i class="fa fa-clock-o fa-stack-1x text-white"></i>
                        </span>
                        <a class="clear" href="#">
                            <span class="h3 block m-t-xs">
                                <strong>31</strong>
                            </span>
                            <small class="text-muted text-uc">历史视频</small>
                        </a>
                    </div>
                </div>
            </section>
            <%--/总览--%>

            <div class="row">

                <%--统计图--%>
                <div class="col-md-8">
                    <section class="panel panel-default">
                        <header class="panel-heading font-bold">Statistics</header>
                        <div class="panel-body">
                            <div id="flot-1ine" style="height:210px"></div>
                        </div>
                        <footer class="panel-footer bg-white no-padder">
                            <div class="row text-center no-gutter">
                                <div class="col-xs-3 b-r b-light">
                                    <span class="h4 font-bold m-t block">5,860</span>
                                    <small class="text-muted m-b block">Orders</small>
                                </div>
                            </div>
                        </footer>
                    </section>
                </div>
                <%--/统计图    --%>

                    <div class="col-md-4">
                        <section class="panel b-light">
                            <header class="panel-heading bg-primary dker no-border"><strong>Calendar</strong></header>
                            <div id="calendar" class="bg-primary m-l-n-xxs m-r-n-xxs"></div>
                            <div class="list-group"> <a href="#" class="list-group-item text-ellipsis"> <span class="badge bg-danger">7:30</span> Meet a friend </a> <a href="#" class="list-group-item text-ellipsis"> <span class="badge bg-success">9:30</span> Have a kick off meeting with .inc company </a> <a href="#" class="list-group-item text-ellipsis"> <span class="badge bg-light">19:30</span> Milestone release </a> </div>
                        </section>
                    </div>
            </div>


            <div class="row">
                <div class="col-md-8">
                    <h4 class="m-t-none">最新文章-审阅</h4>
                    <ul class="list-group gutter list-group-lg list-group-sp sortable">
                        <li class="list-group-item box-shadow"> <a href="#" class="pull-right" data-dismiss="alert"> <i class="fa fa-times icon-muted"></i> </a> <span class="pull-left media-xs"> <i class="fa fa-sort icon-muted fa m-r-sm"></i> <a href="#todo-1" data-toggle="class:text-lt text-success" class="active"> <i class="fa fa-square-o fa-fw text"></i> <i class="fa fa-check-square-o fa-fw text-active text-success"></i> </a> </span>
                            <div class="clear text-success text-lt" id="todo-1"> Browser compatibility </div>
                        </li>
                        <li class="list-group-item box-shadow"> <a href="#" class="pull-right" data-dismiss="alert"> <i class="fa fa-times icon-muted"></i> </a> <span class="pull-left media-xs"> <i class="fa fa-sort icon-muted fa m-r-sm"></i> <a href="#todo-2" data-toggle="class:text-lt text-danger"> <i class="fa fa-square-o fa-fw text"></i> <i class="fa fa-check-square-o fa-fw text-active text-danger"></i> </a> </span>
                            <div class="clear" id="todo-2"> Looking for more example templates </div>
                        </li>
                        <li class="list-group-item box-shadow"> <a href="#" class="pull-right" data-dismiss="alert"> <i class="fa fa-times icon-muted"></i> </a> <span class="pull-left media-xs"> <i class="fa fa-sort icon-muted fa m-r-sm"></i> <a href="#todo-3" data-toggle="class:text-lt"> <i class="fa fa-square-o fa-fw text"></i> <i class="fa fa-check-square-o fa-fw text-active text-success"></i> </a> </span>
                            <div class="clear" id="todo-3"> Customizing components </div>
                        </li>
                        <li class="list-group-item box-shadow"> <a href="#" class="pull-right" data-dismiss="alert"> <i class="fa fa-times icon-muted"></i> </a> <span class="pull-left media-xs"> <i class="fa fa-sort icon-muted fa m-r-sm"></i> <a href="#todo-4" data-toggle="class:text-lt"> <i class="fa fa-square-o fa-fw text"></i> <i class="fa fa-check-square-o fa-fw text-active text-success"></i> </a> </span>
                            <div class="clear" id="todo-4"> The fastest way to get started </div>
                        </li>
                        <li class="list-group-item box-shadow"> <a href="#" class="pull-right" data-dismiss="alert"> <i class="fa fa-times icon-muted"></i> </a> <span class="pull-left media-xs"> <i class="fa fa-sort icon-muted fa m-r-sm"></i> <a href="#todo-5" data-toggle="class:text-lt"> <i class="fa fa-square-o fa-fw text"></i> <i class="fa fa-check-square-o fa-fw text-active text-success"></i> </a> </span>
                            <div class="clear" id="todo-5"> HTML5 doctype required </div>
                        </li>
                        <li class="list-group-item box-shadow"> <a href="#" class="pull-right" data-dismiss="alert"> <i class="fa fa-times icon-muted"></i> </a> <span class="pull-left media-xs"> <i class="fa fa-sort icon-muted fa m-r-sm"></i> <a href="#todo-6" data-toggle="class:text-lt"> <i class="fa fa-square-o fa-fw text"></i> <i class="fa fa-check-square-o fa-fw text-active text-success"></i> </a> </span>
                            <div class="clear" id="todo-6"> LessCSS compiling </div>
                        </li>
                    </ul>
                </div>
            </div>


            <div>
                <div class="btn-group m-b" data-toggle="buttons">
                    <label class="btn btn-sm btn-default active">
                        <input type="radio" name="options" id="option1">
                        Timeline </label>
                </div>

                <section class="comment-list block">
                    <article id="comment-id-1" class="comment-item"> <span class="fa-stack pull-left m-l-xs"> <i class="fa fa-circle text-info fa-stack-2x"></i> <i class="fa fa-play-circle text-white fa-stack-1x"></i> </span>
                        <section class="comment-body m-b-lg">
                            <header> <a href="#"><strong>John smith</strong></a> shared a <a href="#" class="text-info">video</a> to you <span class="text-muted text-xs"> 24 minutes ago </span> </header>
                            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi id neque quam.</div>
                        </section>
                    </article>

                    <!-- / .comment-reply -->
                    <article id="comment-id-2" class="comment-item"> <span class="fa-stack pull-left m-l-xs"> <i class="fa fa-circle text-danger fa-stack-2x"></i> <i class="fa fa-file-o text-white fa-stack-1x"></i> </span>
                        <section class="comment-body m-b-lg">
                            <header> <a href="#"><strong>John Doe</strong></a> <span class="text-muted text-xs"> 1 hour ago </span> </header>
                            <div>Lorem ipsum dolor sit amet, consecteter adipiscing elit.</div>
                        </section>
                    </article>
                    <article id="comment-id-3" class="comment-item"> <span class="fa-stack pull-left m-l-xs"> <i class="fa fa-circle text-success fa-stack-2x"></i> <i class="fa fa-check text-white fa-stack-1x"></i> </span>
                        <section class="comment-body m-b-lg">
                            <header> <a href="#"><strong>Jonathan</strong></a> completed a task <span class="text-muted text-xs"> 1 hour ago </span> </header>
                            <div>Consecteter adipiscing elit.</div>
                        </section>
                    </article>
                </section>
                <a href="#" class="btn btn-default btn-sm m-b"><i class="fa fa-plus icon-muted"></i> more</a> </div>
        </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>

<script>
    (function () {
        var token = "xxxxxxxxx";

    })();
</script>

