package net.ads.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// https://www.baeldung.com/java-deadlock-livelock
// http://tutorials.jenkov.com/java-concurrency/race-conditions-and-critical-sections.html
// https://www.netjstech.com/2015/06/race-condition-in-java-multi-threading.html
    
public class DeadLockExample {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        DeadLockExample deadlock = new DeadLockExample();
        new Thread(deadlock::operation1, "T1").start();
        new Thread(deadlock::operation2, "T2").start();
    }

    public void operation1() {
        lock1.lock();
        System.out.println("lock1 acquired, waiting to acquire lock2.");
        sleep(50);

        lock2.lock();
        System.out.println("lock2 acquired");

        System.out.println("executing first operation.");

        lock2.unlock();
        lock1.unlock();
    }

    public void operation2() {
        lock2.lock();
        System.out.println("lock2 acquired, waiting to acquire lock1.");
        sleep(50);

//        lock1.lock();
        System.out.println("lock1 acquired");

        System.out.println("executing second operation.");

        lock1.unlock();
        lock2.unlock();
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // helper methods
}
