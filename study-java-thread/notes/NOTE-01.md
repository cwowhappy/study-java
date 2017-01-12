## 第一章 锁和信号量
### 1. `ReentrantLock`与`Condition`
1. `java.util.concurrent.locks.ReentrantLock`类
`ReentrantLock`类是对`Sync`类的封装,`Sync`类继承自`AbstractQueuedSynchronizer`类,`AbstractQueuedSynchronizer`类继承自`AbstranctOwnableSynchronizer`类。
2. `java.util.concurrent.locks.Condition`接口