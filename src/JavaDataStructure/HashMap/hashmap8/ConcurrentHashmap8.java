package JavaDataStructure.HashMap.hashmap8;

// JDK 1.8:
// 数组+链表(单向和双向链表)+红黑树TreeBin(里面包含了TreeNode红黑树)
// 1. 只有一个数组Node<K,V>, 没有Segment对象
// 2. 通过UnSafe操作 + synchronized(锁firstNode)解决并发的问题
public class ConcurrentHashmap8 {

    // final V putVal(K key, V value, boolean onlyIfAbsent) {
    //     if (key == null || value == null) throw new NullPointerException();
    //     int hash = spread(key.hashCode());
    //     int binCount = 0;
    //     for (Node<K,V>[] tab = table;;) {
    //         Node<K,V> f; int n, i, fh; K fk; V fv;
    //         if (tab == null || (n = tab.length) == 0)
    //             使用自旋的方式进行初始化，只有一个线程能完成初始化
    //             tab = initTable();
    //         else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
    //             TODO: 通过CAS的方式在table的i位置设置指定Node值
    //             if (casTabAt(tab, i, null, new Node<K,V>(hash, key, value))) {
    //                 如果CAS成功，则退出，如果失败可能是其他线程赋值成功了
    //                 break;  // no lock when adding to empty bin
    //             }
    //         } else if ((fh = f.hash) == MOVED) // MOVED=-1 表示当前的ConcurrentHashmap正在扩容
    //             tab = helpTransfer(tab, f);    // 帮助扩容，通过ForwardingNode结点类型来判断是否table的一个位置转移了
    //         else if (onlyIfAbsent // check first node without acquiring lock
    //                  && fh == hash
    //                  && ((fk = f.key) == key || (fk != null && key.equals(fk)))
    //                  && (fv = f.val) != null)
    //             return fv;
    //         else {
    //             V oldVal = null;
    //             TODO: 在链表(红黑树)的头结点firstNode加锁
    //             synchronized (f) {
    //                 if (tabAt(tab, i) == f) { 再次判断f是否还存在，重新检查
    //                     if (fh >= 0) { 判断hash值，表示链表上的一个结点
    //                         binCount = 1;
    //                         for (Node<K,V> e = f;; ++binCount) { binCount记录链表上的元素个数
    //                             K ek;
    //                             if (e.hash == hash &&((ek = e.key) == key ||(ek != null && key.equals(ek)))) {
    //                                 找到结点之后更新旧值
    //                                 oldVal = e.val;
    //                                 if (!onlyIfAbsent)
    //                                     e.val = value;
    //                                 break;
    //                             }
    //                             Node<K,V> pred = e;
    //                             if ((e = e.next) == null) {
    //                                 TODO: 在链标的最后添加新的结点，使用"尾插法"
    //                                 pred.next = new Node<K,V>(hash, key, value);
    //                                 break;
    //                             }
    //                         }
    //                     } else if (f instanceof TreeBin) { TODO: TreeBin表示整个红黑树(使用TreeNode), 为了在上面获取锁
    //                         Node<K,V> p;
    //                         binCount = 2;
    //                         在红黑树中插入新的结点
    //                         if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,value)) != null) {
    //                             oldVal = p.val;
    //                             if (!onlyIfAbsent)
    //                                 p.val = value;
    //                         }
    //                     }
    //                     else if (f instanceof ReservationNode)
    //                         throw new IllegalStateException("Recursive update");
    //                 }
    //             }
    //             if (binCount != 0) {
    //                 if (binCount >= TREEIFY_THRESHOLD)
    //                     treeifyBin(tab, i); 将链表转成红黑树
    //                 if (oldVal != null)
    //                     return oldVal;
    //                 break;
    //             }
    //         }
    //     }
    //     addCount(1L, binCount); 启动正常的扩容逻辑
    //     return null;
    // }

    //  private final void treeifyBin(Node<K,V>[] tab, int index) {
    //     Node<K,V> b; int n;
    //     if (tab != null) {
    //         if ((n = tab.length) < MIN_TREEIFY_CAPACITY)
    //             tryPresize(n << 1);
    //         else if ((b = tabAt(tab, index)) != null && b.hash >= 0) {
    //             对table的index第一个结点进行加锁
    //             synchronized (b) {
    //                 if (tabAt(tab, index) == b) {
    //                     TreeNode<K,V> hd = null, tl = null;
    //                     for (Node<K,V> e = b; e != null; e = e.next) {
    //                         TreeNode<K,V> p = new TreeNode<K,V>(e.hash, e.key, e.val, null, null);
    //                         if ((p.prev = tl) == null)
    //                             hd = p;
    //                         else
    //                             tl.next = p;
    //                         tl = p;
    //                     }
    //                     改成双向链表，后面再改成红黑树
    //                     setTabAt(tab, index, new TreeBin<K,V>(hd));
    //                 }
    //             }
    //         }
    //     }
    // }

