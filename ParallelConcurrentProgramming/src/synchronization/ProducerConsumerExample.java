package synchronization;

/*
    Using Wait, Notify & NotifyAll
    Labour produce the goods of finite count.
    Customer consume the goods unitl empty.
 */

import java.util.ArrayList;
import java.util.List;

class Producer extends Thread {

    private int id;
    private List<String> goods;

    public Producer(int id, List<String> goods) {
        this.id = id;
        this.goods = goods;
    }

    @Override
    public void run() {
        for (int i = 0; i < 8; i++) {
            try {

                while (goods.size() == 10) {
                    synchronized (goods) {
                        goods.wait();
                    }
                }

                synchronized (goods) {
                    goods.notifyAll();
                    goods.add(i + "");
                }
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }
}

class Consumer extends Thread {

    private int id;
    private List<String> goods;

    public Consumer(int id, List<String> goods) {
        this.id = id;
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true) {
            try {
                while (goods.isEmpty()) {
                    synchronized (goods) {
                        goods.wait();
                    }

                }

                synchronized (goods) {
                    String take = goods.remove(0);
                    System.out.println("Get the goods info " + take + " from the queue..person " + id);
                    goods.notifyAll();
                }
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


public class ProducerConsumerExample {
    public static void main(String[] args) {

        List<String> goods = new ArrayList<>(10);

        for (int i = 0; i < 2; i++)
            new Producer(i, goods).start();

        for (int i = 0; i < 5; i++)
            new Consumer(i, goods).start();
    }
}
