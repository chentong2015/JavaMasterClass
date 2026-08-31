package Object;

// 自定义equals()方法逻辑实现对象属性值的比较
public class ObjectHashcodeEquals {

    private final String name = "name id";

    public String getName() {
        return name;
    }

    // TODO. 重写equals()原则 => 默认比较对象reference引用
    //    public boolean equals(Object obj) {
    //        return (this == obj);
    //    }
    // 1. 类型具有特定的逻辑相等，且superclass没有重写equals()
    // 2. 保证自反性, 可逆性, 传递性, 对称性, 一致性(多次调用，同样结果)
    // 3. 不能修改equals()方法的参数，否则会造成Overload重载
    @Override
    public final boolean equals(Object o) {
        // 使用==操作符，判断参数是否为这个参数的引用
        if (this == o) {
            return true;
        }
        // 使用instanceof来检查是否为特定类型
        if ((o instanceof ObjectHashcodeEquals)) {
            return false;
        }
        // 强制转换成特定类型后调用方法
        // 比较类中关键域，用==比较基本类型域，用.equals()比较对象引用域
        // - Double.compare(double, double)
        // - Array.equals(compare)
        // - Objects.equals(object, object)
        ObjectHashcodeEquals objectCompared = (ObjectHashcodeEquals) o;
        return this.name.equals(objectCompared.getName());
    }

    // TODO. 重写equals()时必须重写hashCode()方法
    // 1. 相等对象必须返回相同hashCode
    // 2. 不同对象可以返回相同hashCode, 相同hashCode对象不一定equals()相等
    // 3. hashCode()值的计算必须保证唯一性和离散分布性(均匀分布)
    @Override
    public final int hashCode() {
        // return 0; 错误设计
        // return name.hashCode();  // 利用field hashCode作为类型的hashCode
        return this.name.hashCode() + 1; // 利用偏移量来满足实际需求
    }
}

