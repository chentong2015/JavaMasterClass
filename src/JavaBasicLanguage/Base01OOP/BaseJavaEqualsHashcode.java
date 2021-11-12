package JavaBasicLanguage.Base01OOP;

import JavaDataStructure.Collections.BaseHashSet;

public class BaseJavaEqualsHashcode {

    // TODO: 关于.equals()方法和.hashCode()的认识
    // 通过name id来实现类型的.equal()方法
    private final String name = "name id";

    public String getName() {
        return name;
    }

    // equals()方法必须满足5个条件约束: 自反性, 可逆性, 传递性, 对称性, 一致性(多次调用，得出同样的结果)
    // 重写类型的equals()方法时，必须同时重写hashCode()方法方法
    @Override
    public final boolean equals(Object comparedObject) { // 该方法返回true才能说明两个对象是相等的 !!!
        // Object.equals() 方法直接比较两个对象的引用是否相等
        // 通过自定义，使得不同引用的对象作为是相等的对象来处理
        if (this == comparedObject) {
            return true;
        }
        // 是否是相同类型，母类和子类型是不等的 ===> 这个是没有必要的
        // 1. 类型本身是不能被继承的
        // 2. 因为所使用field是pirate final的，在子类型中不会被改变 !!!
        if (comparedObject == null || (comparedObject.getClass() != this.getClass())) {
            return false;
        }
        // TODO: instanceof 子类型的对象满足IS-A的关系，所以判断是为True
        if (comparedObject instanceof BaseJavaEqualsHashcode) {
            BaseHashSet theSet = (BaseHashSet) comparedObject;
            // To check the name value
        }
        String compareName = ((BaseJavaEqualsHashcode) comparedObject).getName();
        return this.name.equals(compareName);
    }

    // hashCode()方法:
    // converting the internal address of the object into an integer, @15aeb7ab -> 363771819
    // 1. 如果两个对象相等, 则hashCode()方法返回的值是相等的
    // 2. 不同的对象可以返回相同的hashCode, 具有相同hashCode的对象不一定是equals()相等
    // 3. hashCode()计算值应该具有良好的离散分布性, 同时具有唯一性
    @Override
    public final int hashCode() {
        return name.hashCode();  // 只用类型的field的hashCode来作为整个类型的hashCode
        // return this.name.hashCode() + 1; 通过添加偏移量来取解决上面的问题
        // return 0; 不能直接返回0
    }
}

