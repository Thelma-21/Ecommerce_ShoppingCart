<%@ page import="dao.AdminDao" %>
<%@ page import="connection.DbCon" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dec
  Date: 09/08/2022
  Time: 9:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    AdminDao adminDao;
    try {
        adminDao = new AdminDao(DbCon.getConnection());
    } catch (ClassNotFoundException | SQLException e) {
        throw new RuntimeException(e);
    }
    List<Product> productList = adminDao.viewProducts();
%>
<html>
<head>
    <title>Title</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container">
        <a class="navbar-brand" href="adminDashboard.jsp">Admin Dashboard</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="adminDashboard.jsp">View Product</a></li>
                <li class="nav-item"><a class="nav-link" href="addNewProduct.jsp">Add New Product</a></li>
            </ul>

        </div>
    </div>
</nav>


<div class="container">
    <div class="card-header my-3 ">All Products</div>
    <div class="row">
        <%
            if(!productList.isEmpty()){
                for(Product p: productList){%>
        <div class="col-md-3 my-3">
            <div class="card w-100" style="width: 18rem;">
                <img src="images/<%=p.getImage()%>" class="card-img-top" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title"><%=p.getItemName()%></h5>
                    <h6 class="price">$<%=p.getPrice()%></h6>
                    <h6 class="category"><%=p.getCategory()%></h6>
                    <div class="mt-3 d-flex justify-content-between">
                        <form action="updateProduct?id=<%=p.getId()%>" method="post">
                            <button type="submit" class="btn-primary">Update Product</button>
                        </form>
                        <form action="delete-product?id=<%=p.getId()%>" method="post">
                            <button type="submit" class="btn-primary">Delete Product</button>
                        </form>
                    </div>
                    <%--                            <form method="POST" action="/deleteser?id=<%= p.getId()%>">--%>
                    <%--                                <button type="submit">Delet</button>--%>
                    <%--                            </form>--%>
                    <%--                            <a href="" class="btn btn-primary">edit</a>--%>
                </div>
            </div>
        </div>
        <%}
        }%>

    </div>
</div>

</body>
</html>
<%--<a href="update?id=<%=p.getId() %>" class=" btn-primary">Update Product</a>--%>