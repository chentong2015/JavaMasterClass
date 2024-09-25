package concurrent_api.hashmap;

import java.util.Map;

// Hashmap Node结点保存的四个基本属性
public class HashmapNode<K, V> implements Map.Entry<K, V> {

    K key;
    V value;
    int hash; // 算出来的hash值，用来定位数组索引位置
    HashmapNode<K, V> next; // 指向下一个结点Node：挂载的链表或者是红黑树

    @Override
    public K getKey() {
        return null;
    }

    @Override
    public V getValue() {
        return null;
    }

    @Override
    public V setValue(V value) {
        return null;
    }
}
