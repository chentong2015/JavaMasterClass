package JavaBasicOOP.OOP.Inheritance;

import java.util.Collection;
import java.util.HashSet;

// 继承打破封装性(构成强耦合)，子类依赖与父类中特定方法的实现
// 在重写/实现时需要考虑父类的细节，避免造成额外调用
public class HashSetInheritance<E> extends HashSet<E> {

    private int addCount = 0;

    public HashSetInheritance(int initialCapacity) {
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
