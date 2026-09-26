package core_string.string;

// TODO. 字符串比较一定要用.equals()方法，比较字符串对象的内容
// ==        tests for reference equality (whether they are the same object).
// .equals() tests for value equality (whether they contain the same data).
public class JavaStringEquals {

    public static void main(String[] args) {
        String s1 = "Programming";
        String s2 = new String("Programming"); // 创建新对象并返回新引用

        System.out.println(s1 == s2);      // false 两个引用不同, 即使存储的字符串值相同 !!
        System.out.println(s1.equals(s2)); // true  两个字符串对象的(包含)值相同 !!
    }

    // TODO: String类内部维护着一个字符串池，该池初始为空
    // JVM使用字符串常量池(String Pool)来复用字符串对象, 存放在Heap堆内存中
    // s.intern()返回字符串池中和“s相等值”的字符串, 或将s字符串添加到池中再返回该对象的引用
    private static void testStringPool() {
        String s11 = "Programming";
        String s22 = "Programming";

        System.out.println(s11 == s22);     // true  两个引用相同, 都引用常量池中相同字符串对象

        System.out.println(s11 == s11.intern()); // true 当前对象和池中对象两个引用一定相同

        System.out.println(s11.intern() == s22.intern()); // true 返回常量池中“相等值”对象的引用

        System.out.println(s11.equals(s22)); // true 和上面通过intern()判断引用一致
    }
}
