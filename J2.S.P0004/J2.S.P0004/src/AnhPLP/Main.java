/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnhPLP;

/**
 *
 * @author theFrozenAdam
 */
public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        Producer pro = new Producer(store);
        Consumer con = new Consumer(store);
        pro.addProduct();
        pro.start();
        con.start();
    }
}
