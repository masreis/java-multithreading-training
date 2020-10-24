package net.ads.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadExecutorExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int j = 0; j < 100; j++) {
            executor.execute(new TaskFixed(j));
        }
        
        try {
            executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        
//        executor.shutdown();
    }
}

class TaskFixed implements Runnable {

    private int id;

    TaskFixed(int id) {
        this.id = id;
    }

    @Override
    public void run() {

        System.out.println("Task: " + id + ". " + Thread.currentThread().getName());

        long duration = (long) (Math.random() * 2);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
