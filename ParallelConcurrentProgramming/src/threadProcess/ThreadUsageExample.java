package threadProcess;

import java.lang.management.ManagementFactory;

class ThreadUsage extends Thread {

    @Override
    public void run() {
        while (true) {

        }
    }
}


public class ThreadUsageExample {
    public static void main(String[] args) {

        Runtime rt = Runtime.getRuntime();
        float usedKb = (rt.totalMemory() - rt.freeMemory()) / 1024;
        System.out.println("Process ID: " + ManagementFactory.getRuntimeMXBean().getName());
        System.out.println("Active Thread count: " + Thread.activeCount());
        System.out.println("Used KB: " + usedKb + "\n");

        // Start 6 new threads
        System.out.println("Start 6 CPU waster thread...");
        for (int i = 0; i < 6; i++)
            new ThreadUsage().start();


        System.out.println();
        usedKb = (rt.totalMemory() - rt.freeMemory()) / 1024;
        System.out.println("Process ID: " + ManagementFactory.getRuntimeMXBean().getName());
        System.out.println("Active Thread count: " + Thread.activeCount());
        System.out.println("Used KB: " + usedKb);
    }
}
