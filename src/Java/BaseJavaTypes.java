package Java;

import java.util.ArrayList;
import java.util.List;

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
     *
     * Pascal命名法 -> object pascal团队 -> 微软C#语言的发展: @开头 var === auto (C++)
     * Camel驼峰命名法 -> java
     * 全大写 -> MIN_VALUE 一般用来声明常量 Static final
     *    <1> 运算起来更加快
     *    <2> 常数值在修改的时候很麻烦，每一个位置都需要修改
     *
     * 基本数据类型
     * 1. Reference type引用类型                              >>> class, interface, array数组 !!!!!
     * 2. Primitive type 8种基本类型 (value type直接存值的类型)  >>> 整数类型 (char, byte, int,,,), 浮点类型(float, double)
     * 3. String 特殊类型 !!!
     * 以上两种类型在赋值的时候，获得的均是(变量原始存储的"值")值的一份copy !!
     *
     * Java中没有联合体（union）和结构体（struct）概念 !!
     */

    /**
     * @param args
     */
    public static void testMain(String[] args) {
        System.out.println('3'); // 字符的字面值

        int height = 10; // 所有的变量都有类型，存在类型的转换
        final double PI = 3.14;
        System.out.println(height * PI); // 直接将PI替换成值

        float f = 3.14f; // 3.14是double类型的; 区别float
        double d = 3.14f; // d容纳的是什么的类型
        int y = 'a'; // y = 97; 可以使用字符进行计算
        int x = Integer.parseInt("123"); // 使用类型的静态方法来进行转换
    }

    /**
     * 变量大家族 ===> 根据不同的范围，选择不同的类型，优化程序的性能
     * 1. 类型成员变量
     * 2. 局部变量/本地变量
     * 3. 参数
     * 4. 类型实例的引用
     */
    public static void testVariable(int a, String name) { // 注意参数传递时的类型特点
        // 左边值类型，右边引用类型 (Wrapper值类型的包装器)
        int myInt = 100;             // Java默认将字面值处理成int !!!
        int xx = 10;
        int yy = xx;
        yy = 20;
        System.out.println(xx); // xx = 10
        System.out.println(yy); // yy = 20

        int myMinIntValue = Integer.MIN_VALUE; // 确定Primitive type值的范围; 带符号的整数值
        int myNewMinIntValue = (myMinIntValue / 2); // 后面这个值会被处理成int

        int myMaxIntValue = Integer.MAX_VALUE; // MAX_VALUE + 1 => MIN_VALUE 这里的值出现了溢出，转成最小值
        // 对于最值的操作，会造成溢出的情况，但是不会直接抛出异常 !!
        // OverFlow
        // UnderFlow
        int myMaxIntTest01 = 2147483647; // 使用字面值 可以检测出是否赋值过大 !!
        int myMaxIntTest02 = 2_147_483_647; // 可以使用_ 方便阅读 !! Java 7之后支持
        Integer x = 100; // int类型对应的包装器，模仿int的行为; 但是Copy的是对象 ==> 与引用类型存在区别 !!!!
        Integer y = x;
        y = 200;
        System.out.println(x); // x = 100
        System.out.println(y); // y = 200


        // byte -> Byte 1 byte
        byte myMinByteValue = Byte.MIN_VALUE;
        // 类型装换 处理成byte
        byte myNewByteValue = (byte) (myMinByteValue / 2); // 类型的问题，不是值的范围问题: (myMinByteValue / 2)计算表达式是byte类型 !!!!!

        // short -> Short 2 bytes
        short myShortMinValue = Short.MIN_VALUE;
        short myNewMinShortValue = (short) (myShortMinValue / 2);
        short bigShortLiteralValue = 32767; // 后面的字面值会被视为是int，然后检测是否满足要转换成的类型值的范围 !!

        // long -> Long 8 bytes
        long myLongValue = 100L; // 不写L 会被自动的处理成int，然后隐式转long ==> 但是提供的int的值必须在有效的范围 !!
        long myLongMinValue = Long.MIN_VALUE;


        // float -> Float 单精度浮点类型 32 bits ==> 4 bytes  ===> (浮点数在内存存储：表示成2进制 > 2进制科学计数法 > 填32位bit: 偏移 + 零舍1入)
        float myMinFloatValue = Float.MIN_VALUE;
        float myFloatTestValue = 5.25f; // 纯5.25的字面值 会被视为是double 不能自动处理成float
        float myFloatValue = 5f; // 字面值5视为int 自动转成float没有问题 ==> 但是最好是指明字面值的类型
        System.out.println("Min float value =" + myFloatValue); // 5.0
        System.out.println("Min float value =" + myFloatValue / 2f); // 2.5
        System.out.println("Min float value =" + myFloatValue / 3f); // 1.6666666

        // double -> Double 双精度浮点类型 64 bits ==> 8 bytes  (推荐使用 ==> java 默认处理的浮点类型 如果不添加f或d的后缀) !!!
        double myMinDoubleValue = Double.MIN_VALUE;
        double myDoubleTestValue = 5.25d; // 5.25类型默认成立， 但是推荐使用d修饰
        double myDoubleValue = 5d;
        System.out.println("My double value=" + myDoubleValue); // 5.0
        System.out.println("My double value=" + myDoubleValue / 2d); // 2.5
        System.out.println("My double value=" + myDoubleValue / 3d); // 1.666666666...7
        System.out.println("My double value=" + 5.00d / 3.00d); // 1.666666666...7
        double pi = 3.1415926d;
        double doubleNumber = 3_000_000.4_567_890d; // 同样支持下划线的写法 = 3000000.4567890
        String valueFormatted = String.format("%.2f", pi); // 对double的输出进行格式化


        // char -> Character 16 bits ==> 考虑存储16位的"Unicode编码表 0000-0FFF(可编65536字符)"
        char myChar = 'D';
        char offsetChar = 'A' + 15; // 将整数转成对应的char字符, 根据编码表的偏移量计算, 隐式转换 !!!
        char myUnicodeChar = '\u0044';
        char myCopyRightUnicodeChar = '\u00A9'; //0x00A9

        // boolean -> Boolean
        boolean myTureBooleanValue = true;


        // String 模仿基本类型的行为(彼此操作值的影响是独立的)，但是本身是引用类型 !!!! ===> It's actually a Class
        // String 的默认值是null !!
        // 1. String是不可变值的, 在创建之后不可更改  ==> 所有值的更改都会重新创建一个对象String  !!!
        // 2. String能够存储的字符长度 只收到内存和Integer.MAX_VALUE值的大小限制 !!!
        String s1 = "ABC";
        String s2 = s1;
        s2 = "Check";
        System.out.println(s1); // s1 = "ABC"
        System.out.println(s2); // s2 = "Check"
        String myString = "this is a string" + ", and more"; // 字符串的链接
        String[] array = myString.split(" "); // 切割字符串
        myString += "\u00A9 2019"; // 直接在字符串中使用 unicode码值
        myString += 10 + 120.6d; // 自动转成String进行链接

        // StringBuilder 可变的字符串
        StringBuilder builder = new StringBuilder();
        builder.append("first str");
        builder.append(",");
        builder.append("end str");
    }

    /**
     * 变量和内存
     * 赋值操作；左值 = 右值
     */
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
        Integer integer = new Integer(25);
        Integer integer1 = 25; // 隐藏的装箱的功能 !!!
        int myInt = integer; // 隐藏的自动插箱功能  !!!

        List<Integer> testIntList = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            // testIntList.add(Integer.valueOf(i));
            testIntList.add(i); // AutoBoxing 自动的将值类型装箱成对应的引用类型
        }
        // int findValue = testIntList.get(5).intValue();
        int value = testIntList.get(5); // Unboxing 自动的将引用类型拆箱成值类型
    }

    public static void testParsingValuesFormString() {
        String numberAsString = "2018";
        int intNum = Integer.parseInt(numberAsString);

        String doubleAsString = "2018.10";
        double doubleNum = Double.parseDouble(doubleAsString);
    }
}



