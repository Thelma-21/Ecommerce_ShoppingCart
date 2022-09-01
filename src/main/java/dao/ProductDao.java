package dao;

import model.Cart;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProductDao(Connection con) {
        this.con = con;
    }

    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();

        try {
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

                products.add(row);

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }

    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> products = new ArrayList<>();

            try {
                if(cartList.size()>0){
                    for(Cart item: cartList) {
                        query = "select * from Products where productId=?";
                        pst = this.con.prepareStatement(query);
                        pst.setInt(1, item.getId());
                        rs = pst.executeQuery();
                        while (rs.next()){
                            Cart row = new Cart();
                            row.setId(rs.getInt("productId"));
                            row.setId(rs.getInt("productId"));
                            row.setItemName(rs.getString("itemName"));
                            row.setCategory(rs.getString("category"));
                            row.setPrice(rs.getDouble("price")* item.getQuantity());
                            row.setQuantity(item.getQuantity());
                            products.add(row);


                        }
                    }
                }

            }catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }


        return products;
    }

    public double getTotalCartPrice(ArrayList<Cart> cartList){
        double sum = 0;
        try {
            if(cartList.size() > 0){
                for (Cart item : cartList){
                    query = "select price from Products where productId =?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()){
                        sum += rs.getDouble("price")*item.getQuantity();
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return sum;
    }
}

