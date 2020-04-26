package synchronization;

/*
    5 people hungry and get the soup from the cooker.
    Each people get the soup in a order. 
 */


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SoupCooker extends Thread {

    private int personId;
    private static int serving = 11;
    
    private static Lock cookerLid = new ReentrantLock();
    private static Condition soupTaken = cookerLid.newCondition();
    
    public SoupCooker(int personId){
        this.personId = personId;
    }
    
    @Override
    public void run() {
        while(serving > 0){
            cookerLid.lock();
            try{
                while(personId != (serving % 5) && serving > 0){
                    System.out.println("Person " + personId + " get back the lid and wait for our turn");
                    soupTaken.await();
                }
                if(serving > 0){
                    serving--;
                    System.out.println("Person " + personId + " get the soup and remaining soup is " + serving);
                    soupTaken.signalAll();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                cookerLid.unlock();
            }
        }
    }
}

public class ConditionVariableExample {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SoupCooker(i).start();
        }
    }
}
