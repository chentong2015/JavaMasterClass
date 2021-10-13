package hashmap;

// JDK 1.8: 数组+链表(单向和双向链表)+红黑树TreeNode
public class BaseHashmap8 {

    // TODO: 链表+红黑树共同实现，Node数组中可能同时存在两种数据结构
    // 1. Node -> Node ... 冲突的时候，需要挂链表  O(n)
    // 2. 当链表长度大于8时,自动转成红黑树,优化查询   O(log(n))
    // 3. 如果长度小于6，则转回成链表

    // final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
    //     Node<K,V>[] tab;
    //     Node<K,V> p;
    //     int n, i;
    //     if ((tab = table) == null || (n = tab.length) == 0)
    //         方法resize()包括对数组的初始化和扩容
    //         n = (tab = resize()).length;
    //     if ((p = tab[i = (n - 1) & hash]) == null)
    //         如果算出来index位置为空，则直接创建node，并设置对应位置的值
    //         tab[i] = newNode(hash, key, value, null);
    //     else {
    //         Node<K,V> e;
    //         K k;
    //         if (p.hash == hash &&((k = p.key) == key || (key != null && key.equals(k))))
    //             e = p; 如果有相同的key，则直接覆盖
    //         else if (p instanceof TreeNode)
    //             如果结点是红黑树的结点，在红黑树中插入结点
    //             e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
    //         else {
    //             走链表的逻辑，遍历链表
    //             for (int binCount = 0; ; ++binCount) {
    //                 if ((e = p.next) == null) {
    //                     TODO: 这里使用的是"尾插法"
    //                     p.next = newNode(hash, key, value, null);
    //                     if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
    //                         TODO: 如果链表的长度超过8(第9个元素进来时)，则转成红黑树
    //                         treeifyBin(tab, hash);
    //                     break;
    //                 }
    //                 if (e.hash == hash &&((k = e.key) == key || (key != null && key.equals(k))))
    //                     break;
    //                 p = e; 移动到下一个结点
    //             }
    //         }
    //         if (e != null) { // existing mapping for key
    //             V oldValue = e.value;
    //             if (!onlyIfAbsent || oldValue == null)
    //                 e.value = value;
    //             afterNodeAccess(e);
    //             return oldValue;
    //         }
    //     }
    //     ++modCount;
    //     if (++size > threshold)
    //         TODO: JDK1.7 扩容时还需要判断(table[bucketIndex]!=null)位置非空
    //         resize();
    //     afterNodeInsertion(evict);
    //     return null;
    // }

    // 从链表转成红黑树:
    // 当链表中的元素大于8个元素的时候，并且数组的长度大于等于64时才会转换
    // 如果数组的长度比较小，则可以通过扩容来缩小(分散)链表的长度
    // final void treeifyBin(Node<K,V>[] tab, int hash) {
    //     int n, index; Node<K,V> e;
    //     if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY) MIN_TREEIFY_CAPACITY=64
    //         resize(); 进行扩容
    //     else if ((e = tab[index = (n - 1) & hash]) != null) {
    //         TreeNode<K,V> hd = null, tl = null;
    //         TODO: 将单链表转换成双向链表，然后再转成红黑红树
    //         do {
    //             从链表的Node转换成红黑树的TreeNode(双向链表)
    //             TreeNode<K,V> p = replacementTreeNode(e, null);
    //             if (tl == null)
    //                 hd = p;
    //             else {
    //                 p.prev = tl;
    //                 tl.next = p;
    //             }
    //             tl = p;
    //         } while ((e = e.next) != null);
    //         if ((tab[index] = hd) != null)
    //             hd.treeify(tab); 真正的树化: 依次在红黑树中插入结点
    //             在红黑树中插入，需要先找到插入的位置，比较的因素：
    //             1. hashcode
    //             2. key compareto() 如何key实现接口，则不调用
    //             3. key getClass().getName()
    //             4. System.identifyHashCode(); 获取没有被重写的hashcode方法返回的值
    //     }
    // }

