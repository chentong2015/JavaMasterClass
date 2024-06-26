package JavaBasic.CharString;

import java.util.stream.Stream;

// Java String Native APIs
// s1.subString(0, 10);
// s1.indexOf('o');
// s1.lastIndexOf('o');
// s1.substring(6, 11);                       都会造成O(n)的时间复杂度, 不可以忽略
// String.copyValueOf(char[] data);           直接通过取值来构建
// new String(charArray);                     直接通过字符数组构建String
// new StringBuilder(s).reverse().toString(); 使用StringBuilder来反转字符串
public class JavaStringApis {

    public static void main(String[] args) {
        // 判读字符串是否符合"正则表达式"
        String value = "this is a test";
        boolean isMatch = value.matches(".*");

        // TODO. 将变量写在.equals()方法前可能抛出NullPointerException异常
        String nullStr = null;
        if (nullStr.equalsIgnoreCase("ok")) {
            System.out.println("ok");
        }

        // 如何截取字符中执行两个特殊字符之间的子字符串
        String subStr = value.substring(value.indexOf("[") + 1, value.indexOf("]"));

        // String字符串的聚合操作
        String multiLines = "this is first line \n The second line \n The end";
        Stream<String> streams = multiLines.lines();
    }

    // TODO. String split & replaceAll 都可以使用正则表达式
    // 根据多种可能行来分割字符串 & 替换多种可能型的字符串
    private void testStringSplit() {
        String value = "test ,test, test , test";
        for (String item : value.split("[ ]?,[ ]?")) {
            System.out.println(item);
        }
        value = value.replaceAll("[ ]?,[ ]?", ",");
        System.out.println(value);
    }

    // TODO. replaceFirst() 只替换第一次出现的匹配
    private void testStringSplitAndReplace() {
        String value = "<joined-subclass name=\"client.test.Cash\" table=\"t_cash\">";
        String className = value.split("name=")[1].split("\"")[1];
        String newItem = "entity-name=\"new.item\"";
        String result = value.replace("name=", newItem + " name=");
        String result2 = value.replaceFirst("name=", "entity-name=");
    }

    // StringIndexOutOfBoundsException 由于无法截取子字符串而造成的异常
    public void testStringException() {
        String value = "item check";
        String subStr = value.substring(0, value.lastIndexOf(","));
    }
}
