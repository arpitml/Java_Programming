package liveness;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Teacher extends Thread {

    public Lock chopstickA, chopstickB;
    public static int sushiCount = 500;

    public Teacher(String name, Lock chopsticksA, Lock chopsticksB) {
        this.setName(name);
        this.chopstickA = chopsticksA;
        this.chopstickB = chopsticksB;
    }

    @Override
    public void run() {

        while (sushiCount > 0) {
            try {
                chopstickA.lock();
                chopstickB.lock();

                if (sushiCount > 0) {
                    sushiCount--;
                    System.out.println(this.getName() + " took piece..and remaining left " + sushiCount);
                }

                if (sushiCount == 10)
                    System.out.println(10 / 0);

            } finally {
                chopstickB.unlock();
                chopstickA.unlock();
            }
        }
    }


}

public class AbandonedLockExample {
    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();

        new Teacher("Duke", chopstickA, chopstickB).start();
        new Teacher("Merry", chopstickB, chopstickC).start();
        new Teacher("Hansel", chopstickA, chopstickC).start();
    }
}


