package core_string;

import java.util.stream.Stream;

// TODO. String特殊的基本类型
// String模仿基本类型的行为(独立操作值)，但本身是引用类型(A Class)
// String字符串具有不可变性(Immutable)，修改时必须创建新String对象 !!
// String直接Concatenation级联造成巨大的时间复杂度 !!
public class JavaString {

    public static void main(String[] args) {
        String value1 = null; // 默认值null 容易造成异常
        String value2 = (String) value1;
        System.out.println(value2.toString()); // NullPointerException

        String s1 = "ABC";
        String s2 = s1;
        String str = new String("test");
        String strC = String.valueOf('C');

        String myString = "string" + " more"; // 字符串的链接
        myString += 10 + 120.6d;   // 自动转成String进行链接


        // 截取字符中执行两个特殊字符之间的子字符串
        String value = "this [is a tes]t";
        String subStr = value.substring(value.indexOf("[") + 1, value.indexOf("]"));

        // 截取错误的index坐标范围
        String value3 = "item check";
        String subStr1 = value3.substring(0, value3.lastIndexOf(","));

        // 字符串的聚合操作
        String multiLines = "this is first line \n The second line \n The end";
        Stream<String> streams = multiLines.lines();

        String strRepeat = str.repeat(5);
        "Java\n".repeat(25).lines().forEach(System.out::println);
    }
}
