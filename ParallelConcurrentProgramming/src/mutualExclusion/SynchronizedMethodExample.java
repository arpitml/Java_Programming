package mutualExclusion;

class BuyVegetable extends Thread {

    public static int vegetableCount = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10_000_000; i++)
            incrementCount();
    }

    public static synchronized void incrementCount() {
        vegetableCount++;
    }
}

public class SynchronizedMethodExample {
    public static void main(String[] args) throws InterruptedException {
        BuyVegetable micky = new BuyVegetable();
        BuyVegetable minni = new BuyVegetable();

        micky.start();
        minni.start();

        micky.join();
        minni.join();

        System.out.println("Total vegetable count: " + BuyVegetable.vegetableCount);
    }
}
