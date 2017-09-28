package cwowhappy.study.thread.chapter01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cwowhappy
 * 2016-12-28 Wednesday
 */
public class SimpleTask implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTask.class);

    private Object guard;

    public SimpleTask(Object guard) {
        this.guard = guard;
    }

    @Override
    public void run() {
        LOGGER.info("Thread[{}] begin running", Thread.currentThread().getName());
        try {
            synchronized (guard) {
                guard.wait();
            }
        } catch (InterruptedException e) {
            LOGGER.info("The waiting of Thread[{}] is interrupted", Thread.currentThread().getName());
        }
        LOGGER.info("Thread[{}] finish", Thread.currentThread().getName());
    }
}
