package cwowhappy.study.thread.chapter02;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.Callable;

/**
 * @author cwowhappy
 * 2016-12-28 Wednesday
 */
public class SimpleTask implements Callable<String> {
    private long id;

    public SimpleTask(long id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.currentThread().sleep(100);
        return String.format("Task-%08d Timestamp %d", this.id, LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
