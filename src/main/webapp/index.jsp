<%@ page import="connection.DbCon" %>
<%@ page import="model.User" %>
<%@ page import="dao.ProductDao" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="model.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        request.getSession().setAttribute("auth", auth);}

    ProductDao pD;
    try {
        pD = new ProductDao(DbCon.getConnection());
    } catch (ClassNotFoundException | SQLException e) {
        throw new RuntimeException(e);
    }

    List<Product> products = pD.getAllProducts();

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }

%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to ZivaStores!</title>
    <%@include file="includes/head.jsp" %>
</head>
<body>

    <%@include file="includes/navbar.jsp"%>

    <div class="container">
        <div class="card-header my-3 ">All Products</div>
        <div class="row">
            <%
            if(!products.isEmpty()){
                for(Product p: products){%>
                    <div class="col-md-3 my-3">
                    <div class="card w-100" style="width: 18rem;">
                        <img src="images/<%=p.getImage()%>" class="card-img-top" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title"><%=p.getItemName()%></h5>
                            <h6 class="price">$<%=p.getPrice()%></h6>
                            <h6 class="category"><%=p.getCategory()%></h6>
                            <div class="mt-3 d-flex justify-content-between">
                                <a href="add-To-Cart?id=<%=p.getId() %>" class="btn btn-sm btn-primary">Add to Cart <i class="fa-solid fa-cart-shopping"></i></a>
                                <a href="#" class="btn btn-sm btn-success">Add to Wishlist <i class="fa-solid fa-heart"></i></a>
                            </div>
                        </div>
                    </div>
                    </div>
                <%}
            }%>

        </div>
    </div>
</body>
</html>