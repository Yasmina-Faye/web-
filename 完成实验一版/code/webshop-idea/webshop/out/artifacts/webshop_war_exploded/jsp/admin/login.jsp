<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>网上商城登录</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 2rem;
        }

        #login {
            background: white;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 300px;
        }

        form p {
            margin: 0;
            padding: 10px 0;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #007BFF;
            color: white;
            cursor: pointer;
            border: none;
            border-radius: 4px;
            padding: 10px 20px;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
    <script type="text/javascript">
        function checkForm() {
            var userName = document.getElementById("userName");
            var passWord = document.getElementById("passWord");
            if (userName.value.length <= 0) {
                alert("请输入用户名！");
                userName.focus();
                return false;
            }
            if (passWord.value.length <= 0) {
                alert("请输入密码！");
                passWord.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<c:if test="${!empty infoList}">
    <c:forEach items="${infoList}" var="i">
        <script type="text/javascript">
            alert("${i}")
        </script>
    </c:forEach>
</c:if>



<div id="login">
    <h1 id="title">商城后台管理系统&nbsp;</h1>
    <form action="jsp/admin/LoginServlet" method="post"
          onsubmit="javascript:return checkForm()">
        <p>
            <input type="text" name="userName" id="userName" placeholder="用户名">
        </p>
        <p>
            <input type="password" name="passWord" id="passWord"
                   placeholder="密码">
        </p>
        <p>
            <input type="submit" id="submit" value="登 录">
        </p>
    </form>
</div>
</body>
</html>