/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import info.Course;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author hi
 */
public class ConnectService {
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://SE140140\\MSSQL_SE140140:1433; databaseName=Course; "
                +" user=sa; password=123";    
    
    public Connection openConnection() throws Exception{
        Class.forName(driver);
        return DriverManager.getConnection(url);        
    }
    
     public Vector<Course> getData() throws Exception{
        Vector<Course> list = new Vector();
        String query = "Select * From tblCourse";
        try(Connection c = openConnection(); Statement stm = c.createStatement()){
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                String Code = rs.getString("Code");
                String Name = rs.getString("Name");
                int Credit = rs.getInt("Credit");
                Course co = new Course(Code, Name, Credit);               
                list.add(co);
                }
            }
            return list;
        }  
    public Course getDataByCode(String code)throws Exception{ 
        String query = "Select * From tblCourse Where Code = ?";
        try(Connection c = openConnection(); PreparedStatement pstm = c.prepareStatement(query)){
            pstm.setString(1, code);
            ResultSet rs =pstm.executeQuery();
            while(rs.next()){
                String Code = rs.getString("Code");
                String Name = rs.getString("Name");
                int Credit = rs.getInt("Credit");
                Course co = new Course(Code, Name, Credit); 
                 return co;
            }
        }
        return null;
    }   
    
    public int insertItem(Course item)throws Exception{
        String query = "Insert into tblCourse Values(?,?,?)";
        try(Connection c = openConnection(); PreparedStatement pstm = c.prepareStatement(query)){
            pstm.setString(1, item.getCode());
            pstm.setString(2, item.getName());
            pstm.setInt(3, item.getCredit());
            return pstm.executeUpdate();
        }
    } 
    
    
}
