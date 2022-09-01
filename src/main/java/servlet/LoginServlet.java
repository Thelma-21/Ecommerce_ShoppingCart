package servlet;

import connection.DbCon;
import dao.UserDao;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try(PrintWriter out = response.getWriter()){
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            UserDao userDao = new UserDao(DbCon.getConnection());
            User user = userDao.userLogin(email, password);


            if (user != null){
                request.getSession().setAttribute("auth", user);
                response.sendRedirect("index.jsp");
            }else {
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


