package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/user-signup")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String email = request.getParameter("signup-email");
            String password = request.getParameter("signup-password");

            Connection con;
            RequestDispatcher dispatcher;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "thelmasamuel22");
            PreparedStatement pst = con.prepareStatement("insert into Users(firstName,lastName,email,password) values(?,?,?,?)");
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, email);
            pst.setString(4, password);

            int rowCount = pst.executeUpdate();
            dispatcher = request.getRequestDispatcher("login.jsp");
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
