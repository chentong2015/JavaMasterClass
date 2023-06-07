package JavaBasic.Char.String;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

// String 模仿基本类型的行为(彼此操作值的影响是独立的)，但是本身是引用类型(A Class)
// 1. String默认值是null, 对应Unicode码值，表示UTF-16编码方案
// 2. String不可变值, 在创建之后不可更改, 所有值的更改都会重新创建一个对象
// 3. String能够存储字符长度只受到内存和Integer.MAX_VALUE值的大小限制

// TODO. 不要使用String来代替基本类型，枚举类型，聚会类型(字符串中融合多种属性)
public class JavaString {

    private void testString() {
        // "ABC" 就是class String的一个实例对象, 在创建后不能改变
        String s1 = "ABC";
        String s2 = s1;
        s2 = "Check";
        System.out.println(s1); // s1 = "ABC"
        System.out.println(s2); // s2 = "Check"
        String myString = "this is a string" + ", and more"; // 字符串的链接
        String[] array = myString.split(" ");          // 切割字符串
        myString += "\u00A9 2019"; // 直接在字符串中使用 unicode码值
        myString += 10 + 120.6d;   // 自动转成String进行链接

        String str = new String("test"); // 首次会在线程池和堆上创建对象
        String oldStr = "old String";
        // 将字符串按照指定的方案"解码"成byte数组，然后再按照指定的方案"编码"成String
        String newStr = new String(oldStr.getBytes(StandardCharsets.US_ASCII), StandardCharsets.UTF_8);
    }

    // TODO: String Const Pool字符串常量池(Java堆中, 记录首次出现的实例引用)
    // JVM uses string pools for allocation of string objects
    // 当调用intern方法时，如果字符串池中具有equal的字符串对象，则返回那个对象的引用，反之添加新字符串对象，然后其引用
    private void testStringConstantPool() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);          // false 比较的两个引用是不同的
        System.out.println(s1.equals(s2));     // true  不可变类型的对象(包含)值相同

        System.out.println(s1 == s3);          // true  两个引用所引用的是常量池中相同的字符串对象
        System.out.println(s1 == s1.intern()); // true  .intern()返回的是同一个引用
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
    private void test() {
        String value = "test ,test, test , test";
        for (String item : value.split("[ ]?,[ ]?")) {
            System.out.println(item);
        }

        value = value.replaceAll("[ ]?,[ ]?", ",");
        System.out.println(value);
    }

    // TODO. String format格式化替换%s %d, 支持多个替换
    private void testStringFormat() {
        String query = "SELECT FROM %s entity where entity.name = '%s'";
        String entityName = "MyEntityClass";
        String result = String.format(query, entityName, "value");

        String str = String.format("Post comment %d:%d", 1, 10);
    }

    // String -> Steam: 返回支持使用聚合操作的A sequence of elements
    private void testStringStreams() {
        String multiLines = "this is first line \n The second line \n The end";
        Stream<String> streams = multiLines.lines();
    }

    // replaceFirst() 只替换第一次出现的匹配
    private void testStringSplitAndReplace() {
        String value = "<joined-subclass name=\"client.test.Cash\" table=\"t_cash\">";
        String className = value.split("name=")[1].split("\"")[1];

        String newItem = "entity-name=\"new.item\"";
        String result = value.replace("name=", newItem + " name=");
        String result2 = value.replaceFirst("name=", "entity-name=");
    }
}
