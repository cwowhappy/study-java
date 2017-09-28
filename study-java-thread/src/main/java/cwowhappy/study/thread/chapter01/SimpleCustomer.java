package cwowhappy.study.thread.chapter01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cwowhappy on 2017/9/28.
 */
public class SimpleCustomer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleProducer.class);
    private String name;
    private Long interval;
    private SimpleBlockingQueue<String> simpleBlockingQueue;

    public SimpleCustomer(String name, Long interval, SimpleBlockingQueue<String> simpleBlockingQueue) {
        this.name = name;
        this.interval = interval;
        this.simpleBlockingQueue = simpleBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String value = simpleBlockingQueue.take();
                LOGGER.info("[{}]->Customer[{}] custom element[{}]", Thread.currentThread().getName(), name, value);
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