    // JDK 1.8: ConcurrentHashMap  ==> 支持多线程扩容transfer()，不会中断线程
    // 1. 计算步长=2和转移的位置
    // 2. 不同的线程转移指定区域元素，会对转移的位置加锁/不能put元素到该位置
    // 3. 转移完成之后，从右往左边进行转移，再查找需要转移的元素
    // 4. 在过程中还需要判断新的数组是否需要扩容(新的数组在并发的使用)
    // 5. 转移完成之后的线程需要等待其他转移线程的结束，再次走helpTransfer()帮助转移
    // private final void addCount(long x, int check) {
    //      CounterCell[] cs; long b, s;
    //      多个线程在调用addCount()的过程中，由于存在竞争，使用CAS可能存在一定性能问题(当线程过多时)
    //      不同的线程通过随机值+CounterCell来进行+1，分散竞争
    //      当线程完成CounterCell.value+1之后，则认为完成
    //      TODO: 在调用hashmap.size()的时候，调用sumCount()直接统计的是baseCount+所有CounterCell的中value值
    //      if ((cs = counterCells) != null ||
    //          !U.compareAndSetLong(this, BASECOUNT, b = baseCount, s = b + x)) {
    //          CounterCell c; long v; int m;
    //          boolean uncontended = true;
    //          if (cs == null || (m = cs.length - 1) < 0 || (c = cs[ThreadLocalRandom.getProbe() & m]) == null ||
    //              !(uncontended = U.compareAndSetLong(c, CELLVALUE, v = c.value, v + x))) {
    //              fullAddCount(x, uncontended);
    //              return;
    //          }
    //          if (check <= 1)
    //              return;
    //          s = sumCount();
    //      }
    //      下面是扩容的逻辑
    //      if (check >= 0) {
    //          Node<K,V>[] tab, nt; int n, sc;
    //          while (s >= (long)(sc = sizeCtl) && (tab = table) != null && (n = tab.length) < MAXIMUM_CAPACITY) {
    //              int rs = resizeStamp(n) << RESIZE_STAMP_SHIFT;
    //              if (sc < 0) {
    //                  if (sc == rs + MAX_RESIZERS || sc == rs + 1 ||
    //                      (nt = nextTable) == null || transferIndex <= 0)
    //                      break;
    //                  if (U.compareAndSetInt(this, SIZECTL, sc, sc + 1))
    //                      transfer(tab, nt);
    //              } else if (U.compareAndSetInt(this, SIZECTL, sc, rs + 2))
    //                  transfer(tab, null);
    //              s = sumCount();
    //          }
    //      }
    //  }

    // 类似于LongAdder类型的"分段CAS操作"
    // private final void fullAddCount(long x, boolean wasUncontended) {
    //    if ((c = cs[(n - 1) & h]) == null) {
    //       if (cellsBusy == 0) {            // Try to attach new Cell
    //           创建CounterCell数组中的一个对象
    //           CounterCell r = new CounterCell(x); // Optimistic create
    //           如果CounterCell数组不忙，没有线程在使用，则使用CAS将标记从0改成1 ==> 只有一个线程能够修改成功
    //           if (cellsBusy == 0 && U.compareAndSetInt(this, CELLSBUSY, 0, 1)) {
    //               boolean created = false;
    //               try {  // Recheck under lock
    //                   CounterCell[] rs; int m, j;
    //                   if ((rs = counterCells) != null &&(m = rs.length) > 0 &&
    //                      加锁之后再次检查CounterCell数组指定的位置是否为null
    //                       rs[j = (m - 1) & h] == null) {
    //                       rs[j] = r; 将创建的对象放置到数组算出来的位置
    //                       created = true;
    //                   }
    //               } finally {
    //                   cellsBusy = 0; 用完之后，再修改"繁忙标记"
    //               }
    //               if (created)
    //                   break; 创建成功之后，跳出外层的for自旋循环
    //               continue; // Slot is now non-empty
    //           }
    //       }
    //    }
    //    else if (U.compareAndSetLong(c, CELLVALUE, v = c.value, v + x)) 对值进行+x，如果成功则break;
    //        break;
    //    else if (counterCells != cs || n >= NCPU) 约束扩容的条件，如果CounterCell数组变化了则不会扩容
    //        collide = false;   // At max size or stale
    //    else if (!collide)
    //        必须从false改成true之后才有可能走到后面的扩容逻辑
    //        加了两次都没有加上，
    //        collide = true;
    //    else if (cellsBusy == 0 && U.compareAndSetInt(this, CELLSBUSY, 0, 1)) { TODO: 操作数组时必须要获取到标记
    //        try {
    //            对CounterCell数组进行扩容
    //            if (counterCells == cs) // Expand table unless stale
    //                counterCells = Arrays.copyOf(cs, n << 1);
    //        } finally {
    //            cellsBusy = 0;
    //        }
    //        collide = false;
    //        continue;  // Retry with expanded table
    //    }
    //    h = ThreadLocalRandom.advanceProbe(h); 在重新计算hash
    // }
}
