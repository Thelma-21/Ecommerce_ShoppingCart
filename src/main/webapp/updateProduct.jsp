<%--
  Created by IntelliJ IDEA.
  User: dec
  Date: 15/08/2022
  Time: 8:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <form action="updateProduct" method="get">
        <h5>Product Name</h5><input type="text" name="name">
        <h5>Product Category</h5><input type="text" name="category">
        <h5>Price</h5><input type="text" name="price">
        <h5>Product Image</h5><input type="file" name="image">
        <input type="submit" name="updateProduct" value="Update Product">
    </form>
</div>
</body>
</html>
