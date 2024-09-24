package JavaBasic;

public class Base2TypesInteger {

    public static void testBasicVariables() {
        int myInt = 100;  // Java默认将字面值处理成int
        int xx = 10;      // Java没有无符号的int, 最高位定位符号位，确定了最大和最小值
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

    // TODO. Int整型数据转换Binary二进制的形式
    public static void main(String[] args) {
        int t = 15;
        int tt = t & 0xFF;
        System.out.println(t & 0xFF);
        System.out.println(Integer.toBinaryString(tt));

        int x = 922342959;
        display(x >>> 24);
        display(x >>> 16);
        display(x >>> 8);
        display(x >>> 1);
    }

    private static void display(int x) {
        // 二进制的高位补0
        String all = String.format("%32s", Integer.toBinaryString(x)).replace(" ", "0");
        String colouredBinary =  all.substring(0, 24) + all.substring(24);

        int y = x & 0xFF;
        // 0000 0000 1111 1111 = 0x00FF
        // Integer.toBinaryString(y) 将int转成bits输出，格式成%8s
        String formatString = "%10d and 0xFF is %8s \t %10d is ";
        String output = String.format(formatString, y, Integer.toBinaryString(y), x) + colouredBinary;
        System.out.println(output);
    }
}
