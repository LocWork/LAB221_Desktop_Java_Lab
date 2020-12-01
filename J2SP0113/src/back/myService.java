/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author hi
 */
public class myService implements Serializable{ 
    
    public static Connection myConnection()throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=BookDB", "sa","123");
        return conn;
    }
    
    Connection conn;
    PreparedStatement preStm;
    Statement stm;
    ResultSet rs;
    
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
    
    public ArrayList<String> getAllBookName() throws Exception{
        ArrayList<String> list = new ArrayList<>();
        try{
            String sql = "SELECT BookName FROM Book;";
            conn = myConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while(rs.next()){
                String name = rs.getString(1);
               
                list.add(name);
            }
        }finally{
            closeConnection();
        }
        return list;
    }
    public ArrayList<String> getAllCode() throws Exception{
        ArrayList<String> list = new ArrayList<>();
        try{
            String sql = "SELECT BookCode FROM Book;";
            conn = myConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while(rs.next()){
                String name = rs.getString(1);
               
                list.add(name);
            }
        }finally{
            closeConnection();
        }
        return list;
    }  
    
    public Book getBookData(String code, String name)throws Exception{
        Book x = null;
        try {
            String sql = "Select * From Book Where BookCode = '"+code+"' and BookName = '"+name+"';";
            conn = myConnection();
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            if(rs.next()){
                String bCode = rs.getString("BookCode");
                String bName = rs.getString("BookName");
                String author = rs.getString("Author");
                String publisher = rs.getString("Publisher");
                
                int year = rs.getInt("PublishYear");
                boolean rent = rs.getBoolean("Rent");
//                System.out.println(bCode + bName + author + publisher + year + rent);
                x = new Book(bCode, bName, author, publisher, year, rent);
                x.toString();
            }
        }finally{
            closeConnection();
        }
        
        return x;
    }
    
    public boolean checkBookExist(String code)throws Exception{
        boolean result = false;

        try {
            String sql = "SELECT * From Book Where BookCode = '"+code+"';";
            conn = myConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if(rs.next()){
                String x = rs.getString(1);
//                System.out.println(x);
                if(x != null){
                    result = true;
                }else{
                    result = false;
                }
            }
        }finally{
            closeConnection();
        }
        return result;
    }
    public void insertBook(Book nb)throws Exception{
        String query = "Insert into Book Values(?,?,?,?,?,?)";
        try{
            conn = myConnection();
            preStm = conn.prepareStatement(query);
            preStm.setString(1, nb.getCode());
            preStm.setString(2, nb.getName());
            preStm.setString(3, nb.getAuthor());
            preStm.setString(4, nb.getPublisher());
            preStm.setInt(5, nb.getYear());
            preStm.setBoolean(6, nb.isForRent());
            preStm.executeUpdate();
        }finally{
            closeConnection();
        }
    } 
    
    public void updateBook(Book nb)throws Exception{
         String query = "Update Book Set BookName = ?, Author = ?, "
                + "Publisher = ?, PublishYear = ?, Rent = ? Where BookCode = ?";
        try{
            conn = myConnection();
            preStm = conn.prepareStatement(query);
            preStm = conn.prepareStatement(query);
            preStm.setString(6, nb.getCode());
            preStm.setString(1, nb.getName());
            preStm.setString(2, nb.getAuthor());
            preStm.setString(3, nb.getPublisher());
            preStm.setInt(4, nb.getYear());
            preStm.setBoolean(5, nb.isForRent());
            preStm.execute();
        }finally{
            closeConnection();
        }
        
    }  
    
    public void removeBook(String code)throws Exception{
        String query = "Delete From Book Where BookCode = ?";
        try{
            conn = myConnection();
            preStm = conn.prepareStatement(query);
            preStm.setString(1, code);
            preStm.executeUpdate();
            
        }finally{
            closeConnection();
        }
        
    }    
}
