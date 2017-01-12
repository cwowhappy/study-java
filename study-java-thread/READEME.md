# 学习笔记
## 第一章 简单的生产者消费者
实现了简单的Blocking队列,以完成生产者和消费者例子。使用ReentrantLock、Condition类实现多线程并发访问队列中的元素。
```Java
public class SimpleBlockingQueue {
    private Object[] items;
    private int capacity;
    private int count;
    private int putIndex;
    private int takeIndex;

    private ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;

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
}
```
## 第二章 学习线程池-ExecutorService