<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/1/20
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <base target="welcome">
</head>
<body style="text-align: center">

    <h1>商品信息管理系统</h1>
    <a href="add.jsp" >添加商品</a>
    <a href="<c:url value='/ProductServlet?method=showAll'/> ">显示商品</a>
    <a href="query.jsp">查找商品</a>


</body>
</html>
