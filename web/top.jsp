<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/1/20
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <base target="welcome">
</head>
<body style="text-align: center">
        <h1>Product Management System</h1>
        <a href="add.jsp" >Add Product</a>
        <a href="<c:url value='/ProductServlet?method=showAll'/> ">Show Products</a>
        <a href="query.jsp">Find Product</a>
</body>
</html>
