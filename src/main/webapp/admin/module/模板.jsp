<%--
  Created by IntelliJ IDEA.
  User: Raomengnan
  Date: 2016/11/19
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="app">

<jsp:include page="/admin/module/head.jsp"></jsp:include>

<body>
<section class="vbox">
    <jsp:include page="/admin/module/header.jsp"></jsp:include>

    <section>
        <section class="hbox stretch">
            <!-- .侧边导航栏 -->
            <jsp:include page="/admin/module/left.jsp"></jsp:include>
            <!-- /.侧边导航栏 -->

            <%--工作区--%>
            <section id="content">

            </section>
            <%--/工作区--%>

            <aside class="bg-light lter b-l aside-md hide" id="notes">
                <div class="wrapper">Notification</div>
            </aside>

        </section>
    </section>
</section>

<jsp:include page="/admin/module/foot.jsp"></jsp:include>

</body>
</html>
