package cwowhappy.study.thread.chapter01;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * @author cwowhappy
 * 2016-12-27 Wednesday
 */
public class SimpleCustomer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCustomer.class);
    private BlockingQueue<SimpleMessage> messageQueue;

    public SimpleCustomer(BlockingQueue<SimpleMessage> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        ObjectMapper objectMapper = new ObjectMapper();
        while(true) {
            try {
                SimpleMessage simpleMessage = messageQueue.take();
                LOGGER.info("Message[consume-{}]:{}", Thread.currentThread().getId(), objectMapper.writeValueAsString(simpleMessage));
                Thread.sleep(1000);
            } catch (InterruptedException | JsonProcessingException e) {
                LOGGER.error("Exception:{}", e.getMessage());
                LOGGER.debug("{}", e);
            }
        }
    }
}
