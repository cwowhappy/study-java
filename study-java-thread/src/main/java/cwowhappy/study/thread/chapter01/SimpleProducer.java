package cwowhappy.study.thread.chapter01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cwowhappy on 2017/9/28.
 */
public class SimpleProducer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleProducer.class);
    private String name;
    private Long interval;
    private SimpleBlockingQueue<String> simpleBlockingQueue;

    public SimpleProducer(String name, Long interval, SimpleBlockingQueue<String> simpleBlockingQueue) {
        this.name = name;
        this.interval = interval;
        this.simpleBlockingQueue = simpleBlockingQueue;
    }

    @Override
    public void run() {
        int indexElement = 1;
        while (true) {
            String element = String.format("%s-element-%05d", name, indexElement ++);
            try {
                simpleBlockingQueue.offer(element);
                LOGGER.info("[{}]->Producer[{}] produce element[{}]", Thread.currentThread().getName(), name, element);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
