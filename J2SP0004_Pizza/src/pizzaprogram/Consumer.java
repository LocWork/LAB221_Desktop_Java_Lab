/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaprogram;

import java.util.ArrayList;
import java.util.Scanner;


public class Consumer extends Thread {
    //fields
    PizzaQueue queue = null;
//    Producer p = null; // <---- Bien nay sai
//    int n = 0; //<--- Bien nay sai
   
    public Consumer(PizzaQueue queue){
        this.queue = queue;
      
    }    
    //cái constructor này là làm kiểu giao hàng

    
    @Override
    public void run(){
        while(true){
            try {
                String pizza = queue.deliverPizza();
                if(!pizza.isEmpty()){
                    System.out.println("The "+pizza+" has been deliver --");
                }else{
                    System.out.println("Waiting for more pizza to be made");
                }                    
            } catch (Exception e) {
            }
        }
    }
    
    
    
    
//    public Consumer(PizzaQueue queue,Producer p){
//        this.queue = queue;
//        this.p = p;
//    }    
    //code theo giao hàng
//    public void run(){
//        n = p.getNumberOfPizza();// <------- 
//        while(n > 0){
//            String pizza = queue.deliverPizza();
//            if(!pizza.trim().isEmpty()){
//                System.out.println(pizza+" has been deliver");
//                n--;
//            }else{
//                System.out.println("Waiting for more pizza to be made");
//                //Khong duoc cho sleep vao trong loop
//            }
//        }
//        
//        System.out.println("[Consumber]: all order has been deliver!");
//    
//    }
    
  
            
}
