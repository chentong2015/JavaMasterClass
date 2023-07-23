package JavaBasic.JavaCharString;

// TODO: 可变的字符串类型, 两种类型操作基本一致
// 1. StringBuilder 适用于单线程，没有冲突的程序中
// 2. StringBuffer  适用于多线程，方法都通过"synchronized"加锁
//    StringBuffer实例基本总是用于单线程中，而知悉的却是内部同步，造成不必要的过度同步 !!
public class JavaStringBuilder {

    // Immutable String - Problems & Solutions
    // TODO: String Concatenation in Java 注意String的级联造成巨大的时间复杂度 !!
    // 如何解决和更好的利用Java String不可变的特性
    // 1. 转换成charArray()字符数组
    // 2. 使用StringBuilder数据结构，支持字符串的可变性，可以追加int类型
    public void testString(int n) {
        // First allocating enough space for the new string
        // Copy the contents from the old string and append to the new string
        // 5 + 5 × 2 + 5 × 3 + … + 5 × n = O(n^2) 操作会造成二次方的时间复杂度 !!
        String s = "";
        for (int i = 0; i < n; i++) {
            s += "hello"; // 每次追加的新的字符串，都会造成额外的内存空间开辟，然后造成Copy的操作
        }

        // 用String来构建StringBuilder
        StringBuilder str = new StringBuilder(s);
        for (int i = 0; i < n; i++) {
            str.append("hello");
        }
        // 将StringBuilder转换成String
        String result = str.toString();
    }

    private void testStringBufferBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("first str");
        // 追加截取指定位置的字符串
        stringBuilder.append("second str", 0, 2);

        // 插入到指定位置，往前插入数据
        stringBuilder.insert(0, "table_name");
        stringBuilder.append(System.getProperty("line.separator"));
        stringBuilder.append("ending");
        String result = stringBuilder.toString();
    }
}
