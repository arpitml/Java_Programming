package threadProcess;

import static threadProcess.MuffinEat.eating;

class MuffinEat extends Thread{

    public static boolean eating = true;
    public int muffinCount = 0;

    public MuffinEat(String name) {
       this.setName(name);
    }

    @Override
    public void run() {
        while (eating) {
            muffinCount++;
            System.out.println(this.getName() + " eat muffin count: " + muffinCount);
        }
    }
}

public class ThreadSchedulingExample{
    public static void main(String[] args) throws InterruptedException {
        MuffinEat tom = new MuffinEat("Tom");
        MuffinEat jerry = new MuffinEat("Jerry");

        tom.start();
        jerry.start();

        Thread.sleep(1000);

        eating = false;

        tom.join();
        jerry.join();

        System.out.println("Total Tom eating muffin " + tom.muffinCount);
        System.out.println("Total Jerry eating muffin " + jerry.muffinCount);
    }
}
