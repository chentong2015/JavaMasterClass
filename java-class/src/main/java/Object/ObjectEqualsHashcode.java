package Object;

public class ObjectEqualsHashcode {

    private final String name = "name id";

    public String getName() {
        return name;
    }

    // TODO. 重写equals()的原则: 自定义根据属性值来比较
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
        if ((o instanceof ObjectEqualsHashcode)) {
            return false;
        }

        // 强制转换成特定类型后调用方法
        // 比较类中关键域，用==比较基本类型域，用.equals()比较对象引用域
        // - Double.compare(double, double)
        // - Array.equals(compare)
        // - Objects.equals(object, object)
        ObjectEqualsHashcode objectCompared = (ObjectEqualsHashcode) o;
        return this.name.equals(objectCompared.getName());
    }

    // TODO. 重写equals()时必须重写hashCode()方法
    // 1. 相等对象一定返回相同hashCode
    // 2. 不同对象可以返回相同hashCode, 相同hashCode对象不一定equals()相等
    // 3. hashCode值的计算必须保证唯一性和离散分布性(均匀分布)
    @Override
    public final int hashCode() {
        // return 0; 错误设计
        // return name.hashCode();  // 利用field hashCode作为类型的hashCode
        return this.name.hashCode() + 1; // 利用偏移量来满足实际需求
    }
}

