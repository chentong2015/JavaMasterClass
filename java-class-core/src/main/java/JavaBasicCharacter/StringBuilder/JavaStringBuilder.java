package JavaBasicCharacter.StringBuilder;

// TODO: 可变的字符串类型, 两种类型操作基本一致
// 1. StringBuilder 适用于单线程，没有冲突的程序中
// 2. StringBuffer 适用于多线程，方法都通过"synchronized"加锁
//    StringBuffer 实例基本总是用于单线程中，造成不必要的过度同步 !!
public class JavaStringBuilder {

    // TODO. 使用StringBuilder来支持字符串的可变性，优化字符串级联操作
    public void testString(int n) {
        // String每次追加新字符串，都会造成额外的内存空间开辟，然后造成Copy的操作
        // First allocating enough space for the new string
        // Copy the contents from the old string and append to the new string
        // 5 + 5 × 2 + 5 × 3 + … + 5 × n = O(n^2)
        String s = "";
        for (int i = 0; i < n; i++) {
            s += "hello";
        }

        // StringBuilder直接操作字符串的拼接，优化时间和空间复杂度
        StringBuilder str = new StringBuilder(s);
        for (int i = 0; i < n; i++) {
            str.append("hello");
        }
    }

    public static void main(String[] args) {
        // 使用字符串来构建StringBuilder
        String value = "test link";
        StringBuilder stringBuilder = new StringBuilder(value);

        // TODO. 使用StringBuilder API来反转字符串
        String reversedString = stringBuilder.reverse().toString();

        stringBuilder.append("first str");
        stringBuilder.append(System.getProperty("line.separator"));

        // 追加截取指定位置的字符串
        stringBuilder.append("second str", 0, 2);
        // 插入到指定位置，往前插入数据
        stringBuilder.insert(0, "table_name");

        // 将StringBuilder转换成String
        String result = stringBuilder.toString();
    }
}
