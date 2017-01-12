package cwowhappy.study.thread.chapter01;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 简单的阻塞队列
 * @author cwowhappy
 * 2016-12-28 Wednesday
 */
public class SimpleBlockingQueue<E> implements BlockingQueue<E> {
    private static final int DEFAULT_CAPACITY = 16;
    private Object[] items;
    private int capacity;
    private int count;
    private int putIndex;
    private int takeIndex;

    private ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public SimpleBlockingQueue() {
        this(DEFAULT_CAPACITY);
    }

    public SimpleBlockingQueue(int capacity) {
        if(0 >= capacity) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        this.count = this.putIndex = this.takeIndex = 0;
        this.items = new Object[this.capacity];

        lock = new ReentrantLock(true);
        this.notEmpty = this.lock.newCondition();
        this.notFull = this.lock.newCondition();
    }

    private void enqueue(E e) {
        final Object[] items = this.items;
        items[putIndex] = e;
        if(++putIndex == this.items.length) {
            putIndex = 0;
        }
        this.count++;
        notEmpty.signal();
    }

    private E dequeue() {
        E e;
        final Object[] items = this.items;
        e = (E) items[takeIndex];
        items[takeIndex] = null;
        if(++takeIndex == this.items.length) {
            takeIndex = 0;
        }
        this.count--;
        notFull.signal();
        return e;
    }

    private void checkNotNull(E e) {
        if(null == e) {
            throw new NullPointerException();
        }
    }

    @Override
    public void put(E e) throws InterruptedException {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while(count == this.items.length) {
                notFull.await();
            }
            enqueue(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E take() throws InterruptedException {
        E e;
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while(count == 0) {
                notEmpty.await();
            }
            e = dequeue();
        } finally {
            lock.unlock();
        }
        return e;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        return 0;
    }
}
