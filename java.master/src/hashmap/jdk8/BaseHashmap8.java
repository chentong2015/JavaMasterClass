package hashmap.jdk8;

// JDK 1.8: 数组+链表+红黑树
public class BaseHashmap8 {

    // Entry<key,value> Entry类型的对象
    //  -> Node -> Node ... 冲突的时候，需要挂链表  O(n)
    //  -> 当链表长度大于8时,自动转成红黑树,优化查询   O(log(n))

    // put()方法源码
    //  if ((p = tab[i = (n - 1) & hash]) == null)    具体在Node数组中的槽位位置
    //     TODO: 多线程场景下会出现并发的(判断)问题
    //     tab[i] = newNode(hash, key, value, null);  如果指定槽位没有值，则直接插入node
    //  else
    //     if ((k = p.key) == key || (key != null && key.equals(k))) 如果有相同的key，则直接覆盖
    //     else if (p instanceof TreeNode)
    //          如果已经是红黑树，则在树中添加node结点，如果长度小于6，则转下面的链表 !!
    //     else
    //          挂链表的情况(遍历链表，一般插尾部)，如果长度大于8，转上面的红黑树 !!
    //  if (++size > threshold)       超过指定的长度，需要扩容size
    //     resize();
}
