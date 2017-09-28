package cwowhappy.study.test;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by cwowhappy on 2017/8/1.
 */
public class TestLockSynchronized {
    private static final Object object = new Object();
    private static final Lock lock = new ReentrantLock();

    public static void test01() {
        List<Thread> threads = IntStream.range(1, 11).boxed().map(index -> (Runnable) () -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "获得了sync锁");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).map(Thread::new).collect(Collectors.toList());

        synchronized (object) {
            threads.forEach(Thread::start);
        }

        threads.forEach((thread) -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void test02() {
        List<Thread> threads = IntStream.range(1, 11).boxed().map(index -> (Runnable) () -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得了sync锁");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }).map(Thread::new).collect(Collectors.toList());

        lock.lock();
        threads.forEach(Thread::start);
        lock.unlock();

        threads.forEach((thread) -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        test01();
        test02();
    }
}
