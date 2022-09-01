package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public UserDao(Connection con) {
        this.con = con;
    }

    public User userLogin(String email, String password){
        User user = null;
        try {
            query = "select * from Users where email= ?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();


            if(rs.next()){
                String db_password = rs.getString("password");
                if(password.equals(db_password)){
                    user = new User();
                    user.setUserId(rs.getInt("userID"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setEmail(rs.getString("email"));

                }else {
                    System.out.println("Invalid Password");
                }
            }else {
                System.out.println("user doesnt exist");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        return user;
    }


}
