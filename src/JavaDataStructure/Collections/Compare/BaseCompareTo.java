package JavaDataStructure.Collections.Compare;

// TODO. 实现Comparable接口的原则: 实现一个对排序敏感的类，方便轻松的被分类和搜索
// 1. compareTo()不能跨不同类型进行比较
// 2. compareTo()必须满足自反性，对称性，传递性
public class BaseCompareTo implements Comparable<BaseCompareTo> {

    @Override
    public int compareTo(BaseCompareTo o) {
        return 0;
    }
}
