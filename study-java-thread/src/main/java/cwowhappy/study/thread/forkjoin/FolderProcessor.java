package cwowhappy.study.thread.forkjoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Pattern;

/**
 * Created by cwowhappy on 2017/5/11.
 */
public class FolderProcessor extends RecursiveTask<List<String>> {
    private static final Logger LOG = LoggerFactory.getLogger(FolderProcessor.class);
    private String path;
    private String fileExtension;

    public FolderProcessor(String path, String fileExtension) {
        this.path = path;
        this.fileExtension = fileExtension;
    }

    @Override
    protected List<String> compute() {
        List<String> filenameList = new ArrayList<>();
        List<FolderProcessor> folderProcessorList = new ArrayList<>();

        File folder = new File(path);
        if(folder.isDirectory()) {
            File[] files = folder.listFiles();
            if(null != files) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        FolderProcessor folderProcessor = new FolderProcessor(file.getAbsolutePath(), fileExtension);
                        folderProcessor.fork();
                        folderProcessorList.add(folderProcessor);
                    } else {
                        Pattern filenamePattern = Pattern.compile(String.format(".+\\.%s$", "java"));
                        if (filenamePattern.matcher(file.getAbsolutePath()).matches()) {
                            filenameList.add(file.getAbsolutePath());
                        }
                    }
                }
            }
        }
        if(50 < folderProcessorList.size()) {
            LOG.info("{} : {} tasks run", path, folderProcessorList.size());
        }
        folderProcessorList.forEach(folderProcessor -> filenameList.addAll(folderProcessor.join()));

        return filenameList;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FolderProcessor folderProcessor = new FolderProcessor("/Users/didi/GitRepo", "java");
        forkJoinPool.execute(folderProcessor);
        /*
        do {
            LOG.info("***************************************");
            LOG.info("Main parallelism: {}", forkJoinPool.getParallelism());
            LOG.info("Main: Active Threads: {}", forkJoinPool.getActiveThreadCount());
            LOG.info("Main: Task Count: {}", forkJoinPool.getQueuedTaskCount());
            LOG.info("Main: Steal Count: {}", forkJoinPool.getStealCount());
            LOG.info("***************************************");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while(!folderProcessor.isDone());
        */
        while(!folderProcessor.isDone());
        forkJoinPool.shutdown();
        LOG.info("文件名后缀为java的文件个数: {}", folderProcessor.join().size());
    }
}
