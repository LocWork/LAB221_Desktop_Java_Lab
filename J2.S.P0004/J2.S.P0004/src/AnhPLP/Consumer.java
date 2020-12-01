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
public class Consumer extends Thread {

    Store store = null;

    public Consumer(Store s) {
        store = s;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String x = store.get();
                if (!x.isEmpty()) {
                    System.out.println("-- " + x + " is delivered.");
                } else {
                    System.out.println("Waiting for new pizza.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
