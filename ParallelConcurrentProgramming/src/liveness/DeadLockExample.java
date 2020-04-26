package liveness;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher extends Thread {

    public Lock chopstickA, chopstickB;
    public static int sushiCount = 500;

    public Philosopher(String name, Lock chopsticksA, Lock chopsticksB) {
        this.setName(name);
        this.chopstickA = chopsticksA;
        this.chopstickB = chopsticksB;
    }

    @Override
    public void run() {

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
    }


}

public class DeadLockExample {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();

        new Philosopher("Duke", chopstickA, chopstickB).start();
        new Philosopher("Merry", chopstickB, chopstickC).start();
        new Philosopher("Hansel", chopstickC, chopstickA).start();
    }
}

