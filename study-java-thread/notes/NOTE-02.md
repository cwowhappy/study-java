## 第二章 学习线程池-ExecutorService
### 1. `Thread`与`ThreadGroup`
`java.lang.Thread`类与`java.lang.ThreadGroup`类构建成一个线程树。(好处待续)
### 2. 线程池相关的类与接口
1. `java.util.concurrent.Executors`类是一个Factory类,提供创建ExecutorService、ScheduledExecutorService、ThreadFactory和Callable类实例的方法;
2. `java.util.concurrent.ExecutorService`接口
3. `java.util.concurrent.ScheduledExecutorService`接口
4. `java.util.concurrent.ThreadPoolExecutor`类是`ExecutorService`接口的一个实现类
5. `java.util.concurrent.ScheduledThreadPoolExecutor`类是`ScheduledExecutorService`接口的一个实现类
