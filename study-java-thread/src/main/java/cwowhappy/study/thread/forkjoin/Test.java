package cwowhappy.study.thread.forkjoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);
        CompletableFuture.allOf(IntStream.range(0, 40)
                .mapToObj(taskId -> (Supplier<String>) () -> {
                    LOGGER.info(Thread.currentThread().getId() + Thread.currentThread().getName() + " task-1-" + taskId + " start");
                    try {
                        Thread.sleep(200L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LOGGER.info(Thread.currentThread().getId() + Thread.currentThread().getName() + " task-1-" + taskId + " end");
                    return "task" + taskId;
                })
                .map(CompletableFuture::supplyAsync)
                .collect(Collectors.toList()).toArray(new CompletableFuture[10]))
                .whenComplete((a, b) -> LOGGER.info("complete"));
        CompletableFuture.allOf(IntStream.range(0, 40)
                .mapToObj(taskId -> (Supplier<String>) () -> {
                    LOGGER.info(Thread.currentThread().getId() + Thread.currentThread().getName() + " task-2-" + taskId + " start");
                    try {
                        Thread.sleep(200L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LOGGER.info(Thread.currentThread().getId() + Thread.currentThread().getName() + " task-2-" + taskId + " end");
                    return "task" + taskId;
                })
                .map(CompletableFuture::supplyAsync)
                .collect(Collectors.toList()).toArray(new CompletableFuture[10]))
                .whenComplete((a, b) -> LOGGER.info("complete"));

        Thread.sleep(10000L);

        while (0 < forkJoinPool.getActiveThreadCount()) {
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("线程池中线程的个数:" + forkJoinPool.getActiveThreadCount());
        }
        forkJoinPool.shutdown();
    }
}
