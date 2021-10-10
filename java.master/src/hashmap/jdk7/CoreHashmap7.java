package hashmap.jdk7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// JDK7: 数组 + 链表: 同时保证插入和搜索的效率
public class CoreHashmap7 {

    // key --> key.hashCode() --> 14521600452 % length --> index 均匀分布在取值的范围中
    // node --> node --> node --> null 链表插入到头部效率更高，然后需要修改数组index位置的引用
    // Entry<K,V> 数组中存储的是Entry对象的引用地址
    //   table[index] = new Entry(key,value,null); 包含next引用地址
    //   table[index] = new Entry(key,value,table[index]); 头插法
    //   TODO: 在头插法时需要遍历指定index位置上的链表，如果存在相同的key，则直接覆盖旧的值
    //         由于不一定会遍历完所有的结点，因此仍然使用"头插法"

    public void testHashmap() {
        // 默认数组初始化容量 16
        // HashMap key支持null
        Map<Integer, String> map = new HashMap<>();
        map.put(10, "old value"); // 没有指定下标，通过key来确定到数组的下标
        String oldValue = map.put(10, "new value"); // 判断是否key是否存在
        map.get(10);
    }

    public void testArrayList() {
        // 底层使用数组来存储
        List<Integer> list = new ArrayList<>();
        list.add(10); // 默认添加在list的末尾
        list.get(1); // 通过下标来获取元素，效率高

        // TODO: 指定添加的位置，index位置后面的元素移动一个位置
        // System.arraycopy(elementData, index,elementData, index + 1,s - index);
        // elementData[index] = element;
        list.add(1, 10);
    }
}
