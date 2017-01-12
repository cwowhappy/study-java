package cwowhappy.study.thread.chapter02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cwowhappy
 * 2017-01-11 Wednesday
 */
public class TreadLocalTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TreadLocalTest.class);

    public static void main(String[] args) {
        Element element = new Element(1);
        ThreadLocal<Element> elementThreadLocal = new ThreadLocal<Element>() {
            @Override
            protected Element initialValue() {
                return element;
            }
        };
        List<Thread> threadList = new ArrayList<>();
        threadList.add(new Thread(new Task(elementThreadLocal)));
        threadList.add(new Thread(new Task(elementThreadLocal)));
        threadList.add(new Thread(new Task(elementThreadLocal)));
        threadList.add(new Thread(new Task(elementThreadLocal)));

        threadList.forEach(thread -> {
            thread.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static class Task implements Runnable {
        private ThreadLocal<Element> elementThreadLocal;

        private Task(ThreadLocal<Element> elementThreadLocal) {
            this.elementThreadLocal = elementThreadLocal;
        }

        @Override
        public void run() {
            Element element = elementThreadLocal.get();
            LOGGER.info("{}:{}", Thread.currentThread().getId(), element.getValue());
            element.setValue(element.getValue() + 1);
        }
    }

    private static class Element {
        private int value;

        public Element(int value) {
            setValue(value);
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
