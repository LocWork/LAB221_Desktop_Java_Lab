package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import dto.StockDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class StockDAO {
    public List<StockDTO> getStockList() {
        List<StockDTO> list = new ArrayList<StockDTO>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = dbutils.DBConnection.getConnection();
            if (con!=null) {
                String sql = "SELECT StockID, StockName, Address, DateAvailable, Note"
                        + " FROM Stocks";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("StockID");
                    String name = rs.getString("StockName");
                    String address = rs.getString("Address");
                    Date date = rs.getDate("DateAvailable");
                    String note = rs.getString("Note");
                    list.add(new StockDTO(id, name, address, date, note));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public void updateStocks(List<StockDTO> list){
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = dbutils.DBConnection.getConnection();
            if (con != null) {
                String sql = "UPDATE Stocks SET StockName=?, Address=?, DateAvailable=?, Note=?"
                        + " WHERE StockID=?";
                con.setAutoCommit(false);
                stm = con.prepareStatement(sql);
                for (StockDTO dto : list) {
                    stm.setString(1, dto.getName());
                    stm.setString(2, dto.getAddress());
                    stm.setDate(3, dto.getDateAvailable());
                    stm.setString(4, dto.getNote());
                    stm.setInt(5, dto.getId());
                    stm.executeUpdate();
                }               
                con.commit();
            }
        } catch (Exception e) {
            try {
                e.printStackTrace();
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
