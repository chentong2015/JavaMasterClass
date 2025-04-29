package JavaBasicTypes;

// TODO: String Const Pool
// 字符串常量池(JVM Heap堆中), 记录首次出现的实例引用
// JVM uses string pools for allocation of string objects
public class JavaStringEquals {

    // TODO. 字符串比较必须使用.equals()方法，比较字符串对象的内容
    // == tests for reference equality (whether they are the same object).
    // .equals() tests for value equality (whether they contain the same data).
    public static void main(String[] args) {
        String str1 = "value";
        String str2 = "value";
        if (str1 == str2) {
            System.out.println("It's same reference");
        }
        if (str1.equals(str2)) {
            System.out.println("It's same value");
        }

        // TODO. null空对象不能调用.equals()方法
        String nullStr = null;
        if (nullStr.equalsIgnoreCase("ok")) {
            System.out.println("ok");
        }

        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);      // false 比较的两个引用是不同的
        System.out.println(s1.equals(s2)); // true  不可变类型的对象(包含)值相同
        System.out.println(s1 == s3);      // true  两个引用所引用的是常量池中相同的字符串对象

        // 当调用intern方法时:
        // 如果字符串池中具有equal的字符串对象，则返回那个对象的引用
        // 反之添加新字符串对象，然后其引用
        System.out.println(s1 == s1.intern()); // true  .intern()返回的是同一个引用
    }
}
