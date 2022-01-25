package JavaBasic;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Base2JavaBaseTypes {

    private final static int MY_INT = 10;

    // int 32, byte 8, short 16, long 64, float 32, double 64, char 16, boolean
    public static void test8BasicVariables(int a, String name) {
        // 左边值类型，右边引用类型 (Wrapper值类型的包装器)
        int myInt = 100;  // Java默认将字面值处理成int !!!
        int xx = 10;      // Java没有无符号的integer type, 最高位定位符号位，确定了最大和最小值
        int yy = xx;
        yy = 20;
        System.out.println(xx); // xx = 10
        System.out.println(yy); // yy = 20

        int myMinIntValue = Integer.MIN_VALUE;    // 确定Primitive type值的范围; 带符号的整数值
        int myNewMinIntValue = myMinIntValue / 2; // 后面这个值会被处理成int
        int myMaxIntValue = Integer.MAX_VALUE;    // MAX_VALUE + 1 => MIN_VALUE 这里的值出现了溢出，转成最小值

        // 对于最值的操作，会造成溢出的情况，但是不会直接抛出异常 OverFlow & UnderFlow
        int myMaxIntTest01 = 2147483647; // 使用字面值 可以检测出是否赋值过大 !!
        int myMaxIntTest02 = 2_147_483_647; // Java 7之后支持使用_来标识大数字

        // TODO: 指定类型的包装器(包装类), 模仿int的行为, 但是Copy的是对象
        //       Java不支持原始类型(Primitive Type)的泛型, int不自动装箱就无法转型为Object
        //       因为在类型擦除之后没有办法插入强制类型转换，包装类型在Java泛型的实现中做强制类型转换使用
        Integer x = 100;
        Integer y = x;
        y = 200; // new Integer(200) 这里自动装箱构建新的对象，返回引用给变量
        System.out.println(x); // x = 100
        System.out.println(y); // y = 200


        // byte -> Byte 1 byte 范围是
        byte myMinByteValue = Byte.MIN_VALUE;
        // 类型装换 处理成byte
        byte myNewByteValue = (byte) (myMinByteValue / 2); // 运算之后结果成int类型，需要强制转换

        // short -> Short 2 bytes
        short myShortMinValue = Short.MIN_VALUE;
        myShortMinValue /= 2;                           // 隐含的强制类型转换 !!
        short newValue = (short) (myShortMinValue / 2); // 必须显示的添加类型转换
        short bigShortLiteralValue = 32767; // 后面的字面值会被视为是int，然后检测是否满足要转换成的类型值的范围

        // long -> Long 8 bytes
        long myLongValue = 100L; // 不写L 会被自动的处理成int，然后隐式转long ==> 但是提供的int的值必须在有效的范围
        long myLongMinValue = Long.MIN_VALUE;


        // float -> Float 单精度浮点类型 32 bits ==> 4 bytes
        // ===> (浮点数在内存存储：表示成2进制 > 2进制科学计数法 > 填32位bit: 偏移 + 零舍1入)
        float myMinFloatValue = Float.MIN_VALUE;
        float myFloatTestValue = 5.25f; // 纯5.25的字面值 会被视为是double 不能自动处理成float
        float myFloatValue = 5f; // 字面值5视为int 自动转成float没有问题 ==> 但是最好是指明字面值的类型
        System.out.println("float value =" + myFloatValue); // 5.0
        System.out.println("float value =" + myFloatValue / 2f); // 2.5
        System.out.println("float value =" + myFloatValue / 3f); // 1.6666666


        // TODO: 如果不添加f或d的后缀，java默认处理浮点类型成double类型
        // double -> Double 双精度浮点类型 64 bits ==> 8 bytes
        double myMinDoubleValue = Double.MIN_VALUE;
        double myDoubleTestValue = 5.25d; // 5.25类型默认成立， 但是推荐使用d修饰
        double myDoubleValue = 5d;
        System.out.println("double value =" + myDoubleValue);      // 5.0
        System.out.println("double value =" + myDoubleValue / 2d); // 2.5
        System.out.println("double value =" + myDoubleValue / 3d); // 1.666666666...7
        System.out.println("double value =" + 5.00d / 3.00d);      // 1.666666666...7
        double pi = 3.1415926d;
        double doubleNumber = 3_000_000.4_567_890d;        // 同样支持下划线的写法 = 3000000.4567890
        String valueFormatted = String.format("%.2f", pi); // 对double的输出进行格式化


        // char -> Character 16 bits 对应16位的"Unicode编码表 0000-0FFF(可编65536字符)"
        //      -> ASCII 8 bits 该编码方案取的是低八位的字符码对应关系表
        char myChar = 'D';
        char offsetChar = 'A' + 15;               // 1. 使用常量进行偏移量计算, 隐式转换 !!
        char constChar = 'A' + MY_INT;
        int offset = 10;
        char convertChar = (char) ('A' + offset); // 2. 使用变量进行偏移计算，显示强制转换 !!
        char convertChar2 = (char) (65 + offset);
        char myUnicodeChar = '\u0044';            // \\u 表示码位的表示
        char myCopyRightUnicodeChar = '\u00A9';   // 0x00A9 十六进制写法

        // boolean -> Boolean
        boolean myTureBooleanValue = true;
    }

    // OptionalInt int值的一个容器对象
    private void testOptionalInt() {
        // A container object which may or may not contain an int value.
        // If a value is present, isPresent() returns true.
        // If no value is present, the object is considered empty and isPresent() returns false.
        OptionalInt sum = OptionalInt.of(0);
        OptionalInt total = IntStream.of(1, 2).reduce(Integer::sum);
    }
}
