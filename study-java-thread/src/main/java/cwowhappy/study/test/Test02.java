package cwowhappy.study.test;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by cwowhappy on 2017/8/28.
 */
public class Test02 {
    public static void main(String[] args) throws FileNotFoundException {
        Stream<String> stringStream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<String>() {
            private int count = 100;
            @Override
            public boolean hasNext() {
                return count > 0;
            }

            @Override
            public String next() {
                return String.format("[%s]number-%02d", Thread.currentThread().getName(), --count);
            }
        }, Spliterator.ORDERED | Spliterator.NONNULL), true);
        stringStream.parallel().forEach(System.out::println);
    }
}
