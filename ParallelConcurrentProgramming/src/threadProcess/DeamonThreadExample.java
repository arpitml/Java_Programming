package threadProcess;

class HouseCleaning implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("Tom cleaning house..");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }
}

public class DeamonThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread tom = new Thread(new HouseCleaning());
        /**
         * If dar
         */
        tom.setDaemon(true);
        tom.start();

        System.out.println("Jerry working in the house...");
        Thread.sleep(500);

        System.out.println("Jerry working in the house...");
        Thread.sleep(500);

        System.out.println("Jerry working in the house...");
        Thread.sleep(500);

        System.out.println("Jerry completed our work...");


    }
}
