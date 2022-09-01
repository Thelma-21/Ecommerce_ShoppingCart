package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/delete-product")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int productId = Integer.parseInt(request.getParameter("id"));
        Connection con;
        RequestDispatcher dispatcher;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "thelmasamuel22");
            PreparedStatement pst = con.prepareStatement("delete from Products where productId= ?");

            pst.setInt(1, productId);

            int rowCount = pst.executeUpdate();
            dispatcher = request.getRequestDispatcher("deleteProduct.jsp");
            if(rowCount > 0){
                request.setAttribute("status", "success");


            }else{
                request.setAttribute("status", "failed");
            }
            dispatcher.forward(request, response);
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
