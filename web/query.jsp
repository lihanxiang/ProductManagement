<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/1/20
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h3 align="center">查找商品</h3>
    <form  action="<c:url value="/ProductServlet"/> " method="get">
        <input type="hidden" name="method" value="query">
        <table border="0" align="center" width="50%">
            <tr>
                <td width="150px" >商品条形码</td>
                <td width="20%">
                    <input type="text" name="barcode">
                </td>
            </tr>
            <tr>
                <td>商品名称</td>
                <td>
                    <input type="text" name="name">
                </td>
            </tr>
            <tr>
                <td>单位</td>
                <td>
                    <input type="text" name="units">
                </td>
            </tr>
            <tr>
                <td>进价</td>
                <td>
                    <input type="text" name="purchasePrice" />
                </td>
            </tr>
            <tr>
                <td>售价</td>
                <td>
                    <input type="text" name="salePrice"/>
                </td>
            </tr>
            <tr>
                <td>库存</td>
                <td>
                    <input type="text" name="inventory"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" name="搜索"/>
                    <input type="reset" name="重置"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
