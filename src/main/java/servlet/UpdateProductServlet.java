package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/updateProduct")
public class UpdateProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int productId = Integer.parseInt(request.getParameter("id"));
        String itemName = request.getParameter("name");
        String category = request.getParameter("category");
        String price = request.getParameter("price");
        String image = request.getParameter("image");


        Connection con;
        RequestDispatcher dispatcher;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "thelmasamuel22");
            PreparedStatement pst = con.prepareStatement("update Products set itemName = ?, category = ?, price = ?, image = ? where productId = ?");
            pst.setInt(1, productId);
            pst.setString(2, itemName);
            pst.setString(3, category);
            pst.setString(4, price);
            pst.setString(5, image);


            int rowCount = pst.executeUpdate();
            dispatcher = request.getRequestDispatcher("updateProduct.jsp");
            if(rowCount > 0){
                request.setAttribute("status", "success");


            }else{
                request.setAttribute("status", "failed");
            }

            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
