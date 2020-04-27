package asynchronousTask;

/**
 * Sum of natural numbers using ForkJoinPool
 */

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class RecursiveSum extends RecursiveTask<Long>{

    private long low, high;

    public RecursiveSum(long low, long high){
        this.low = low;
        this.high = high;
    }

    @Override
    protected Long compute() {

        if(high - low <=100_000){
            long res = 0L;
            for(long i=low ; i <= high ; i++){
                res += i;
            }
            return res;
        }else{
            long mid = (low + high) / 2;
            RecursiveSum left = new RecursiveSum(low,mid);
            RecursiveSum right = new RecursiveSum(mid+1,high);
            left.fork();
            return right.compute() + left.join();
        }
    }
}

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Long result = pool.invoke(new RecursiveSum(0,1_000_000_000));
        pool.shutdown();
        System.out.println("result : " + result);
    }
}
