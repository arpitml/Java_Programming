package synchronization;

/*
    Multiple user to connecting mobile in the USB charger which consists 3 ports.
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

class ChargingPorts extends Thread {

    private static Semaphore usbPort = new Semaphore(3);
/*
    If I am using permits 1...
    it works like a mutex
    private static Semaphore usbPort = new Semaphore(1);
*/
    public ChargingPorts(String name) {
        this.setName(name);
    }

    @Override
    public void run() {

        try {
            usbPort.acquire();
            System.out.println(this.getName() + " charging the mobile...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            System.out.println(this.getName() + " complete charging....");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            usbPort.release();
        }
    }
}

public class SemaphoreExample {
    public static void main(String[] args) {

        for (int i = 0; i < 8; i++) {
            new ChargingPorts("user " + i).start();
        }
    }
}
