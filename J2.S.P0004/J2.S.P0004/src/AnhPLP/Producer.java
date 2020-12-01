/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnhPLP;

import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author theFrozenAdam
 */
public class Producer extends Thread {

    Store store = null;
    String product = "";
    Vector<String> pizza = new Vector();
    Scanner sc = new Scanner(System.in);

    public Producer(Store s) {
        store = s;
    }

    public void addProduct() {
        System.out.print("Enter list of pizzas which provided by the restaurant (seperate by coma): ");
        product = sc.nextLine();
        Scanner sc1 = new Scanner(product);
        String delim = "[,]+";
        sc1.useDelimiter(delim);
        while (sc1.hasNext()) {
            pizza.add(sc1.next().trim());
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                int index = new java.util.Random().nextInt(pizza.size());
                String value = pizza.get(index);
                boolean result = store.put(value);
                if (result == true) {
                    System.out.println("** New " + value + " is made.");
                } else {
                    System.out.println("Store is full");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
