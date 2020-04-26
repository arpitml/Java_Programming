package mutualExclusion;

import java.util.concurrent.atomic.AtomicInteger;

class PeanutsEat extends Thread {

    public static AtomicInteger peanutCount = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 10_000_000; i++) {
            peanutCount.incrementAndGet();
        }
    }
}

public class AtomicVariableExample {
    public static void main(String[] args) throws InterruptedException{
        PeanutsEat tom = new PeanutsEat();
        PeanutsEat jerry = new PeanutsEat();
        tom.start();
        jerry.start();
        tom.join();
        jerry.join();

        System.out.println("Total Tom & Jerry Peanuts eat count: " + PeanutsEat.peanutCount);


    }
}

