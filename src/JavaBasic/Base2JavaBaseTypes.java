package JavaBasic;

import java.text.DecimalFormat;

// int 32, byte 8, short 16, long 64, float 32, double 64, char 16, boolean
public class Base2JavaBaseTypes {

    public static void testBasicVariables() {
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
    }

    // (浮点数在内存存储：表示成2进制 > 2进制科学计数法 > 填32位bit: 偏移 + 零舍1入)
    // TODO. 不要使用float和double来完成需要精确答案的任务
    // float -> Float 单精度浮点类型 32 bits ==> 4 bytes
    private void testFloatDouble() {

        float myMinFloatValue = Float.MIN_VALUE;
        float myFloatTestValue = 5.25f; // 纯5.25的字面值 会被视为是double 不能自动处理成float
        float myFloatValue = 5f; // 字面值5视为int 自动转成float没有问题 ==> 但是最好是指明字面值的类型
        System.out.println("float value =" + myFloatValue); // 5.0
        System.out.println("float value =" + myFloatValue / 2f); // 2.5
        System.out.println("float value =" + myFloatValue / 3f); // 1.6666666
    }

    // TODO: 如果不添加f或d的后缀，java默认处理浮点类型成double类型
    // double -> Double 双精度浮点类型 64 bits ==> 8 bytes
    private void testJavaDouble() {
        double myMinDoubleValue = Double.MIN_VALUE;
        double myDoubleTestValue = 5.25d; // 5.25类型默认成立， 但是推荐使用d修饰
        double pi = 3.1415926d;
        double doubleNumber = 3_000_000.4_567_890d;        // 同样支持下划线的写法 = 3000000.4567890

        double myDoubleValue = 5d;
        System.out.println("double value =" + myDoubleValue);      // 5.0
        System.out.println("double value =" + myDoubleValue / 2d); // 2.5
        System.out.println("double value =" + myDoubleValue / 3d); // 1.666666666...7
        System.out.println("double value =" + 5.00d / 3.00d);      // 1.666666666...7

        // 格式化double的数值, 输出指定格式的字符串形式
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        double input = 3.14159265359;
        System.out.println("double : " + decimalFormat.format(input)); //3.14
        String valueFormatted = String.format("%.2f", pi);
    }
}
