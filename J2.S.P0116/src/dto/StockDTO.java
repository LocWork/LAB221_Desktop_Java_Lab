package dto;



import java.sql.Date;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class StockDTO {
    private int id;
    private String name;
    private String address;
    private Date dateAvailable;
    private String note;

    public StockDTO() {
        id = 1;
        name = "";
        address = "";
        dateAvailable = null;
        note = "";
    }

    public StockDTO(int id, String name, String address, Date date, String note) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateAvailable = date;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(Date dateAvailable) {
        this.dateAvailable = dateAvailable;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public Vector toVector() {
        //show information
        Vector vector = new Vector();
        vector.add(id);
        vector.add(name);
        vector.add(address);
        vector.add(dateAvailable);
        vector.add(note);
        return vector;
    }
    
}
