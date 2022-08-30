package JavaBasic;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

// String 模仿基本类型的行为(彼此操作值的影响是独立的)，但是本身是引用类型 It's actually a Class
// 1. String默认值是null, it represents a string in the UTF-16 format
//    对应Unicode码值，表示UTF-16编码方案
// 2. String不可变值, 在创建之后不可更改, 所有值的更改都会重新创建一个对象
// 3. String能够存储字符长度只受到内存和Integer.MAX_VALUE值的大小限制
public class Base2JavaString {

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

    // TODO: String和BigDecimal都是不可变类型
    public void testImmutableTypeComparison(String[] args) {
        BigDecimal bigDecimal1 = new BigDecimal(1);
        BigDecimal bigDecimal2 = new BigDecimal(1);
        System.out.println(bigDecimal2 == bigDecimal1); // false 对象引用不同
        System.out.println(bigDecimal2.equals(bigDecimal1)); // ture 不可变类型的(包含)值相同
    }

    // TODO: 可变的字符串类型, 两种类型操作基本一致
    // 1. StringBuffer  适用于多线程，方法都通过"synchronized"加锁
    // 2. StringBuilder 适用于单线程，没有冲突的程序中
    private void testStringBufferBuilder() {
        StringBuilder builder = new StringBuilder();
        builder.append("first str");
        builder.append("second str");
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

    // String -> Color 设置字符串输出的不同颜色
    private void testStringColors() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";
    }
}
