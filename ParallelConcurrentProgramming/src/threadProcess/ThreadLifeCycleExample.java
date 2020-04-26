package threadProcess;

class JerryBake implements Runnable{
    @Override
    public void run() {
        System.out.println("Jerry Bake Muffin.....");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Jerry Stop Bake Muffin");

    }
}

public class ThreadLifeCycleExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Tom request jerry to bake muffin...");
        Thread jerry = new Thread(new JerryBake());
        System.out.println("Jerry state: " + jerry.getState());

        System.out.println("Tom tell jerry to start work...");
        jerry.start();
        System.out.println("Jerry state: " + jerry.getState());

        System.out.println("Tom continue work work...");
        Thread.sleep(500);
        System.out.println("Jerry state: " + jerry.getState());

        System.out.println("Tom wait jerry to finish and join...");
        jerry.join();
        System.out.println("Jerry state: " + jerry.getState());

        System.out.println("Tom and Jerry work done...");

    }
}
