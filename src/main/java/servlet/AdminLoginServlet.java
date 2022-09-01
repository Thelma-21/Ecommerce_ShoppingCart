package servlet;

import connection.DbCon;
import dao.AdminDao;
import model.User;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try(PrintWriter out = response.getWriter()){
            String email = request.getParameter("ad-login-email");
            String password = request.getParameter("ad-login-password");

            AdminDao adminDao = new AdminDao(DbCon.getConnection());
            User user = adminDao.adminLogin(email, password);

            if(user != null) {
                out.print("admin login");
                request.getSession().setAttribute("auth", user);
                response.sendRedirect("adminDashboard.jsp");

            }else {
                out.print("admin login failed");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
