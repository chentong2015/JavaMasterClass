package JavaBasic.BoxingUnboxing;

import java.util.ArrayList;
import java.util.List;

// TODO: 指定类型的包装器(包装类), 模仿int的行为, 但是Copy的是对象
//  Java不支持原始类型(Primitive Type)的泛型, int不自动装箱就无法转型为Object
//  因为在类型擦除之后没有办法插入强制类型转换，包装类型在Java泛型的实现中做强制类型转换使用
public class TypesBoxingUnboxing {

    // Java泛型中无数构造包装类和装箱，拆箱的开销，使得泛型比较慢 !!
    public static void testAutoBoxingAndUnBoxingList() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            // nums.add(Integer.valueOf(i));
            // AutoBoxing 自动的将值类型装箱成对应的引用类型
            nums.add(i);
        }
        for (int i = 0; i <= 10; i++) {
            // nums.get(i).intValue();
            // Unboxing 自动的将引用类型拆箱成值类型
            System.out.println(nums.get(i));
        }
    }

    // TODO: 自动装箱(AutoBoxing)和拆箱(unBoxing)
    // 1. 将int值赋值给Integer类型的变量时，使用Integer.valueOf(i)进行自动装箱
    // 2. 将Integer变量赋值给int类型的变量时，自动拆箱
    // 3. 当Integer变量进行算术运算时，自动拆箱
    //
    // 根据JLS的要求，为Integer设置"IntegerCache缓存"以支持在默认区间(-128,127]范围int值的自动装箱
    //     public static Integer valueOf(int i) {
    //        if (i >= IntegerCache.low && i <= IntegerCache.high)
    //            return IntegerCache.cache[i + (-IntegerCache.low)];
    //        return new Integer(i);
    //     }
    // 该cache数组只加载一次，根据index返回指定装箱的对象的引用
    public void testAutoBoxingAndUnBoxing() {
        Integer x = 100;
        Integer y = x;
        y = 200; // new Integer(200) 这里自动装箱构建新的对象，返回引用给变量

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

    // Boolean <-> boolean 装箱和拆箱的问题
    private void testBooleanUnBoxing(boolean isCommit) {
        Boolean isAutoCommit = isCommit;
        // 直接在装箱类型上进行!操作符的运算，在拆箱时可能导致NullPointer异常
        if (!isAutoCommit) {
            System.out.println("not auto commit");
        }
        if (Boolean.FALSE.equals(isAutoCommit)) {
            System.out.println("not auto commit");
        }
    }
}
