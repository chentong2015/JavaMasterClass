package locks.ReentrantLock;

// 谈谈对Reentrant的理解:
// 如果是单个线程，或者线程交替执行，则和队列无关，直接在JDK级别解决同步问题(和OS没有关系，性能非常快)
// TODO: AQS(AbstractQueuedSynchronizer)队列设计思想：
//      自旋(2次) + park/unpark(UNSAFE操作) + CAS(通过设置状态拿锁)
//      设计是让AQS队列中的第一个Node结点的线程是持有锁的线程，实际上持有锁的线程不参与排队，因此头结点是"虚拟结点"
// 1. 线程如何拿到锁 ?
// 2. 没有拿到锁的线程如何入队列 ?
// 3. 持有锁的线程释放锁 + 排队线程的唤醒 ?
public class ReentrantLockSourceCode {

    // class UnFairSync extends Sync
    final boolean initialTryLockUnFair() {
        Thread current = Thread.currentThread();
        // TODO: 不需要判断state状态，直接CAS ==> 非公平锁的实现
        // if (compareAndSetState(0, 1)) { // first attempt is unguarded
        //     setExclusiveOwnerThread(current);
        //     return true;
        // } else if (getExclusiveOwnerThread() == current) {
        //     int c = getState() + 1;
        //     if (c < 0) // overflow
        //         throw new Error("Maximum lock count exceeded");
        //     setState(c);
        //     return true;
        // } else
        //     return false;
        return false;
    }

    // class FairSync extends Sync
    final boolean initialTryLockFair() {
        Thread current = Thread.currentThread();
        // int c = getState(); 这个取值是原子操作
        // if (c == 0) { 判断int state;是否为自由状态
        //     TODO: 判断排队的线程队列，优先中队列中取线程 ==> 公平锁的实现，解决线程饥饿问题
        //           判断自己需不需要排队，再进行CAS
        //     if (!hasQueuedThreads() && compareAndSetState(0, 1)) {
        //         // 把当前线程设置到AbstractQueuedSynchronizer属性exclusiveOwnerThread;值
        //         setExclusiveOwnerThread(current); // current 当前持有锁的线程
        //         return true;
        //     }
        // TODO: 拿到当前持有锁的线程 ==> 可重入锁的设计
        // } else if (getExclusiveOwnerThread() == current) {
        //     重入之后state会增加
        //     if (++c < 0) // overflow
        //         throw new Error("Maximum lock count exceeded");
        //     setState(c);
        //     return true;
        // }
        return false;
    }
    //  判断自己需不需要排队: 判断AQS队列的组成情况
    //  public final boolean hasQueuedThreads() {
    //      for (Node p = tail, h = head; p != h && p != null; p = p.prev)
    //          if (p.status >= 0)
    //              return true;
    //      return false;
    //  }

    //  TODO: 尝试获取锁的核心算法 ==> 在两次自旋的时候调用的方法
    //        公平锁: 即使锁处于自由状态，也需要判断是否需要排队 + 队列的情况
    //  protected final boolean tryAcquire(int acquires) {
    //      // getState() == 0 表示锁处于自由的状态
    //      // 如果hasQueuedPredecessors返回false，则表示请求的线程队列中排队的第一个线程
    //      // 前面两个条件成立，则进行CAS
    //      if (getState() == 0 && !hasQueuedPredecessors() && compareAndSetState(0, acquires)) {
    //          setExclusiveOwnerThread(Thread.currentThread());
    //          return true;
    //      }
    //      return false;
    //  }
    //
    //  判断是否需要排队: 队列是否初始化?
    //  1. false
    //  2. true
    //     2.1 队列中的元素 > 1
    //     2.2 队列中的元素 = 1 当队列中只剩一个"虚拟结点"时
    //  public final boolean hasQueuedPredecessors() {
    //      Thread first = null;
    //      Node h, s;
    //      if ((h = head) != null &&
    //          ((s = h.next) == null || (first = s.waiter) == null || s.prev == null))
    //          // retry via getFirstQueuedThread
    //          first = getFirstQueuedThread();
    //      // TODO: 如果是AQS队列中第一个排队的线程了询问是否加锁
    //      // 当锁自由状态时，且是第一个排对的线程，返回false
    //      return first != null && first != Thread.currentThread();
    //  }

    // AQS入队算法:
    // final void enqueue(Node node) {
    //     if (node != null) {
    //         for (;;) {
    //             Node t = tail;
    //             node.setPrevRelaxed(t);        // avoid unnecessary fence
    //             if (t == null)                 // initialize
    //                 tryInitializeHead();
    //             else if (casTail(t, node)) {
    //                 t.next = node;
    //                 if (t.status < 0)          // wake up to clean link
    //                     LockSupport.unpark(node.waiter);
    //                 break;
    //             }
    //         }
    //     }
    // }
    // TODO: 如果初始AQS队列是空的，则在队列的头部添加一个"专属结点(结点的thread属性没有赋值)"
    // private void tryInitializeHead() {
    //     Node h = new ExclusiveNode(); ExclusiveNode类型直接继承自Node结点类型
    //     if (U.compareAndSetReference(this, HEAD, null, h)) 使用CAS进行赋值
    //         tail = h;
    // }
}
