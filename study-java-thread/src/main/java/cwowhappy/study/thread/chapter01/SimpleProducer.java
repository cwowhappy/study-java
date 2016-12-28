package cwowhappy.study.thread.chapter01;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * @author cwowhappy
 * 2016-12-28 Wednesday
 */
public class SimpleProducer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCustomer.class);
    private BlockingQueue<SimpleMessage> messageQueue;

    public SimpleProducer(BlockingQueue<SimpleMessage> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        long messageId = 1;
        ObjectMapper objectMapper = new ObjectMapper();
        while(true) {
            try {
                SimpleMessage simpleMessage = SimpleMessage.build(String.format("message-%08d", messageId ++));
                messageQueue.put(simpleMessage);
                LOGGER.info("Queue[size={}]-Message[produce]:{}", messageQueue.size(), objectMapper.writeValueAsString(simpleMessage));
                Thread.sleep(100);
            } catch (InterruptedException | JsonProcessingException e) {
                LOGGER.error("Exception:{}", e.getMessage());
                LOGGER.debug("{}", e);
            }
        }
    }
}
