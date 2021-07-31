package Java;

import java.util.ArrayList;
import java.util.List;

// Java原生不支持Tuple元组       ===> C++, C#支持 (固定大小的不同类型值的集合)
// Java原生不支持Struct结构体    ===> C++, C#支持
// Java原生不支持Union联合体     ===> C++支持
public class BaseJavaTypes {

    /**
     * Java编程语言的认识：
     * 1. 值value 所有的值都是有类型(强类型) 0.0f -> float; 0.0 -> double
     *      >> 存储在内存中的时候，有类型约束，决定存储空间的大小 int -> 4bytes
     *      >> 该类型的值具有指定的操作
     *      >> 使用类型声明变量，决定和约束变量(在内存中关联关系)
     * 2. 数据(值 字面值/常值 Literal values, 数字化) + 算法 + 面向对象/语言本身
     */

    /**
     * 变量Identifier (ID在指定的Scope范围中是唯一) ==> 值的名字 !!!!! 关联了一段内存空间
     * 1. 开头字符 : 非数字
     * 2. 后续字符 : 非标点符号，除了_和$
     * 以上都不能用保留字符keywords
     * .
     * Pascal命名法 -> object pascal团队 -> 微软C#语言的发展: @开头 var === auto (C++)
     * Camel驼峰命名法 -> java
     * 全大写 -> MIN_VALUE 一般用来声明常量static final
     * <1> 运算起来更加快
     * <2> 常数值在修改的时候很麻烦，每一个位置都需要修改
     * .
     * 基本数据类型
     * 1. Reference type 引用类型 (class, interface, array数组(支持协变))
     * 2. Primitive type 原生类型 (value type直接存值的类型)               ===> 值类型方案"内联类型"
     * 3. String         特殊类型
     * 以上两种类型在赋值的时候，获得的均是(变量原始存储的"值")值的一份copy
     */
    public static void testMain(String[] args) {
        System.out.println('3'); // 字符的字面值
        int height = 10;         // 所有的变量都有类型，存在类型的转换
        final double PI = 3.14;
        System.out.println(height * PI);

        float f = 3.14f;         // 3.14是double类型的; 区别float
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

        // 所有引用类型的变量都分配4个字节 => 用来存放内存地址(最低位置的地址 0x00F1)
        // 0000 0000
        // 0000 0000
        // 0000 0000
        // 1111 0001 => F1
        // Student student = new Student(); ===> student引用变量存放引用对象的实际内存地址 @14ae5a5
    }

    /**
     * 自动装箱和拆箱：AutoBoxing & unBoxing  ===> 在值类型和它的包装器(引用类型之间的转换)
     */
    public static void testAutoBoxingAndUnBoxing() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            // nums.add(Integer.valueOf(i));
            nums.add(i); // AutoBoxing 自动的将值类型装箱成对应的引用类型
        }
        // int findValue = nums.get(5).intValue();
        int value = nums.get(5); // Unboxing 自动的将引用类型拆箱成值类型

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Long g = 3L;
        System.out.println(c == d);          // ==     TODO: 一般用于判断原生类型的大小比较, 而使用equals()来比较引用类型
        System.out.println(c == a + b);      // ==     在遇到运算符号的情况下会自动的装箱 !!
        System.out.println(g == a + b);      // ==     相等运算符判断只是确定值的大小
        System.out.println(c.equals(a + b)); // True   运算之后成Integer类型，匹配
        System.out.println(g.equals(a + b)); // False  equals()方法不仅要匹配类型(instanceof所属类型关系)，还有判断值是否相等
    }
}



