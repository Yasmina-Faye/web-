<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${basePath}">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商城</title>
    <link rel="stylesheet" href="bs/css/bootstrap.css">
    <script type="text/javascript" src="bs/js/jquery.min.js"></script>
    <script type="text/javascript" src="bs/js/bootstrap.js"></script>

    <link rel="stylesheet" type="text/css" href="bs/validform/style.css">
    <script type="text/javascript" src="bs/validform/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript" src="js/goods/user_reg_login.js"></script>
    <script type="text/javascript" src="js/goods/landing.js"></script>
    <link href="css/goods/head_footer.css" rel="stylesheet" type="text/css">
    <link href="css/goods/user_reg_login.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/goods/search-autocomplete.js"></script>

</head>
<script>

</script>

</script>
<body>
<c:if test="${!empty infoList}">
    <c:forEach items="${infoList}" var="i">
        <script type="text/javascript">
            alert("${i}")
        </script>
    </c:forEach>
</c:if>
<div class="container-fullid">
    <%@include file="header.jsp" %>
    <div class="wrapper">
        <!-- main start -->
        <div class="main container">
            <div style="text-align: center;font-size: 25px;padding-bottom: 30px">我的资料</div>
            <!-- 注册表单 -->
            <div id="myTabContent">
                <div id="tab_reg">
                    <form id="myForm" action="UserServlet?action=update" method="post" class="form-horizontal">
                        <input type="hidden" name="userId" value="${userInfo.userId}">
                        <div class="form-group">
                            <label for="userName" class="col-md-2 col-md-offset-2 control-label">用户名：</label>
                            <div class="col-md-4">
                                <p class="form-control-static">${userInfo.userName}</p>
                            </div>

                        </div>
                        <div class="form-group">
                            <label for="passWord" class="col-md-2 col-md-offset-2 control-label">密码：</label>
                            <div class="col-md-6">
                                <input type="password" name="passWord" id="passWord" class="form-control" value="${userInfo.userPassWord }">
                            </div>
                            <div class="col-md-4">
                                <span class="Validform_checktip"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="c_passWord" class="col-md-2 col-md-offset-2 control-label">确认密码：</label>
                            <div class="col-md-6">
                                <input type="password" name="c_passWord" id="c_passWord" class="form-control" value="${userInfo.userPassWord }">
                            </div>
                            <div class="col-md-4">
                                <span class="Validform_checktip"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-md-2 col-md-offset-2 control-label">姓名：</label>
                            <div class="col-md-6">
                                <input type="text" id="name" name="name" class="form-control" value="${userInfo.name }">
                            </div>
                            <div class="col-md-4">
                                <span class="Validform_checktip"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 col-md-offset-2 control-label">性别：</label>
                            <div class="col-md-6 ">
                                <c:choose>
                                    <c:when test="${userInfo.sex eq '男'}">
                                        <label class="radio-inline">
                                            <input type="radio" name="sex" id="sex" checked="checked" class="pr1" value="男">男
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="sex"  class="pr1"  value="女">女
                                        </label>
                                    </c:when>
                                    <c:otherwise>
                                        <label class="radio-inline">
                                            <input type="radio" name="sex" id="sex" class="pr1" value="男">男
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="sex" checked="checked"  class="pr1"  value="女">女
                                        </label>
                                    </c:otherwise>
                                </c:choose>

                            </div>

                        </div>
                        <div class="form-group">
                            <label for="age" class="col-md-2 col-md-offset-2 control-label">年龄：</label>
                            <div class="col-md-6">
                                <input type="text" id="age" name="age" class="form-control" value="${userInfo.age }">
                            </div>
                            <div class="col-md-4">
                                <span class="Validform_checktip"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="tell" class="col-md-2 col-md-offset-2 control-label">电话：</label>
                            <div class="col-md-6">
                                <input type="text" id="tell" name="tell" class="form-control" value="${userInfo.tell }">
                            </div>
                            <div class="col-md-4">
                                <span class="Validform_checktip"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="address" class="col-md-2 col-md-offset-2 control-label">地址：</label>
                            <div class="col-md-6">
                                <input type="text" id="address" name="address" class="form-control" value="${userInfo.address }">
                            </div>
                            <div class="col-md-4">
                                <span class="Validform_checktip"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3  control-label col-md-offset-5">
                                <input class="btn btn-success btn-block" type="submit" value="更改">
                            </label>

                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
</div>


<script type="text/javascript">


</script>
</body>
</html>