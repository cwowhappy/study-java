package cwowhappy.study.thread.chapter01;

import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by cwowhappy on 2017/9/27.
 */
public class Process {
    private static void testSimpleBlockingQueue() {
        final SimpleBlockingQueue<String> simpleBlockingQueue = new SimpleBlockingQueue<>();
        List<Thread> producers = IntStream.range(1, 2)
                .mapToObj(index -> new Thread(new SimpleProducer("Producer" + index, 50L, simpleBlockingQueue)))
                .collect(Collectors.toList());
        List<Thread> customers = IntStream.range(1, 3)
                .mapToObj(index -> new Thread(new SimpleCustomer("Customer" + index, 100L, simpleBlockingQueue)))
                .collect(Collectors.toList());

        producers.forEach(Thread::start);
        customers.forEach(Thread::start);
    }

    public static void main(String[] args) throws InterruptedException {
        //testSimpleBlockingQueue();
        StringTokenizer stringTokenizer = new StringTokenizer("/a/ /c/", "/");
        while (stringTokenizer.hasMoreTokens()) {
            System.out.println("/" + stringTokenizer.nextToken());
        }

    }
}
