package synchronization;

/*
    Using Blocking Queue
    Labour produce the goods of finite count.
    Customer consume the goods unitl empty.
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Labour extends Thread {

    private int id;
    private static BlockingQueue<String> goods;

    public Labour(int id, BlockingQueue<String> goods) {
        this.id = id;
        this.goods = goods;
    }

    @Override
    public void run() {
        for (int i = 0; i < 8; i++) {
            try {
                goods.add(i + "");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }
}

class Customer extends Thread {

    private int id;
    private static BlockingQueue<String> goods;

    public Customer(int id, BlockingQueue<String> goods) {
        this.id = id;
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (goods.isEmpty())
                    break;

                String take = goods.take();
                System.out.println("Get the goods info " + take + " from the queue..person " + id);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class ProducerConsumerBlockingQueueExample {
    public static void main(String[] args) {

        BlockingQueue<String> goods = new ArrayBlockingQueue<>(10);

        for (int i = 0; i < 2; i++)
            new Labour(i, goods).start();

        for (int i = 0; i < 5; i++)
            new Customer(i, goods).start();
    }
}
