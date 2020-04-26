package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class HouseGrosary extends Thread {

    public static int tomato = 0;
    public static int potato = 0;

    public static Lock listMarker = new ReentrantLock();

    public void addTomato() {
        listMarker.lock();
        tomato++;
        addPotato();
        listMarker.unlock();
    }

    public void addPotato() {
        listMarker.lock();
        potato++;
        listMarker.unlock();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            addPotato();
            addTomato();
        }
    }
}

public class ReentrantLockExample {
    public static void main(String[] args) throws InterruptedException {
        HouseGrosary tom = new HouseGrosary();
        HouseGrosary jerry = new HouseGrosary();

        tom.start();
        jerry.start();

        tom.join();
        jerry.join();

        System.out.println("Total Tomato count: " + HouseGrosary.tomato);
        System.out.println("Total Potato count: " + HouseGrosary.potato);
    }
}
