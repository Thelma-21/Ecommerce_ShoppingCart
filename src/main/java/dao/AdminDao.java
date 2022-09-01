package dao;

import model.Product;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public AdminDao(Connection con) {
        this.con = con;
    }

    public User adminLogin(String email, String password){
        User admin = null;
        try{
            query = "select * from Admin where email=? and password=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if(rs.next()){
                admin = new User();
                admin.setAdminId(rs.getInt("adminId"));
                admin.setFirstName(rs.getString("firstName"));
                admin.setLastName(rs.getString("lastName"));
                admin.setEmail(rs.getString("email"));

            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return admin;
    }

    public List<Product> viewProducts(){
        List<Product> productList = new ArrayList<>();

        try{
            query = "select * from Products";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()){
                Product row = new Product();
                row.setId(rs.getInt("productId"));
                row.setItemName(rs.getString("itemName"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getDouble("price"));
                row.setImage(rs.getString("image"));

                productList.add(row);

            }

        }catch (Exception e){
            e.printStackTrace();
        }



        return productList;
    }



}
