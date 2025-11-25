package JavaBasicTypeString.builder;

// TODO. 清空StringBuilder数据的两种方式
// 1. 将长度设置成0 -> For performance-critical code
//    重用旧对象开辟的buffer内存空间(默认维持旧buffer size，调用trimToSize进行优化)
//    没有GC操作，当Buffer Size过大时，重用buffer空间将更加高效
//
// 2. 重建StringBuilder对象 -> For known expected capacity (short buffer)
//    旧对象开开辟的内存空间将被GC清除
public class JavaStringBuilderClean {

    public static void main(String[] args) {
        String str = "the value";
        StringBuilder stringBuilder = new StringBuilder(str);

        stringBuilder.setLength(0);

        // 根据当前存储的字符序列来优化Buffer空间的Size，释放多余空间
        // 当需要利用原始的Buffer空间的Size时，不建议做空间优化
        // Attempts to reduce storage used for the character sequence.
        // If the buffer is larger than necessary to hold its current sequence of characters,
        // then it may be resized to become more space efficient.
        stringBuilder.trimToSize();


        stringBuilder = new StringBuilder();
        stringBuilder.append("a");
    }
}
