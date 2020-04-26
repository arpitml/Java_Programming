package mutualExclusion;

class MuffinCount extends Thread {

    public static int muffinCount = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10_000_000; i++)
            muffinCount++;
    }
}

public class DataRaceExample {
    public static void main(String[] args) throws InterruptedException{
        MuffinCount tom = new MuffinCount();
        tom.setName("Tom");
        MuffinCount jerry = new MuffinCount();
        jerry.setName("Jerry");
        tom.start();
        jerry.start();

        tom.join();
        jerry.join();

        System.out.println("Total Tom & Jerry Muffin eat count: " + MuffinCount.muffinCount);
    }
}
