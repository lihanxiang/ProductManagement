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
    <title>Find Product</title>
</head>
<body>
    <h3 align="center">Find Product</h3>
    <form  action="<c:url value="/ProductServlet"/> " method="get">
        <input type="hidden" name="method" value="query">
        <table border="0" align="center" width="30%">
            <tr>
                <td width="10%" >Barcode</td>
                <td width="20%">
                    <input type="text" name="barcode">
                </td>
            </tr>
            <tr>
                <td>Name</td>
                <td>
                    <input type="text" name="name">
                </td>
            </tr>
            <tr>
                <td>Units</td>
                <td>
                    <input type="text" name="units">
                </td>
            </tr>
            <tr>
                <td>PurchasePrice</td>
                <td>
                    <input type="text" name="purchasePrice" />
                </td>
            </tr>
            <tr>
                <td>SalePrice</td>
                <td>
                    <input type="text" name="salePrice"/>
                </td>
            </tr>
            <tr>
                <td>Inventory</td>
                <td>
                    <input type="text" name="inventory"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" name="submit"/>
                    <input type="reset" name="reset"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
