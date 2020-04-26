package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class NotepadList extends Thread{

    public static int notepadItems = 0;
    public static int itemAdd = 0;

    public static Lock pencil = new ReentrantLock();

    public NotepadList(String name){
        this.setName(name);
    }

    @Override
    public void run(){
        while(notepadItems < 20){
            if(itemAdd > 0 && pencil.tryLock()){
                notepadItems += itemAdd;
                System.out.println("Item add by " + this.getName() + " " + itemAdd);
                itemAdd = 0;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    pencil.unlock();
                }
            }else{
                try {
                 Thread.sleep(100);
                System.out.println(this.getName() + " thinking");
                itemAdd++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class TryLockExample {
    public static void main(String[] args) throws InterruptedException{
        NotepadList tom = new NotepadList("Tom");
        NotepadList jerry = new NotepadList("Jerry");

        tom.start();
        jerry.start();

        tom.join();
        jerry.join();


    }
}
