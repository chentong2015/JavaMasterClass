package jvm_basics.concurrent_packages.JavaBasic;

import java.util.ArrayList;
import java.util.List;

// 1. 每一个基本类型都有一个对对应的引用类型 => 装箱基本类型(Boxed primitive)
// 2. 当基本类型和装箱类型在操作中混合使用时，装箱基本类型就会自动拆箱，如果为null则拆箱失败
// 3. 不要在装箱类型上运用==操作符 => ==运算符对于基本类型是比较值，但对装箱类型则是比较引用 !!

// TODO. 装箱基本类型(Boxed primitive)的使用场景 ? 为什么存在
// 1. 装箱基本类型作为集合中的元素，键和值
// 2. 对于参数化的类型和方法，必须使用装箱基本类型作为类型参数
// 3. 在进行反射方法调用时，必须使用装箱基本类型
public class Base2JavaBaseTypesBoxed {

    // TODO: 指定类型的包装器(包装类), 模仿int的行为, 但是Copy的是对象
    //  Java不支持原始类型(Primitive Type)的泛型, int不自动装箱就无法转型为Object
    //  因为在类型擦除之后没有办法插入强制类型转换，包装类型在Java泛型的实现中做强制类型转换使用
    private void testInteger() {
        Integer x = 100;
        Integer y = x;
        y = 200; // new Integer(200) 这里自动装箱构建新的对象，返回引用给变量
        System.out.println(x); // x = 100
        System.out.println(y); // y = 200
    }

    // TODO: 自动装箱(AutoBoxing)和拆箱(unBoxing)认识
    // 1. 将int值赋值给Integer类型的变量时，使用Integer.valueOf(i)进行自动装箱
    // 2. 将Integer变量赋值给int类型的变量时，自动拆箱
    // 3. 当Integer变量进行算术运算时，自动拆箱
    public void testAutoBoxingAndUnBoxing() {
        // 根据JLS的要求，为Integer设置"IntegerCache缓存"以支持在默认区间(-128,127]范围int值的自动装箱
        //     public static Integer valueOf(int i) {
        //        if (i >= IntegerCache.low && i <= IntegerCache.high)
        //            return IntegerCache.cache[i + (-IntegerCache.low)];
        //        return new Integer(i);
        //     }
        // 该cache数组只加载一次，根据index返回指定装箱的对象的引用
        Integer aa = 1;
        Integer bb = 1;
        System.out.println(aa == bb); // true aa和bb变量都会引用缓存中的同一个装箱的对象

        Integer aaa = 200;            // new Integer(200) 在范围之外，直接构建堆上新的对象
        Integer bbb = 200;
        System.out.println(aaa == bbb);      // false  运算比较引用而不是值
        System.out.println(aaa == bbb + 0);  // true   在遇到算术运算的情况下会自动拆箱，转成int值来比较
        System.out.println(aaa.equals(bbb)); // true   equals()不会处理数据转型的问题，它判断引用的类型，然后判断引用类型的值(字面值)

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Long g = 3L;
        System.out.println(c == a + b);      // Ture
        System.out.println(g == a + b);      // Ture   自动拆箱之后, 在int和long值比较的时候会(自动)进行类型的隐式转换
        System.out.println(c.equals(a + b)); // True   运算之后成Integer类型，匹配
        System.out.println(g.equals(a + b)); // False  equals()判断数据类型不匹配
    }

    // Java泛型中无数构造包装类和装箱，拆箱的开销，使得泛型比较慢 !!
    public static void testAutoBoxingAndUnBoxingList() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            // nums.add(Integer.valueOf(i));
            nums.add(i); // AutoBoxing 自动的将值类型装箱成对应的引用类型
        }
        for (int i = 0; i <= 10; i++) {
            // nums.get(i).intValue();
            System.out.println(nums.get(i)); // Unboxing 自动的将引用类型拆箱成值类型
        }
    }
}
