package hashmap;

import java.util.concurrent.ConcurrentHashMap;

// JDK 1.7: 数组+链表
// Segment分段锁+CAS+Unsafe操作(包括cas)
public class ConcurrentHashmap7 {

    // 1. 背后存储的数据结构:
    //    Segment数组: segment对象(其中包含一个小型的HashMap, 也会挂链表) ...
    // 2. Segment对象
    //    对象中包含一个属性HashEntry<K,V>[] table; 内部的数组
    //    Segment<K,V>继承ReentrantLock, 通过调用.lock()方法来加锁, 不同再新创建一个ReentrantLock
    //    static class Segment<K,V> extends ReentrantLock implements Serializable {
    //        private static final long serialVersionUID = 2249069246763182397L;
    //        final float loadFactor;
    //        Segment(float lf) { this.loadFactor = lf; }
    //    }
    // 3. 运作模式: 早期版本计算hashcode的算法
    //    <key,value> 这里的key不能为null
    //    -> hashcode (h & (segments.length-1))
    //    -> index Segment数组下标(找到在Segment数组的指定位置)
    //    -> 调用Segment.lock()方法来加锁
    //    -> hashcode (h & entry.length-1)
    //    -> index 计算出segment内部Entry数组的index位置
    //    -> 将生成Entry<K,V>对象添加数组对应的位置，或者挂链表
    // 4. 多线程实现原理: 使用到CAS"乐观锁"
    //    如果运算到不同的Segment Index时，使用不同的Segment对象来加锁，不会造成冲突(可以同时进行，提高性能)
    //    <key1, value> -> Segment Index -> .lock() -> Add Entry<key, value>
    //    <key2, value> -> Segment Index -> .lock() -> 同一时刻只有一个线程能够添加到内部的数组中
    public void testMain() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(16, 0.75f, 8);
        map.put("test", "123");
        map.putIfAbsent("test", "12"); // 如果存在key，则不做操作
        map.get("test");
    }

    // 5. 构造方法源码
    //    new ConcurrentHashMap<>() -> this(initialCapacity, LOAD_FACTOR, 1);
    //           initialCapacity  初始的16表示总的内部的Entry<key,value>数组的容量     ===> 与HashMap保持一致
    //           LOAD_FACTOR      负载因子，到达总容量的多少时，需要进行内部Entry<>扩容   ===> 可不影响其他的Segment对象
    //           concurrencyLevel 最多支持多少个线程同时操作Segments数组(指定数组长度定值)
    //    约束条件: segment数组的长度必须是大于等于concurrencyLevel, 2^n数值            ===> 都需要通过hash计算数组index下标位置
    //            segment数组每个位置Segment对象内部Entry<k,v>数组长度最小值2, 满足2^n  ===> 根据初始构造出来cap的值判断
    // 在初始化的时候，构造Segments[]数组，同时在Segments[0]位置添加一个新对象
    // 在put添加数组新位置过程中直接使用之前计算过的参数 Segment<K,V> proto=ss[0];当作原型来使用

    // 6. put()方法源码
    //    public V put(K key, V value) {
    //       int hash = hash(key);  使用key计算出hash值
    //       // 通过hash值计算出对应的index位置
    //       // 这里的segmentShift和segmentMask有对应关系，保证"与计算"的bit位置上能够取到
    //       // 两次算下标其实是相互独立的, 为什么偏移计算 ?
    //       int segmentIndex = (hash >>> segmentShift) & segmentMask;
    //       // TODO: 通过UNSAFE方式取数组中指定位置的值(从内存取值)
    //       UNSAFE.getObject(segments, (j << SSHIFT) + SBASE);
    //       // 判断对应的位置是否有Segment的对象，如果没有则创建s.ensureSegment(segmentIndex);
    //       s.put(key, hash, value, false); 在指定的Segment中添加Entry<key, value>对象
    //    }

    //    方法只需要返回一个Segment<K,V>对象即可
    //    Segment<K,V> s.ensureSegment(segmentIndex) {
    //      // 过程中由两次取对象的判断，考虑在并发情况下，对象可能由别的线程所已经创建
    //      if null (can not get instance) ==> getObjectVolatile(ss,u) 多次if判断能够最大限度的减少要执行的代码, 提高性能 !!
    //        Segment<K,V> proto = segments[0];
    //        HashEntry<K,V> tab = (HashEntry<K,V>) new HashEntry[cap];
    //        it null (can not get instance) 这里重新check一次
    //             TODO: 解决线程并发的问题, CAS方法(指令, 性能比锁更加优化)保证只有一个线程能够成功设值
    //             Segment<K,V> seg = new Segment<K,V>(lf, threshold, tab);
    //             while(seg ... null) 自旋操作
    //                ss数组的第u个位置为null的时候才会执行seg=s;成功
    //                if(UNSAFE.compareAndSwapObject(ss,u,null,seg=s) break;
    //                将新创建的Segment对象添加到Segments数组指定位置

    //    调用Segment对象的.put方法，在内部添加Entry<Key,Value>对象
    //    s.put(key, hash, value, false) {
    //       segment.lock 多个线程同时进来，首先需要加锁
    //          tryLock() ? --> 非阻塞的，尝试去加锁，可能成功也可能失败
    //             scanAndLockForPut(key, hash, value); 等锁失败时会执行
    //          lock()      --> 阻塞，直到加到锁之后才会执行完成
    //                      --> 等效于while(!tryLock()) {} 自旋 ==> 很消耗CPU，但是在等待的过程中可以做一些事情
    //       类似于HashMap的put算法逻辑

    //    TODO: 线程在等待锁的过程中，可以执行一定的操作
    //    1. 使用key计算出index，遍历指定Entry数组的index位置值或所挂的链表，如果有相同的key，则修改这个Entry的value值
    //    2. 如果没有找到相同的key，则可以先生成Entry<K,V>对象，以便之后直接使用
    //    private HashEntry<K,V> scanAndLockForPut(K key, int hash, V value) {
    //       HashEntry<K,V> first = entryForHash(this, hash); // 拿到Entry Table链表的第一个Entry值
    //       HashEntry<K,V> e = first;
    //       HashEntry<K,V> node = null;
    //       int retries = -1; // negative while locating node
    //       while (!tryLock()) {
    //           HashEntry<K,V> f; // to recheck first below
    //           if (retries < 0) {
    //               if (e == null) {
    //                   if (node == null) // speculatively create node {
    //                      如果遍历到链表的结尾，则创建出HashEntry对象，后面再修改next结点指针
    //                      这里的node创建的对象可能不会使用到
    //                      node = new HashEntry<K,V>(hash, key, value, null);
    //                   }
    //                   retries = 0;
    //               } else if (key.equals(e.key)) {
    //                   如果找到了相同的key，也会标记出来 retries=0;
    //                   retries = 0;
    //               } else {
    //                   每次都会尝试去遍历链表，一旦拿到锁之后，则不管遍历到什么位置，跳出while循环，返回node结点(可能没有创建对象)
    //                   e = e.next;
    //               }
    //           } else if (++retries > MAX_SCAN_RETRIES) {
    //               遍历完链表之后，最多再允许再执行while() MAX_SCAN_RETRIES=64次(和CPU的核心数有关)
    //               如果尝试的此时过多，则直接使用lock()阻塞加锁，直到拿到为止
    //               lock();
    //               break;
    //           } else if ((retries & 1) == 0 && (f = entryForHash(this, hash)) != first) {
    //               判断增加的次数是否是偶数次数，如果是则取Entry Table链表的第一个Entry值
    //               如果取出来的结点不是原来记录的first结点，则说明有新的结点插到头部"头插法"
    //               更新所记录的头结点
    //               e = first = f; // re-traverse if entry changed
    //               设置成-1，重新进入到遍历链表的if条件中
    //               retries = -1;
    //           }
    //       }
    //       return node;
    //   }

    //   Segment内部扩容机制 ==> 支持多线程的扩容
    //   private void rehash(HashEntry<K,V> node) {
    //      HashEntry<K,V>[] oldTable = table;
    //      int oldCapacity = oldTable.length;
    //      int newCapacity = oldCapacity << 1;
    //      threshold = (int)(newCapacity * loadFactor);
    //      创建扩容新的数组，两倍的扩容
    //      HashEntry<K,V>[] newTable = (HashEntry<K,V>[]) new HashEntry[newCapacity];
    //      int sizeMask = newCapacity - 1;
    //      for (int i = 0; i < oldCapacity ; i++) {
    //          HashEntry<K,V> e = oldTable[i];
    //          if (e != null) {
    //              HashEntry<K,V> next = e.next;
    //              int idx = e.hash & sizeMask; 计算新数组的index下标位置
    //              if (next == null)   //  Single node on list
    //                  newTable[idx] = e;
    //              else {
    //                  TODO: 链表在转移的过程中，判断链表中是否有存储在连续位置的node结点
    //                  HashEntry<K,V> lastRun = e;
    //                  int lastIdx = idx;
    //                  for (HashEntry<K,V> last = next; last != null; last = last.next) {
    //                      int k = last.hash & sizeMask; 算出的node的下标
    //                      if (k != lastIdx) { 判断是否和之前的index下标一致
    //                          lastIdx = k;
    //                          lastRun = last;
    //                      }
    //                      之后记录链表中最后一段相同index的node
    //                  }
    //                  newTable[lastIdx] = lastRun; 直接移动链表最后一段node(需要转移到相同index的node)
    //                  TODO: 再来转移其余的node，直接使用"头插法"
    //                  for (HashEntry<K,V> p = e; p != lastRun; p = p.next) {
    //                      V v = p.value;
    //                      int h = p.hash;
    //                      int k = h & sizeMask;
    //                      HashEntry<K,V> n = newTable[k];
    //                      newTable[k] = new HashEntry<K,V>(h, p.key, v, n);
    //                  }
    //              }
    //          }
    //      }
    //      int nodeIndex = node.hash & sizeMask; // add the new node
    //      node.setNext(newTable[nodeIndex]);
    //      newTable[nodeIndex] = node;
    //      table = newTable; 使用扩容出来的新的数组
    //   }
    //
}
