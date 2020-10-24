package net.ads.multithreading;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadExecutorExample {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(new StockMarket(1), 1000, 3000, TimeUnit.MILLISECONDS);
        

    }
}

class StockMarket implements Runnable {

    private int id;

    StockMarket(int id) {
        this.id = id;
    }

    @Override
    public void run() {

        System.out.println("Task: " + id + ". " + Thread.currentThread().getName());

        long duration = (long) (Math.random() * 5);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
