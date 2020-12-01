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
public class Store {

    int n;
    int maxN = 2;
    String[] product;

    public Store() {
        n = 0;
        product = new String[maxN];
    }

    private boolean empty() {
        return n == 0;
    }

    private boolean full() {
        return n == product.length;
    }

    public synchronized boolean put(String x) {
        if (full()) {
            return false;
        }
        product[n] = x;
        n++;
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        return true;
    }

    public synchronized String get() {
        String result = "";
        if (!empty()) {
            result = product[0];
            for (int i = 0; i < product.length - 1; i++) {
                product[i] = product[i + 1];
            }
            n--;
        }
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        return result;
    }
}
