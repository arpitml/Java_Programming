package asynchronousTask;

import java.util.concurrent.*;

class CountingVegetable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception{
        System.out.println("Counting Vegetable in the basket....");
        Thread.sleep(1000);
        return 43;
    }
}

public class FutureExample {
    public static void main(String[] args) throws InterruptedException,ExecutionException{
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future result = executorService.submit(new CountingVegetable());
        System.out.println(result.get() + " vegetables in the basket");
        executorService.shutdown();
    }
}
