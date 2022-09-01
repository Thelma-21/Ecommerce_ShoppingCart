package servlet;

import model.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/add-To-Cart")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try(PrintWriter out = response.getWriter()){
            List<Cart> cartList = new ArrayList<>();

            int id = Integer.parseInt(request.getParameter("id"));
            Cart cart = new Cart();
            cart.setId(id);
            cart.setQuantity(1);

            HttpSession session = request.getSession();
            List<Cart> cart_List = (List<Cart>) session.getAttribute("cart-list");

            if(cart_List == null){
                cartList.add(cart);
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("index.jsp");
            }else {
                cartList = cart_List;
                boolean exist = false;
                for(Cart c : cart_List){
                    if(c.getId() == id){
                        exist = true;
                        out.println("<h3 style= 'color: crimson; text-align: center'>Item already exist in Cart.<a href= 'cart.jsp'>Go to Cart page</a></h3>");
                    }
                }
                if(!exist){
                    cartList.add(cart);
                    response.sendRedirect("index.jsp");
                }
            }

        }
        }
    }

