/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaprogram;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author hi
 */
public class Producer extends Thread{
    
    //fields
    String pizzaName;
    PizzaQueue queue = null;
    ArrayList<String> orderList = new ArrayList<>();// <--- Chua cac loai pizza
//    int numberOfPizza;// <---- Code nay sai
    
    public Producer(PizzaQueue queue){
        this.queue = queue;
               
    }
    
    public void getTypePizza(){
        Scanner s = new Scanner(System.in);
        do {            
            System.out.print("Please enter type of pizza (seperate by comma): ");
            s = new Scanner(System.in);
            String order = s.nextLine();
          
            order = order.replaceAll("[\\s]+", " ");
            String[] list2 = order.split(",");
            
            //Thay doi tat cac khoan trong bang 1 khoan trong
//            for(int i = 0; i < list2.length; i++){
//                System.out.println(list2[i].replace("[\\s]+", " "));
//                list2[i] = list2[i].replace("[\\s]+", " ");
//            }
            
            for(int i = 0; i < list2.length; i++){
                if(Validator(list2[i])){
                    orderList.add(list2[i]);
                }
            }             
        } while (orderList.isEmpty());
    }    
    @Override
     public void run(){
        while(true){
            try {
                Random rando = new Random();
                int randomIndex = rando.nextInt(orderList.size());
                boolean make = queue.makePizza(orderList.get(randomIndex));
                String pizza = orderList.get(randomIndex);
                if(make == true){
                    System.out.println("The "+pizza+" has been made ++");
                    
                }else{
                    System.out.println("The queue is full");
                
                }                
                
                
            } catch (Exception e) {
            }
        }
     }

     
// Code này là sai vì nó đí theo lối đặt hàng
//    public void run(){
//        while(orderList.size() > 0){
//            try {
//                String pizza = orderList.get(0);
//                boolean make = queue.makePizza(pizza);
//                if(make == true){
//                    orderList.remove(0);
//                    System.out.println("The "+pizza+" has been made");
//                    
//                }else{
//                    System.out.println("The queue is full");
//                    // Khong duoc cho sleep vao trong loop no crash
////                    Thread.sleep(4000);
//                }
//                
//                
//            } catch (Exception e) {
//            }
//        }
//        System.out.println("[Producer]: All order has been completed!");
//      
//    }
    
//    public void orderPizza(){
//        Scanner s = new Scanner(System.in);
//        System.out.print("Please enter your order: ");
//        s = new Scanner(System.in);
//        String order = s.nextLine();
//        String[] list2 = order.split(",");
//        for(int i = 0; i < list2.length; i++){
//            if(Validator(list2[i])){
//                orderList.add(list2[i]);
//            }
//        }        
//    }
//    
//    public int getNumberOfPizza(){
//        return orderList.size();
//    }
    
   public boolean Validator(String name){
        
        String reg = "[a-zA-Z\\s]+";
        try {
            if(name.isBlank())throw new Exception();
            if(!name.matches(reg))throw new Exception();
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }        
    
    
   
}
