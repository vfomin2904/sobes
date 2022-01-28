package hw3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private int count;
    private final Lock lock;

    public Counter(int count) {
        this.count = count;
        lock = new ReentrantLock();
    }

    public int getCount() {
        return count;
    }

    public void increment () {
        lock.lock();
        count++;
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter(0);
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 200000; i++) {
                counter.increment();
            }
            countDownLatch.countDown();
        }).start();

        countDownLatch.await();

        System.out.println(counter.getCount());
    }
}
