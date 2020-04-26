package liveness;

import jdk.nashorn.internal.runtime.ECMAException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Lawyer extends Thread {

    public Lock chopstickA, chopstickB;
    public static int sushiCount = 500;

    public Lawyer(String name, Lock chopsticksA, Lock chopsticksB) {
        this.setName(name);
        this.chopstickA = chopsticksA;
        this.chopstickB = chopsticksB;
    }

    @Override
    public void run() {

        while (sushiCount > 0) {
            chopstickA.lock();
            if (!chopstickB.tryLock()) {
                System.out.println(this.getName() + " realease the chopstickA");
                chopstickA.unlock();
            } else {
                try {
                    if (sushiCount > 0) {
                        sushiCount--;
                        System.out.println(this.getName() + " took piece..and remaining left " + sushiCount);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    chopstickB.unlock();
                    chopstickA.unlock();
                }
            }


        }
    }


}

public class LiveLockExample {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();

        new Lawyer("Duke", chopstickA, chopstickB).start();
        new Lawyer("Merry", chopstickB, chopstickC).start();
        new Lawyer("Hansel", chopstickC, chopstickA).start();
    }
}
