package cwowhappy.study.thread.ch03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 学习和测试ThreadLocal类的使用
 * @author cwowhappy
 * 2016-12-30 Friday
 */
public class ThreadLocalTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadLocalTest.class);

    public static void main(String[] args) {
        final ThreadLocal<String> message = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return "Hello world";
            }
        };

        List<Thread> threads = IntStream.range(1, 11).boxed().map(index -> (Runnable) () -> {
            LOGGER.info("Thread-{} [message]的初始值:{}", Thread.currentThread().getName(), message.get());
            message.set(Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("Thread-{} [message]的值:{}", Thread.currentThread().getName(), message.get());
        }).map(Thread::new).collect(Collectors.toList());
        threads.forEach(Thread::start);
        threads.forEach((thread) -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
