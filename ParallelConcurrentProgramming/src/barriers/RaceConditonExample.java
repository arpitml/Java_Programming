package barriers;

/**
 * Deciding how much balance in my account
 * */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account extends Thread {

    public static int balance;
    private String user;

    private Lock account = new ReentrantLock();

    public Account(String user) {
        this.user = user;
    }

    @Override
    public void run() {
        if (user.contains("Duke")) {
            try {
                account.lock();
                balance += 30;
                System.out.println("Duke after add..current balance : " + balance);
            } finally {
                account.unlock();
            }
        } else if (user.contains("Martin")) {
            try {
                account.lock();
                balance *= 2;
                System.out.println("Martin after multiply..current balance : " + balance);
            } finally {
                account.unlock();
            }
        }
    }
}

public class RaceConditonExample {
    public static void main(String[] args) throws InterruptedException {
        Account[] user = new Account[10];

        for (int i = 0; i < 5; i++)
            user[i] = new Account("Duke");

        for (int i = 5; i < 10; i++)
            user[i] = new Account("Martin");

        for (int i = 0; i < 10; i++)
            user[i].start();

        for (int i = 0; i < 10; i++)
            user[i].join();

        System.out.println("Total Amount after operation : " + Account.balance);
    }
}
