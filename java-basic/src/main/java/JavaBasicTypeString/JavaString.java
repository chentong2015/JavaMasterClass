package JavaBasicTypeString;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class JavaString {

    public static void main(String[] args) {
        // TODO. null空能够强制转换成String对象但无法调用API
        String value = null; // 默认值
        String value2 = (String) value;
        System.out.println(value2.toString()); // NullPointerException

        // TODO. 初始化字符串类型对象的方式
        String s1 = "ABC";
        String s2 = s1;
        String str = new String("test");
        String strC = String.valueOf('C');
        String strRepeat = strC.repeat(5);

        String myString = "string" + " more"; // 字符串的链接
        myString += 10 + 120.6d;   // 自动转成String进行链接
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
