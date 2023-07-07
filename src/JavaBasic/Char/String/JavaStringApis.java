package JavaBasic.Char.String;

import java.util.stream.Stream;

// 测试Java String原生API
public class JavaStringApis {

    // String -> Steam: 返回支持使用聚合操作的A sequence of elements
    private void testStringStreams() {
        String multiLines = "this is first line \n The second line \n The end";
        Stream<String> streams = multiLines.lines();
    }

    public void testStringMatches() {
        // "正则匹配"字符串
        String value = "this is a test";
        boolean isMatch = value.matches(".*");

        // java.lang.NullPointerException
        // Null空字符串是不能调用String类的方法
        String nullStr = null;
        if (nullStr.equalsIgnoreCase("ok")) {
            System.out.println("ok");
        }
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

    // replaceFirst() 只替换第一次出现的匹配
    private void testStringSplitAndReplace() {
        String value = "<joined-subclass name=\"client.test.Cash\" table=\"t_cash\">";
        String className = value.split("name=")[1].split("\"")[1];

        String newItem = "entity-name=\"new.item\"";
        String result = value.replace("name=", newItem + " name=");
        String result2 = value.replaceFirst("name=", "entity-name=");
    }

    // 如何截取字符中执行两个特殊字符之间的子字符串
    private void getSubstring(String value) {
        if (value.contains("[") && value.contains("]")) {
            String subStr = value.substring(value.indexOf("[") + 1, value.indexOf("]"));
        }
    }
}