    //  final void treeify(Node<K,V>[] tab) {
    //     ...
    //     TreeNode<K,V> xp = p;
    //     判断在红黑树中的左移或者右移，直到走到null
    //     if ((p = (dir <= 0) ? p.left : p.right) == null) {
    //         x.parent = xp;
    //         if (dir <= 0)
    //            xp.left = x;
    //         else
    //            xp.right = x;
    //         再调用红黑树调整(平衡)的逻辑，改变root结点
    //         root = balanceInsertion(root, x);
    //         break;
    //     }
    //     ...
    //     moveRootToFront(tab, root);
    //     1. 将红黑树root移动到Node数组相应的位置tab[index] = root;
    //     2. 同时保证红黑树的root结点是双向链表的头结点(会使用到双向链表的next, prev双向指针)
    //     3. 最后验证红黑树的规则
    // }

    // if(size>threshold){
    //    resize();
    //    for (int j = 0; j < oldCap; ++j) {
    //       Node<K,V> e;
    //       if ((e = oldTab[j]) != null) {
    //          oldTab[j] = null;
    //          if (e.next == null)
    //              如果只有一个结点，则直接转移到新数组的新index位置
    //              newTab[e.hash & (newCap - 1)] = e;
    //          else if (e instanceof TreeNode)
    //              转移红黑树的过程
    //              1. 树中的结点也需要更加计算hash的算法来拆分，可以拆开
    //                 TODO: 如果红黑树的结点个数<=6，则需要转成链表存储，从TreeNode转成Node
    //              2. 红黑树上面还是有链表的信息.next
    //              3. 这里可能直接转移红黑树的root结点进行转移
    //              ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
    //          else {
    //              转移链表的过程
    //              Node<K,V> loHead = null, loTail = null;
    //              Node<K,V> hiHead = null, hiTail = null;
    //              Node<K,V> next;
    //              do {
    //                  next = e.next;
    //                  TODO: 计算之后，到新的数组中只有两种位置可能: 原来位置或者+oldTable.length位置
    //                  if ((e.hash & oldCap) == 0) {
    //                      找出存储在低位置的子链表，连接在一起，只有一起转移
    //                      if (loTail == null)
    //                          loHead = e; 确定头结点
    //                      else
    //                          loTail.next = e;
    //                      loTail = e; 后面只改变尾部的结点，确定尾部的结点
    //                  } else {
    //                      找出存储在高位置的子链表
    //                      if (hiTail == null)
    //                          hiHead = e;
    //                      else
    //                          hiTail.next = e;
    //                      hiTail = e;
    //                  }
    //              } while ((e = next) != null);
    //              if (loTail != null) {
    //                  loTail.next = null;
    //                  newTab[j] = loHead; 把低位的子链表转移到新数组的指定位置
    //              }
    //              if (hiTail != null) {
    //                  hiTail.next = null;
    //                  newTab[j + oldCap] = hiHead; 把高位的子链表转移到新数组的指定位置, oldCap原数组大小
    //              }
    //          }
    //       }
    //    }
    // }

    // final Node<K,V> getNode(Object key) {
    //    Node<K,V>[] tab; Node<K,V> first, e; int n, hash; K k;
    //    if ((tab = table) != null && (n = tab.length) > 0 &&
    //        判断通过key找到的那个位置上的第一个node是否为空
    //        (first = tab[(n - 1) & (hash = hash(key))]) != null) {
    //        if (first.hash == hash && ((k = first.key) == key || (key != null && key.equals(k))))
    //            如果第一个结点找到了，则直接返回
    //            return first;
    //        if ((e = first.next) != null) {
    //            如果第一个结点后面有结点，可能是链表的结点，也可能是红黑树的结点
    //            if (first instanceof TreeNode)
    //                从红黑树中取指定的结点，通过比较往左或者往右结点移动
    //                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
    //            从链表中循环遍历，找到指定的结点(判断hash值和key一致)
    //            do {
    //                if (e.hash == hash &&((k = e.key) == key || (key != null && key.equals(k))))
    //                    return e;
    //            } while ((e = e.next) != null);
    //        }
    //    }
    //    return null;
    // }

    // hashmap.remove(key, value);
    // 在移除结点的时候，如果红黑树删除结点之后，结点太少，则会转成链表
    // 转条件没有严格判断是否结点数目<=6，直接判断红黑树是否符合一定规则
    // if (root == null || (movable && (root.right == null
    //     || (rl = root.left) == null || rl.left == null))) {
    //        tab[index] = first.untreeify(map);  // too small
    //        return;
    // }
}
