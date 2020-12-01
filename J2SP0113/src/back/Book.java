/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

/**
 *
 * @author hi
 */
public class Book {
    String code;
    String name;
    String author;
    String publisher;
    int year;
    boolean forRent;

    public Book(String code, String name, String author, String publisher, int year, boolean forRent) {
        this.code = code;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.forRent = forRent;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public boolean isForRent() {
        return forRent;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setForRent(boolean forRent) {
        this.forRent = forRent;
    }
    
}
