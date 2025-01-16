package JavaBasicCharacter;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

// TODO. 不要使用String来代替基本类型
// 1. String字符串本质上是一个16 bits unicode characters码值的数组
// 2. 字符串具有Immutable不可变性, 如果要改变, 则需要创建新String
// 3. TODO: 注意String Concatenation级联造成巨大的时间复杂度 !!
//
// String 模仿基本类型的行为(独立操作值)，但本身是引用类型(A Class)
// 1. String默认值null, 对应Unicode码值，表示UTF-16编码方案
// 2. String是不可变值, 在创建之后不可更改, 所有值的更改都会重新创建一个对象
// 3. String能够存储字符长度只受到内存和Integer.MAX_VALUE值的大小限制
public class JavaString {

    public static void main(String[] args) {
        String s1 = "ABC";
        String s2 = s1;
        s2 = "Check";
        System.out.println(s1); // s1 = "ABC"
        System.out.println(s2); // s2 = "Check"

        String myString = "string" + " more"; // 字符串的链接
        myString += 10 + 120.6d;   // 自动转成String进行链接

        // 首次会在线程池和堆上创建对象
        String str = new String("test");

        // 判断字符串的前缀字符串
        boolean isStartWith = s2.startsWith("Ch");

        // 使用char来创建字符串
        String strC = String.valueOf('C');
        
        // 拷贝重复字符串，效率不如StringBuilder !!
        String strRepeat = strC.repeat(5);
    }

    private void testStringApi(String str) {
        // 将字符串按照指定的方案"解码"成byte数组，然后再按照指定的方案"编码"成String
        byte[] oldBytes = str.getBytes(StandardCharsets.US_ASCII);
        String newStr = new String(oldBytes, StandardCharsets.UTF_8);

        // 在string字符串拷贝到字符数组中，可以指定要拷贝的偏移量
        char[] input = new char[str.length()];
        str.getChars(0, str.length(), input, 0);

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
