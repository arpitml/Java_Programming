package liveness;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Judge extends Thread {

    public Lock chopstickA, chopstickB;
    public static int sushiCount = 500;
    public static int sushiEaten;


    public Judge(String name, Lock chopsticksA, Lock chopsticksB) {
        this.setName(name);
        this.chopstickA = chopsticksA;
        this.chopstickB = chopsticksB;
    }

    @Override
    public void run() {

        sushiEaten = 0;
        while (sushiCount > 0) {
            chopstickA.lock();
            chopstickB.lock();

            if (sushiCount > 0) {
                sushiCount--;
                System.out.println(this.getName() + " took piece..and remaining left " + sushiCount);
            }


            chopstickB.unlock();
            chopstickA.unlock();
        }
        System.out.println(this.getName() + " sushi eaten " + sushiEaten);
    }


}

public class StarvationExample {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();

        for(int i=0;i<10_000;i++) {
            new Judge("Duke", chopstickA, chopstickB).start();
            new Judge("Merry", chopstickB, chopstickC).start();
            new Judge("Hansel", chopstickA, chopstickC).start();
        }
    }
}
