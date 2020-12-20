package cwowhappy.study.java.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class StudyCompletableFuture {
    private static final Logger LOG = LoggerFactory.getLogger(StudyCompletableFuture.class);

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            LOG.info("1");
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOG.info("1");
            return 1;
        }).thenApply((future1) -> CompletableFuture.supplyAsync(() -> {
            LOG.info("2");
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOG.info("2");
            return future1 + 1;
        })).thenAcceptAsync((result) -> {
            try {
                LOG.info("a={}", result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).join();
    }

}
