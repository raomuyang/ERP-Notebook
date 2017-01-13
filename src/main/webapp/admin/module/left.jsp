<%--
  Created by IntelliJ IDEA.
  User: Raomengnan
  Date: 2016/11/19
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="bg-dark lter aside-md hidden-print" id="nav">
    <section class="vbox">
        <header class="header bg-primary lter text-center clearfix"></header>

        <section class="w-f scrollable">
            <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="5px" data-color="#333333"> <!-- nav -->
                <nav class="nav-primary hidden-xs">
                    <ul class="nav">

                        <%--控制台--%>
                        <li class="active">
                            <a href="index.html" class="active">
                                <i class="fa fa-dashboard icon">
                                    <b class="bg-danger"></b>
                                </i>
                                <span>控制台</span>
                            </a>
                        </li>
                        <%--/控制台--%>

                        <%--主页管理    --%>
                        <li >
                            <a href="#layout" >
                            <i class="fa fa-columns icon"> <b class="bg-warning"></b> </i>
                            <span class="pull-right">
                                <i class="fa fa-angle-down text"></i>
                                <i class="fa fa-angle-up text-active"></i>
                            </span>
                            <span>主页管理</span> </a>

                            <ul class="nav lt">
                                <li > <a href="a.html" > <i class="fa fa-angle-right"></i> <span>文章管理</span> </a> </li>
                                <li > <a href="b.html" > <i class="fa fa-angle-right"></i> <span>图片管理</span> </a> </li>
                                <li > <a href="c.html" > <i class="fa fa-angle-right"></i> <span>视频管理</span> </a> </li>
                            </ul>
                        </li>
                        <%--/主页管理    --%>


                        <%--图片资源    --%>
                        <li >
                            <a href="#uikit" >
                                <i class="fa fa-flask icon"> <b class="bg-success"></b> </i>
                                <span class="pull-right">
                                    <i class="fa fa-angle-down text"></i>
                                    <i class="fa fa-angle-up text-active"></i>
                                </span>
                                <span>图片资源</span>
                            </a>
                            <ul class="nav lt">
                                <li > <a href="buttons.html" > <i class="fa fa-angle-right"></i> <span>时间轴</span> </a> </li>
                                <li > <a href="icons.html" > <b class="badge bg-info pull-right">369</b> <i class="fa fa-angle-right"></i> <span>照片墙</span> </a> </li>
                            </ul>
                        </li>
                        <%--/图片资源    --%>

                        <%--权限--%>
                        <li >
                            <a href="#pages" >
                                <i class="fa fa-file-text icon">
                                    <b class="bg-primary"></b>
                                </i>
                                <span class="pull-right">
                                    <i class="fa fa-angle-down text"></i>
                                    <i class="fa fa-angle-up text-active"></i>
                                </span>
                                <span>权限管理</span> </a>
                            <ul class="nav lt">
                                <li > <a href="gallery.html" > <i class="fa fa-angle-right"></i> <span>角色管理</span> </a> </li>
                                <li > <a href="profile.html" > <i class="fa fa-angle-right"></i> <span>用户管理</span> </a> </li>
                                <li > <a href="invoice.html" > <i class="fa fa-angle-right"></i> <span>授权政策</span> </a> </li>
                            </ul>
                        </li>
                        <%--/权限--%>

                        <%--信息管理--%>
                        <li >
                            <a href="#main" >
                                <i class="fa fa-pencil icon">
                                    <b class="bg-info"></b>
                                </i>
                                <span class="pull-right">
                                    <i class="fa fa-angle-down text"></i>
                                    <i class="fa fa-angle-up text-active"></i>
                                </span>
                                <span>信息管理</span>
                            </a>

                            <ul class="nav lt">
                                <li > <a href="a.html" > <i class="fa fa-angle-right"></i> <span>基本信息</span> </a> </li>
                                <li > <a href="a.html" > <i class="fa fa-angle-right"></i> <span>组织架构</span> </a> </li>
                                <li > <a href="b.html" > <i class="fa fa-angle-right"></i> <span>协会介绍</span> </a> </li>
                                <li > <a href="c.html" > <i class="fa fa-angle-right"></i> <span>加入我们</span> </a> </li>
                            </ul>
                        </li>
                        <%--/信息管理--%>

                    </ul>
                </nav>
                <!-- / nav --> </div>
        </section>

        <footer class="footer lt hidden-xs b-t b-dark">

            <a href="#nav" data-toggle="class:nav-xs" class="pull-right btn btn-sm btn-dark btn-icon"> <i class="fa fa-angle-left text"></i> <i class="fa fa-angle-right text-active"></i> </a>
            <div class="btn-group hidden-nav-xs">
                <button type="button" title="logout" class="btn btn-icon btn-sm btn-dark" data-toggle="dropdown" data-target="#chat"><i class="fa fa-comment-o"></i></button>
            </div>
        </footer>
    </section>
</aside>