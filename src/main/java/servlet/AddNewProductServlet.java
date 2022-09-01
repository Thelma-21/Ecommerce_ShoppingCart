package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/add-new-product")
public class AddNewProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


        String itemName = request.getParameter("name");
        String category = request.getParameter("category");
        String price = request.getParameter("price");
        String image = request.getParameter("image");

        Connection con;
        RequestDispatcher dispatcher;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "thelmasamuel22");
            PreparedStatement pst = con.prepareStatement("insert into Products(itemName,category,price,image) values(?,?,?,?)");
            pst.setString(1, itemName);
            pst.setString(2, category);
            pst.setString(3, price);
            pst.setString(4, image);

            int rowCount = pst.executeUpdate();
            dispatcher = request.getRequestDispatcher("adminDashboard.jsp");
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
