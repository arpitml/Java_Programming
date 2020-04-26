package barriers;

/**
 * Deciding how many chipsBags of chips to buy for the party
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class PartyChipsBags extends Thread {

    public static int chipsBags;
    private String user;

    private static CyclicBarrier barrier = new CyclicBarrier(10);
    private static Lock pencil = new ReentrantLock();

    public PartyChipsBags(String user) {
        this.user = user;
    }

    @Override
    public void run() {
        if (user.contains("Duke")) {
            try {
                pencil.lock();
                chipsBags += 3;
                System.out.println("Duke after add..current chipsBags : " + chipsBags);
            } finally {
                pencil.unlock();
            }
            try {
                barrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        } else if (user.contains("Martin")) {
            try {
                barrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
            try {
                pencil.lock();
                chipsBags *= 2;
                System.out.println("Martin after multiply..current chipsBags : " + chipsBags);
            } finally {
                pencil.unlock();
            }
        }
    }
}


public class CyclicBarrierExample {
    public static void main(String[] args) throws InterruptedException {
        PartyChipsBags[] user = new PartyChipsBags[10];

        for (int i = 0; i < 5; i++)
            user[i] = new PartyChipsBags("Duke");

        for (int i = 5; i < 10; i++)
            user[i] = new PartyChipsBags("Martin");

        for (int i = 0; i < 10; i++)
            user[i].start();

        for (int i = 0; i < 10; i++)
            user[i].join();

        System.out.println("Total Bags after operation : " + PartyChipsBags.chipsBags);
    }
}
