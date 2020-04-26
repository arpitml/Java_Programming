package asynchronousTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ChoppingVegetables extends Thread{

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " chopping vegetables....");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<100;i++)
            executorService.submit(new ChoppingVegetables());
        executorService.shutdown();
    }
}
