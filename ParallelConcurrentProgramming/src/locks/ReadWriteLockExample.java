package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class CalenderInfo extends Thread {

    public static final String[] WEEKDAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    public static int today = 0;
    public static ReentrantReadWriteLock marker = new ReentrantReadWriteLock();
    public static Lock readMarker = marker.readLock();
    public static Lock writeMarker = marker.writeLock();


    public CalenderInfo(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        while (today < WEEKDAYS.length - 1)
            if (this.getName().contains("Writer")) {
                try {
                    writeMarker.lock();
                    today = (today + 1 ) % 7;
                    System.out.println(this.getName() + " updated date to " + WEEKDAYS[today]);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    writeMarker.unlock();
                }
            } else {
                try {
                    readMarker.lock();
                    System.out.println(this.getName() + " sees that today is " + WEEKDAYS[today] + "; total readers: " + marker.getReadLockCount());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    readMarker.unlock();
                }
            }
    }
}

public class ReadWriteLockExample {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++)
            new CalenderInfo("Reader " + i).start();

        for (int i = 0; i < 2; i++)
            new CalenderInfo("Writer " + i).start();
    }
}
