package hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// JDK 1.7: 数组+链表: 同时保证插入和搜索的效率
public class BaseHashmap7 {

    // key --> key.hashCode() --> 14521600452 % length --> index 均匀分布在取值的范围中
    // node --> node --> node --> null 链表插入到头部效率更高，然后需要修改数组index位置的引用
    // Entry<K,V> 数组(默认16)中存储的是Entry对象的引用地址
    //   table[index] = new Entry(key,value,null); 包含next引用地址
    //   table[index] = new Entry(key,value,table[index]); "头插法"
    //   TODO: 在头插法时需要遍历指定index位置上的链表，如果存在相同的key，则直接覆盖旧的值
    //         由于不一定会遍历完所有的结点，因此仍然使用"头插法"

    // 支持传入的key为null，做特殊处理
    // if(key==null) {
    //   return putForNullKey(value);
    //   addEntry(0,null,value,0); key==null时固定会固定存在第一个位置
    //   TODO: key!=null时算出来的index位置也有可能存在第一个位置，仍然需要挂链表 !!
    // }

    // 算出一个随机hash值 > 可以配置参数"jdk.map.althashing.threshold"来设置hashSeed来增加hash值的散列性
    private int hash(Object key) { // 重新计算hash值
        int h = key.hashCode();
        return (key == null) ? 0 : h ^ (h >>> 16);
        //   TODO: 使用异或运算，使高位bit参与运算，尽量保证index的随机性分布
        //   由于求&的运算只取了低位的4个bit位，没有使用到高位bit参加运算，因此很有可能出现hash冲突
        //   h: 0111 0101
        //   >> 0000 0111
        //   ^  0111 0010
    }

    // 使用上面算出来的hash值
    int indexFor(int h, int length) {
        return h & (length - 1);
        // TODO: 这里的运算前提是必须保证数组的容量是 "二的幂次方值", 保证取余 + 随机性
        // h: 0101 0101
        // 15:0000 1111  保证了在数组的index区间中，只取低位上全部的hashcode值
        // &  0000 0101  由于上面的hashcode本身时随机的，因此这里算出来的index位置也具有随机性
        // TODO: h&length比h%length更有效率 ==> 任意数 % 2^n => 任意数 ^ (2^n - 1)
    }
    // h            = 1111 1111 1111 1111 1111 0000 1110 1010
    // h>>>16       = 0000 0000 0000 0000 1111 1111 1111 1111
    // h^(h>>>16)   = 1111 1111 1111 1111 0000 1111 0001 0101 ==> 让高16位参与运算，增大随机性，减少hash冲突
    // (n-1)        = 0000 0000 0000 0000 0000 0000 0000 1111 ==> 二进制表示15，低位置上都为1
    // (n-1) & hash = 0000 0000 0000 0000 0000 0000 0000 0101 ==> 5存储在第5个槽位上

    // 扩容机制: ==> 可能重新对key进行hash计算
    // if(size>threshold && table[bucketIndex] != null) { size表示实际存储元素数目
    //    阈值threshold=table.length*loadFactor; 这里使用数组的容量来计算
    //    table[bucketIndex] 插入在数组的index位置不为空
    //    resize(2 * table.length); 扩容的倍数
    // }
    // resize() {
    //   Entry[] newTable = new Entry[newCapacity];
    //   transfer();
    //   table = newTable;
    // }

    // TODO: 多线程在JDK7扩容时会造成循环链表，遍历链表则死循环   ==> 来源于"头插法"
    //       不直接转移链表的头结点，可以在扩容时分散链表中结点存储 ==> 使用扩容后计算出来的长度有两种可能
    // transfer() {
    //    将原来table中的数据转移到新的扩容的新数组中(双重循环)
    //    转移的时候，新的index位置要么在原来位置，要么在+oldTable.length的位置
    //    链表在转移时，颠倒了原来的顺序
    // }

    // TODO: Fast-Fail快速失败，容错机制，抛出异常
    // modCount++ 修改次数(和异常有关系)
    // 避免并发的问题：一个线程在get，一个在修改，造成数据错误(读取不到)
    // for(String key: map.keySet()) { // 返回keyIterator<E>迭代器
    //    if(key.equals("test") {      // keyIterator == expectedModCount
    //       map.remove(key);          // "删除"操作会导致modCount++
    //    }
    // }
    //
    // 使用迭代器可以避免异常的抛出
    // Iterator i$ = hasMap.keySet().iterator();
    // while(i$.hasNext()) {
    //   String key = (String)i$.next();
    //   if(key.equals("test")) {
    //      i$.remove();  // 这里会导致expectedModCount数值同步变化，不会抛出异常
    //   }
    // }

    // 关于HashMap的几个问题:
    // 1. Entry<K,V> 数组的长度必须是"二的幂次方"的原因?
    //    使得算出来的index位置更加的随机(减少冲突), 方便利用"与运算"来求模
    // 2. hashCode()和equals()有什么作用?
    //    通过key的hashCode()来进行hash运算，然后计算下标(h & (length-1))
    //    使用"key.equals(k)"在链表或者红黑树中查找对应的结点
    public void testHashmap() {
        Map<Integer, String> map = new HashMap<>(10); // 找到大于等于10的二的幂次方数
        map.put(10, "old value"); // 没有指定下标，通过key来确定到数组的下标
        String oldValue = map.put(10, "new value"); // 判断是否key是否存在
        map.get(10);
    }

    // ArrayList底层使用数组来存储
    public void testArrayList() {
        List<Integer> list = new ArrayList<>();
        list.add(10); // 默认添加在list的末尾
        list.get(1); // 通过下标来获取元素，效率高
        // TODO: 指定添加的位置，index位置后面的元素移动一个位置
        // System.arraycopy(elementData, index,elementData, index + 1,s - index);
        // elementData[index] = element;
        list.add(1, 10);
    }
}
