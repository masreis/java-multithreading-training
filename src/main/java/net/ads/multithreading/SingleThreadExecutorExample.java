package net.ads.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutorExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int j = 0; j < 5; j++) {
            executor.execute(new Task(j));
        }
        executor.shutdown();
    }
}

class Task implements Runnable {

    private int id;

    Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {

        System.out.println("Task: " + id);

        long duration = (long) (Math.random() * 3);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
