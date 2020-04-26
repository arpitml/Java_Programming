package mutualExclusion;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MuffinBake extends Thread {

    public static int muffinCount = 0;
    public static Lock ovenLock = new ReentrantLock();

    @Override
    public void run() {
        for (int i = 0; i < 10_000_000; i++) {
            ovenLock.lock();
            muffinCount++;
            ovenLock.unlock();
        }

    }
}


public class MutexExample {
    public static void main(String[] args) throws InterruptedException{
        MuffinBake tom = new MuffinBake();
        tom.setName("Tom");
        MuffinBake jerry = new MuffinBake();
        jerry.setName("Jerry");
        tom.start();
        jerry.start();

        tom.join();
        jerry.join();

        System.out.println("Total Tom & Jerry Muffin eat count: " + MuffinBake.muffinCount);


    }
}
