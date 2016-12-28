package cwowhappy.study.thread.chapter01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author cwowhappy
 * 2016-12-28 Wednesday
 */
public class SimpleProcess {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleProcess.class);

    public void execute() throws InterruptedException {
        //BlockingQueue<SimpleMessage> messageQueue = new ArrayBlockingQueue<>(100);
        BlockingQueue<SimpleMessage> messageQueue = new SimpleBlockingQueue<>(50);
        Thread producer = new Thread(new SimpleProducer(messageQueue));
        producer.start();
        List<Thread> customerList = new ArrayList<>();
        for(int i = 1; i <= 5; i++) {
            Thread customer = new Thread(new SimpleCustomer(messageQueue));
            customerList.add(customer);
            customer.start();
        }

        producer.join();
        customerList.forEach(customer -> {
            try {
                customer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleProcess simpleProcess = new SimpleProcess();
        simpleProcess.execute();
    }
}
