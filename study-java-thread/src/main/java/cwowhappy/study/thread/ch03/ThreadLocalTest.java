package cwowhappy.study.thread.ch03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 学习和测试ThreadLocal类的使用
 * @author cwowhappy
 * 2016-12-30 Friday
 */
public class ThreadLocalTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadLocalTest.class);

    public static void main(String[] args) {
        ThreadLocal<String> message = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return "Hello world";
            }
        };
    }
}
