package barriers;

/**
 * Deciding how many bags in the inventory
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Inventory extends Thread {

    public static int bags;
    private String user;

    private static CountDownLatch countDownLatch = new CountDownLatch(5);
    private static Lock pencil = new ReentrantLock();
    public Inventory(String user) {
        this.user = user;
    }

    @Override
    public void run() {
        if (user.contains("Duke")) {
            try {
                pencil.lock();
                bags += 3;
                System.out.println("Duke after add..current bags : " + bags);
            } finally {
                pencil.unlock();
            }
            countDownLatch.countDown();
        } else if (user.contains("Martin")) {
            try{
                countDownLatch.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            try {
                pencil.lock();
                bags *= 2;
                System.out.println("Martin after multiply..current bags : " + bags);
            } finally {
                pencil.unlock();
            }
        }
    }
}


public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        Inventory[] user = new Inventory[10];

        for (int i = 0; i < 5; i++)
            user[i] = new Inventory("Duke");

        for (int i = 5; i < 10; i++)
            user[i] = new Inventory("Martin");

        for (int i = 0; i < 10; i++)
            user[i].start();

        for (int i = 0; i < 10; i++)
            user[i].join();

        System.out.println("Total Bags after operation : " + Inventory.bags);
    }
}
