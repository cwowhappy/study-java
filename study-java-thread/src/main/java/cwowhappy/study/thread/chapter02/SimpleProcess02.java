package cwowhappy.study.thread.chapter02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author cwowhappy
 * 2016-12-28 Wednesday
 */
public class SimpleProcess02 {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleProcess02.class);

    public void learnSingleThreadPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        List<Future<String>> futureList = new ArrayList<>();
        for(long id = 1; id <= 1000; id++) {
            futureList.add(executorService.submit(new SimpleTask(id)));
        }
        futureList.forEach(stringFuture -> {
            try {
                LOGGER.info(stringFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error("Exception:{}", e.getMessage());
                LOGGER.debug("{}", e);
            }
        });
        executorService.shutdown();
    }

    public static void main(String[] args) {
        SimpleProcess02 simpleProcess = new SimpleProcess02();
        simpleProcess.learnSingleThreadPool();
    }
}
