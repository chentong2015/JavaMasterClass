package hashmap.jdk7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// JDK7: 数组 + 链表: 同时保证插入和搜索的效率
public class CoreHashmap7 {

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
    // hash(key) {
    //    int h = key.hashCode();
    //    return h ^ (h >>> 16);
    //    TODO: 使用异或运算，使高位bit参与运算，尽量保证index的随机性分布
    //    由于求&的运算只取了低位的4个bit位，没有使用到高位bit参加运算，因此很有可能出现hash冲突
    //    h: 0111 0101
    //    >> 0000 0111
    //    ^  0111 0010
    // }

    // indexFor(int h, int length) {
    //   return h & (length-1); TODO: 这里的运算前提是必须保证数组的容量是"二的幂次方值", 保证取余 + 随机性
    //   h: 0101 0101
    //   15:0000 1111  保证了在数组的index区间中，只取低位上全部的hashcode值
    //   &  0000 0101  由于上面的hashcode本身时随机的，因此这里算出来的index位置也具有随机性
    //                 h&length比h%length更有效率
    // }

    // 扩容机制
    // size表示总共存储了多少个元素
    // if(size>threshold && table[bucketIndex] != null) {
    //    阈值threshold=table.length*loadFactor;
    //    table[bucketIndex] 插入在数组的index位置不为空 ==> Just for JDK7
    //    resize(2 * table.length); 扩容的倍数
    // }
    // resize() {
    //   Entry[] newTable = new Entry[newCapacity];
    //   transfer();
    //   table = newTable;
    // }

    // TODO: 多线程在JDK7扩容时会造成循环链表，一旦遍历链表则死循环 ==> 来源于"头插法"
    //       只所以不直接转移链表的头结点，原因是可以在扩容的时候分散链表中的结点，进行存储 ==> 使用扩容后计算出来的长度有两种可能
    // transfer() {
    //    将原来table中的数据转移到新的扩容的新数组中(双重循环)
    //    转移的时候，新的index位置要么在原来位置，要么+table.length
    //    链表在转移时，颠倒了原来的顺序
    // }

    // modCount++
    //
    //
    //

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
