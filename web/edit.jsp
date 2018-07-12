<%--
  Created by IntelliJ IDEA.
  User: 94545
  Date: 2018/1/20
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/edit.css">
    <title>Edit Product</title>
</head>
<body>
    <br><br>
    <h3 align="center">Edit Product</h3>
    <form  action="ProductServlet" method="post">
        <input type="hidden" name="method" value="edit"/>
        <input type="hidden" name="id" value="${product.id}">
        <div id="edit">
            <input type="text" name="barcode" required="required" placeholder="Barcode" value="${product.barcode}"/>
            <input type="text" name="name" required="required" placeholder="Name" value="${product.name}"/>
            <input type="text" name="units" required="required" placeholder="Units" value="${product.units}"/>
            <input type="text" name="purchasePrice" required="required" placeholder="PurchasePrice" value="${product.purchasePrice}"/>
            <input type="text" name="salePrice" required="required" placeholder="SalePrice" value="${product.salePrice}"/>
            <input type="text" name="inventory" required="required" placeholder="Inventory" value="${product.inventory}"/>
            <input class="button" type="submit" name="submit"/>
            <input class="button" type="reset" name="reset"/>
        </div>
    </form>
</body>
</html>
