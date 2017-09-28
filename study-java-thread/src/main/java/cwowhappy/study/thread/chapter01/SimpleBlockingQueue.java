package cwowhappy.study.thread.chapter01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cwowhappy on 2017/9/28.
 */
public class SimpleBlockingQueue<E> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleBlockingQueue.class);
    private static final int QUEUE_LENGTH = 16;
    private Object[] elements;
    private int count;
    private int indexHead;
    private int indexTail;

    private final Object guard = new Object();
    private final Object notFull = new Object();
    private final Object notEmpty = new Object();

    public SimpleBlockingQueue() {
        elements = new Object[QUEUE_LENGTH];
        count = indexHead = indexTail = 0;
    }

    public E take() throws InterruptedException {
        E value;
        synchronized (notEmpty) {
            LOGGER.debug("[{}]获得notEmpty", Thread.currentThread().getName());
            while (0 == count) {
                LOGGER.debug("[{}]队列为空,notEmpty等待", Thread.currentThread().getName());
                notEmpty.wait();
            }

            LOGGER.debug("[{}]队列有元素", Thread.currentThread().getName());
            synchronized (guard) {
                LOGGER.debug("[{}]获得guard,准备获取元素", Thread.currentThread().getName());
                value = (E) elements[indexHead];
                count --;
                indexHead ++;
                if(indexHead == QUEUE_LENGTH) {
                    indexHead = 0;
                }
                LOGGER.debug("[{}]获得guard,完成获取元素", Thread.currentThread().getName());
            }

        }
        synchronized (notFull) {
            LOGGER.debug("[{}]获得notEmpty,notEmpty通知", Thread.currentThread().getName());
            notFull.notify();
        }
        return value;
    }

    public void offer(E element) throws InterruptedException {
        synchronized (notFull) {
            LOGGER.debug("[{}]获得notFull", Thread.currentThread().getName());
            while (count == QUEUE_LENGTH) {
                LOGGER.debug("[{}]队列为满,notFull等待", Thread.currentThread().getName());
                notFull.wait();
            }
            LOGGER.debug("[{}]队列有空", Thread.currentThread().getName());
            synchronized (guard) {
                LOGGER.debug("[{}]获得guard,准备插入元素", Thread.currentThread().getName());
                elements[indexTail] = element;
                count ++;
                indexTail ++;
                if (QUEUE_LENGTH == indexTail) {
                    indexTail = 0;
                }
                LOGGER.debug("[{}]获得guard,完成插入元素", Thread.currentThread().getName());
            }
        }
        synchronized (notEmpty) {
            LOGGER.debug("[{}]获得notEmpty,notEmpty通知", Thread.currentThread().getName());
            notEmpty.notify();
        }
    }
}
