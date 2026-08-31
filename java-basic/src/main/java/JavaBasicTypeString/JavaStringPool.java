package JavaBasicTypeString;

// TODO: JVM 使用字符串池(Heap堆中)来分配字符串对象
// JVM uses string pools for allocation of string objects
public class JavaStringPool {

    public static void main(String[] args) {
        String s1 = "Programming";

        // 当调用intern方法时:
        // 如果字符串池中具有equal的字符串对象，则返回那个对象的引用
        // 反之添加新字符串对象，然后其引用
        System.out.println(s1 == s1.intern()); // true  .intern()返回的是同一个引用
    }
}
