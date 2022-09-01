<%--
  Created by IntelliJ IDEA.
  User: dec
  Date: 07/08/2022
  Time: 12:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Cart" %>
<%@ page import="dao.ProductDao" %>
<%@ page import="connection.DbCon" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        request.getSession().setAttribute("auth", auth);
    }

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    if(cart_list != null){

        ProductDao pd;
        try {
            pd = new ProductDao(DbCon.getConnection());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        cartProduct = pd.getCartProducts(cart_list);
        double total = pd.getTotalCartPrice(cart_list);
        request.setAttribute("cart_list", cart_list);
        request.setAttribute("total", total);

    }
//    int cartSize = cart_list != null ? cart_list.size() : 0;
%>

<html>
<head>
    <title>Title</title>
    <%@include file="includes/head.jsp"%>
</head>

<body>
<%@include file="includes/navbar.jsp"%>
<div class="container">
    <div class="d-flex py-3"><h3>Total Price: $ ${ (total >0)? total:0 }</h3><a class="mx-3 btn btn-primary" href="#">Check Out</a> </div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Price</th>
            <th scope="col">Buy Now</th>
            <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
        <%
            if(cart_list != null){
                for(Cart cart: cartProduct){%>
                   <tr>
                    <td><%=cart.getItemName()%></td>
                    <td><%=cart.getCategory()%></td>
                    <td>$<%=cart.getPrice()%></td>
                    <td>
                        <form action="" method="post" class="form-inline">
                            <input type="hidden" name="productId" value="<%=cart.getId()%>" class="form-input">
                            <div class="form-group d-flex justify-content-between">
                                <a class="btn btn-sm btn-decrement" href="quantity-inc-dec?action=dec&id=<%=cart.getId()%>"><i class="fas fa-minus-square"></i></a>
                                <input type="text" name="quantity" class="form-control" value="<%=cart.getQuantity()%>" readonly="readonly">
                                <a class="btn btn-sm btn-increment" href="quantity-inc-dec?action=inc&id=<%=cart.getId()%>"><i class="fas fa-plus-square"></i></a>
                            </div>
                        </form>
                    </td>
                    <td><a class="btn btn-sm btn-danger" href="remove-from-cart?id=<%=cart.getId() %>">Remove</a></td>
                    </tr>
                <%}
            }
             %>

        </tbody>
    </table>
</div>
</body>
</html>
