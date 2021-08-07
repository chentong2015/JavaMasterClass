package JavaBasicLanguage.Base01OOP;

import JavaDataStructure.Collections.BaseHashSet;

//                        public  protected  private   static
// Top Level Class :        YES       NO       NO        NO
// Member Class    :        YES       YES      YES       YES
// Local Class     :        NO        NO       NO        NO
// Anonymous class :        NO        NO       NO        NO
// Class Fields    :        YES       YES      YES       YES

// 默认不写, 表示该类型只能在当前package中被访问
public class MasterClass {

    // 成员类型可以使用4种修饰符
    // private static class InnerClass { }

    // 受包含的成员，只能在当前的package和它的子类中被访问到 !!
    protected void testProtectedMethod() {
    }

    // 默认不写约束, 方法只能在Package内被访问到, 实列方法和静态方法都是如此 !!
    static int getNumber() {
        return 10;
    }

    // TODO: Java语言中默认的实例方法是虚方法，可以别继承类型重写的。除非使用final关键字
    public void testVirtualMethod() {
        System.out.println("This is a virtual method");
    }

    // TODO: 关于.equals()方法和.hashCode()的认识
    private final String name = "name id"; // 通过name id来实现类型的.equal()方法

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
        // instanceof: 子类型的对象满足IS-A的关系，所以判断是为True
        if (comparedObject instanceof MasterClass) {
            BaseHashSet theSet = (BaseHashSet) comparedObject;
            // To check the name value
        }
        String compareName = ((MasterClass) comparedObject).getName();
        return this.name.equals(compareName);
    }

    // hashCode()方法: converting the internal address of the object into an integer, @15aeb7ab -> 363771819
    // 1. 如果两个对象相等, 则hashCode()方法返回的值是相等的
    // 2. 不同的对象可以返回相同的hashCode, 具有相同hashCode的对象不一定是equals()相等
    // 3. hashCode()的计算值应该具有良好的离散分布性, 同时具有唯一性
    @Override
    public final int hashCode() {
        return name.hashCode();  // 只用类型的field的hashCode来作为整个类型的hashCode
        // return this.name.hashCode() + 1; 通过添加偏移量来取解决上面的问题
        // return 0; 不能直接返回0
    }
}

