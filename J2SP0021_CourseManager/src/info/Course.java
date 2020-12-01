/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info;

/**
 *
 * @author hi
 */
public class Course implements Comparable<Course>{
    //field
    String code;
    String name;
    int credit;
    public Course(){}

    public Course(String code, String name, int credit) {
        this.code = code;
        this.name = name;
        this.credit = credit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return code+" | "+name+" | "+credit;
    }

    @Override
    public int compareTo(Course t) {
        if(credit > t.credit){
            return 1;   //swap hai con cho
                        //return 1 co nghia la doi vi chi hai con dog     
        }
        else if(credit< t.credit){
            return -1;  //-1 co nghia la giu nguyen
        }
        return 0;        
    }
    
}
