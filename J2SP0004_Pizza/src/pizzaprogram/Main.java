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
public class Main {
    
    static PizzaQueue pq;
    static Producer p;
    static Consumer c;
    
    public static void main(String[] args) {
        pq = new PizzaQueue();
        p = new Producer(pq);
        c = new Consumer(pq);
      
   
        p.getTypePizza();
        p.start();
        c.start();
         
      
 
        
    }
    

    
 
}
