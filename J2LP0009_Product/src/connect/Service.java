/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author hi
 */
public class Service implements Serializable{
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    private Statement stm;
    public void closeConnection()throws Exception{
        if(rs != null){
            rs.close();
        }
        if(preStm != null){
            preStm.close();
        }
        if(conn != null){
            conn.close();
        }
        if(stm != null){
            stm.close();
        }
    }
    
    public ArrayList<Category> getCategoryData()throws Exception{
        ArrayList<Category> list = new ArrayList();
        try{
            String sql = "Select * From Category";
            conn = MyConnection.getMyConnection();
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                Category x = new Category(id, name);
                list.add(x);
            }
        }finally{
            closeConnection();
        }
        return list;
    }
    
    
    
//    public void createNewTable(String catagoryName)throws Exception{        
//        try{
//            String sql = "CREATE TABLE "+catagoryName+" ( ProductID varchar(50) not NULL, ProductName varchar(50) not NULL, Quantity int not NULL, Price decimal(18,0) not NULL, PRIMARY KEY (ProductID) );";
//            
//            conn = MyConnection.getMyConnection();
//            stm = conn.createStatement();
//            stm.executeUpdate(sql); //if you want to create then execute update
//        }finally{
//            closeConnection();
//        }
//    }
//    
    public void deletedCatagory(String id)throws Exception{
        try {
            String sql = "Delete From Category Where CategoryID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            preStm.execute();
        }finally{
            closeConnection();
        }
    }
    public void deletedProduct(String id)throws Exception{
        try {
            String sql = "Delete From Product Where ProductID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            preStm.execute();
        }finally{
            closeConnection();
        }
    }    
    
   
//    
    public boolean checkCategoryExist(String id)throws Exception{
        boolean result = false;

        try {
            String sql = "Select * From Category Where CategoryID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if(rs.next()){
                String x = rs.getString(1);
                if(x != null ){
                    result = true;
                }
            }else{
                result = false;
            }
        }finally{
            closeConnection();
        }
        return result;
    }    
//    
//    public ArrayList<String> getAllTableName() throws Exception{
//        ArrayList<String> list = new ArrayList<>();
//        try{
//            String sql = "SELECT name FROM sys.tables;";
//            conn = MyConnection.getMyConnection();
//            preStm = conn.prepareStatement(sql);
//            rs = preStm.executeQuery();
//            while(rs.next()){
//                String name = rs.getString(1);
//               
//                list.add(name);
//            }
//        }finally{
//            closeConnection();
//        }
//        return list;
//    }
//    
    public void updateCategoryName(String id, String newName)throws Exception{
        try {
            String sql = "Update Category Set CategoryName = '"+newName+"' Where CategoryID = '"+id+"';";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.executeUpdate();
        }finally{
            closeConnection();
        }
    }
    
    public void updateProduct(Product x)throws Exception{
        try {
            String sql = "Update Product Set ProductName = ?, Quantity = ?, Price = ? Where ProductID = ?;";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, x.getProductName());
            preStm.setInt(2, x.getQuantity());
            preStm.setDouble(3, x.getPrice());
            preStm.setString(4, x.productID);
            preStm.executeUpdate();
        }finally{
            closeConnection();
        }
    }    
    
    public void newCategory(String id, String name)throws Exception{
        try {
            String sql = "Insert Category(CategoryID,CategoryName) Values (?,?);";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            preStm.setString(2, name);
            preStm.executeUpdate();
        }finally{
            closeConnection();
        }
    }
    public void newProduct(Product x)throws Exception{
        try {
            String sql = "Insert Product(ProductID,ProductName,Quantity,Price,CategoryID) Values (?,?,?,?,?);";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, x.getProductID());
            preStm.setString(2, x.getProductName());
            preStm.setInt(3, x.getQuantity());
            preStm.setDouble(4, x.getPrice());
            preStm.setString(5, x.getCategoryID());
            preStm.executeUpdate();
        }finally{
            closeConnection();
        }
    } 
    public void deletedAllProduct(String id)throws Exception{
        try {
            String sql = "Delete From Product Where CategoryID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            preStm.execute();
        }finally{
            closeConnection();
        }
    }  
    public Vector<Product> getProductData(String categoryID)throws Exception{
        Vector<Product> list = new Vector<>();
        try {
            String sql = "Select * From Product Where CategoryID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, categoryID);
            rs = preStm.executeQuery();
            while(rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                int quality = rs.getInt(3);
                double price = rs.getDouble(4);
                String cid = rs.getString(5);
                Product p = new Product(id, name, quality, price, cid);
                list.add(p);
            }
        }finally{
            closeConnection();
        }
        return list;
    }    
 
    public int getNumberOfProduct(String categoryID)throws Exception{
        int count = 0;
        try {
            String sql = "Select * From Product Where CategoryID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, categoryID);
            rs = preStm.executeQuery();
            while(rs.next()){
                count++;
            }
        }finally{
            closeConnection();
        }
        return count;
    }
    
    public Product getProductByID(String id)throws Exception{
        Product x = null;
        try {
            String sql = "Select * From Product Where ProductID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            while(rs.next()){                
                String pid = rs.getString(1);
                String name = rs.getString(2);
                int quanity = rs.getInt(3);
                double price = rs.getDouble(4);
                String cid = rs.getString(5);
                x = new Product(pid, name, quanity, price, cid);
            }
            
        }finally{
           closeConnection();
        }
        return x;
    }
    public Category getCategoryByID(String id)throws Exception{
        Category x = null;
        try {
            String sql = "Select * From Category Where CategoryID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            while(rs.next()){                
                String pid = rs.getString(1);
                String name = rs.getString(2);
                x = new Category(pid, name);
            }
        }finally{
           closeConnection();
        }
        return x;
    }
        

}
