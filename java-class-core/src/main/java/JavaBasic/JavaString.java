package JavaBasic;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

// TODO. 不要使用String来代替基本类型
// 1. String字符串本质上是一个16 bits unicode characters码值的数组
// 2. 字符串具有Immutable不可变性, 如果要改变, 则需要创建新String

// String 模仿基本类型的行为(独立操作值)，但本身是引用类型(A Class)
// 1. String默认值是null, 对应Unicode码值，表示UTF-16编码方案
// 2. String不可变值, 在创建之后不可更改, 所有值的更改都会重新创建一个对象
// 3. String能够存储字符长度只受到内存和Integer.MAX_VALUE值的大小限制
public class JavaString {

    // Char字符对应到Unicode码值，char可以转换int值比较计算
    // Int值可转换成对于的char值，再转换成字符串
    public static void main2(String[] args) {
        String value = "asa";
        System.out.println((int) value.charAt(0));

        String myStr = "\u00A9 2019";
        System.out.println(myStr);

        String str1 = String.valueOf((char)3);
        String str2 = String.valueOf((char)34);
    }

    private void testString() {
        // "ABC" 就是class String的一个实例对象, 在创建后不能改变
        String s1 = "ABC";
        String s2 = s1;
        s2 = "Check";
        System.out.println(s1); // s1 = "ABC"
        System.out.println(s2); // s2 = "Check"

        String myString = "this is a string" + ", and more"; // 字符串的链接
        myString += 10 + 120.6d;   // 自动转成String进行链接

        String str = new String("test"); // 首次会在线程池和堆上创建对象
        // 将字符串按照指定的方案"解码"成byte数组，然后再按照指定的方案"编码"成String
        byte[] oldBytes = str.getBytes(StandardCharsets.US_ASCII);
        String newStr = new String(oldBytes, StandardCharsets.UTF_8);

        // 在string字符串拷贝到字符数组中，可以指定要拷贝的偏移量
        char[] input = new char[str.length()];
        str.getChars(0, str.length(), input, 0);
    }

    // TODO: String Const Pool 字符串常量池(JVM Heap堆中), 记录首次出现的实例引用
    // JVM uses string pools for allocation of string objects
    private void testStringConstantPool() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);          // false 比较的两个引用是不同的
        System.out.println(s1.equals(s2));     // true  不可变类型的对象(包含)值相同

        System.out.println(s1 == s3);          // true  两个引用所引用的是常量池中相同的字符串对象

        System.out.println(s1 == s1.intern()); // true  .intern()返回的是同一个引用
        // 当调用intern方法时，如果字符串池中具有equal的字符串对象，则返回那个对象的引用
        // 反之添加新字符串对象，然后其引用
    }

    // TODO. String Native APIs 原生API的使用
    // s1.subString(0, 10);
    // s1.indexOf('o');
    // s1.lastIndexOf('o');
    // s1.substring(6, 11);                       都会造成O(n)的时间复杂度, 不可以忽略
    // String.copyValueOf(char[] data);           直接通过取值来构建
    // new String(charArray);                     直接通过字符数组构建String
    // new StringBuilder(s).reverse().toString(); 使用StringBuilder来反转字符串
    public static void main(String[] args) {
        // TODO. 将变量写在.equals()方法前可能抛出NullPointerException异常
        String nullStr = null;
        if (nullStr.equalsIgnoreCase("ok")) {
            System.out.println("ok");
        }

        // 如何截取字符中执行两个特殊字符之间的子字符串
        String value = "this is a test";
        String subStr = value.substring(value.indexOf("[") + 1, value.indexOf("]"));

        // StringIndexOutOfBoundsException 由于无法截取子字符串而造成的异常
        String value1 = "item check";
        String subStr1 = value.substring(0, value.lastIndexOf(","));

        // String字符串的聚合操作
        String multiLines = "this is first line \n The second line \n The end";
        Stream<String> streams = multiLines.lines();
    }
}
