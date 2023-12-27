package JavaBasicLanguage.BaseOOP.Inheritance;

import java.util.Collection;
import java.util.HashSet;

// 继承打破了封装性(构成强耦合)，子类依赖与父类中特定方法的实现细节
public class HashSetInheritance<E> extends HashSet<E> {

    private int addCount = 0;

    public HashSetInheritance() {
    }

    public HashSetInheritance(int initialCapacity) {
        super(initialCapacity);
    }

    // TODO. 由于这里重写了HashSet中的方法，因此会调用到该方法进行逐个添加
    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    // HashSet的addAll()方法会调用自身的add()方法，导致统计出错
    // 需要了解父类addAll()方法的具体实现才能避免在继承时出错
    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
