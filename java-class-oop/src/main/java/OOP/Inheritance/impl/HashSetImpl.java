package OOP.Inheritance.impl;

import java.util.Collection;
import java.util.HashSet;


public class HashSetImpl<E> extends HashSet<E> {

    private int addCount = 0;

    public HashSetImpl(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();

        // TODO. HashSet的addAll()方法会调用自身的add()方法
        //  实际上会调用到自定义重写的add()方法，造成统计错误
        return super.addAll(c);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    public int getAddCount() {
        return addCount;
    }
}
