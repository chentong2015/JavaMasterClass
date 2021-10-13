package hashmap;

// JDK 1.8:
// 数组+链表+红黑树TreeBin(里面包含了TreeNode红黑树)
// 1. 只有一个数组Node<K,V>, 没有Segment对象
// 2. 通过CAS + synchronized(firstNode)解决并发的问题
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
    //         } else if ((fh = f.hash) == MOVED) // MOVED=-1 表示当前的ConcurrentHashmap正在扩容 !!
    //             tab = helpTransfer(tab, f);    // 帮助一起扩容
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

    // private final void addCount(long x, int check) {
    //    多个线程在调用addCount()的过程中，由于存在竞争，使用CAS也是存在一定的性能问题(当线程过多时)
    //    不同的线程通过随机值+CounterCell来进行+1，分散竞争
    //    当线程完成CounterCell.value+1之后，则认为完成
    //    TODO: 在调用hashmap.size()的时候，调用sumCount()直接统计的是baseCount+所有CounterCell的中value值
    // }
}
