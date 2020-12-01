/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaprogram;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hi
 */
public class PizzaQueue {
    //fields
    public int size = 10;
    public int pizzaIndex;
    public String mainQueue[];
   
    //constructor
    public PizzaQueue(){
        pizzaIndex = 0;
        mainQueue = new String[size];
    }
    //test case cho size to hon
    public PizzaQueue(int max) {
        pizzaIndex = 0;
        mainQueue = new String[max];
    }
    
    public boolean empty(){
        return pizzaIndex == 0;
    }
    
    
    public  boolean full(){
        return pizzaIndex == size;
    }
    
    public synchronized boolean makePizza(String x){
        
        if(full() == false){
            mainQueue[pizzaIndex] = x;
            pizzaIndex++;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            return true;
        }else{
            return false;
        }
        
        
    }
    
    public synchronized String deliverPizza(){
        String theDeliverPizza = "";
        if(empty() == false){
            theDeliverPizza = mainQueue[0];
            for(int i = 0; i < pizzaIndex-1; i++){
                mainQueue[i] = mainQueue[i + 1];
            }
            pizzaIndex--;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
        }
        return theDeliverPizza;
    }
    
   
    
}


