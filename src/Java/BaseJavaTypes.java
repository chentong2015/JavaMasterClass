package Java;

import java.util.ArrayList;
import java.util.List;

// Java原生不支持Tuple元组       ===> C++, C#支持 (固定大小的不同类型值的集合)
// Java原生不支持Struct结构体    ===> C++, C#支持
// Java原生不支持Union联合体     ===> C++支持
public class BaseJavaTypes {

    // 类型和值的所用: 所有的值都是有类型(强类型)
    // 1. 存储在内存中的时候，有类型约束，决定存储空间的大小
    // 2. 该类型的值具有指定的操作
    // 3. 使用类型声明变量，决定和约束变量(在内存中关联关系)

    // 变量Identifier(ID在指定的Scope范围中是唯一)的声明
    // 1. 不能用保留字符keywords
    // 2. 开头字符: 非数字 + 后续字符: 非标点符号，除了_和$
    // 3. 大概分为4种变量: 类型成员变量, 局部变量, 参数变量, 对象的引用变量

    // 命名规范问题
    // Pascal命名法     -> object pascal团队   -> 微软C#语言的发展: @开头 var === auto (C++
    // Camel驼峰命名法   -> java
    // 大写加下划线命名法 -> 声明常量static final -> 常量的运行效率更快

    // TODO: Java基本数据类型
    // 1. Reference type 引用类型 (class, interface, array数组(支持协变, 转换成它的基类))
    // 2. Primitive type 原生类型 (value type直接存值的类型)  ===> 值类型方案"内联类型"，优化Java泛型 !!
    // 3. String         特殊类型
    // 以上两种类型在赋值的时候，获得的均是(变量原始存储的"值")值的一份copy
    public static void testMain(String[] args) {
        System.out.println('3'); // 字符的字面值
        int height = 10;         // 所有的变量都有类型，存在类型的转换
        final double PI = 3.14;  // 默认浮点型是double类型
        System.out.println(height * PI);

        float f = 3.14f;         // 使用f注明float类型
        double d = 3.14f;        // d容纳的是什么的类型
        int y = 'a';             // y = 97; 根据字符对应的unicode码值来进行判断和计算
        int x = Integer.parseInt("123");
        int intNum = Integer.parseInt("2018");
        double doubleNum = Double.parseDouble("2015.06");
    }

    // 变量和内存 & 赋值操作；左值 = 右值
    public static void testMemory() {
        // 先分配指定的内存空间，变量x(标签)关联在4个字节上，最低的字节上
        // 0000 0000
        // 0000 0000
        // 0000 0000
        // 0000 1010 从低位开始存储
        int x = 10;

        // 所有引用类型的变量都分配4个字节, 用来存放内存地址(最低位置的地址 0x00F1)
        // 0000 0000
        // 0000 0000
        // 0000 0000
        // 1111 0001 => F1

        // Student student = new Student();
        // student引用变量存放引用对象的实际内存地址 @14ae5a5
    }

    // TODO: 自动装箱(AutoBoxing)和拆箱(unBoxing)认识
    // 1. 将int值赋值给Integer类型的变量时，使用Integer.valueOf(i)进行自动装箱
    // 2. 将Integer变量赋值给int类型的变量时，自动拆箱
    // 3. 当Integer变量进行算术运算时，自动拆箱
    public void testAutoBoxingAndUnBoxing() {
        // 根据JLS的要求，为Integer设置IntegerCache缓存以支持在默认区间(-128,127]范围int值的自动装箱
        // IntegerCache.cache[i + (-IntegerCache.low)]; 该cache数组只加载一次，根据index返回指定装箱的对象的引用
        Integer aa = 1;
        Integer bb = 1;
        System.out.println(aa == bb); // true aa和bb变量都会引用缓存中的同一个装箱的对象

        Integer aaa = 200;            // new Integer(100) 在范围之外，直接构建堆上新的对象
        Integer bbb = 200;
        System.out.println(aaa == bbb);      // false  == 运算比较引用而不是值
        System.out.println(aaa == bbb + 0);  // true   == 在遇到算术运算的情况下会自动拆箱，转成int值来比较
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



