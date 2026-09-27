package core_types.integer;

// int整型能表示的字节长度4 bytes
public class JavaInt {

    public static void main(String[] args) {
        int xx = 100; // Java默认将字面值处理成int
        int yy = xx;
        yy = 20;
        System.out.println(xx); // xx = 10
        System.out.println(yy); // yy = 20

        int result = yy / 3;
        System.out.println(result); // 计算结果只取整数部分

        int myMinIntValue = Integer.MIN_VALUE;
        System.out.println(myMinIntValue - 1); // 最小值减1则溢出 成最大值2147483647

        int myMaxIntValue = Integer.MAX_VALUE;
        System.out.println(myMaxIntValue + 1); // 最大值加1则溢出 成最小值

        // TODO. int整型字面值的特殊表示
        int myMaxIntTest01 = 2147483647;    // 使用字面值大值
        int myMaxIntTest02 = 2_147_483_647; // 使用_来标识大数字
        int mod = (int) 1e9 + 7; // 使用e表示10的次方数
        System.out.println(mod); // 1000000007

        testFormats();
    }

    public static void testFormats() {
        int t = 15;
        System.out.println(Integer.toBinaryString(t)); // 1111 二进制形式表示

        int x = 922342959;
        System.out.println(Integer.toBinaryString(x)); // 110110111110011101011000101111

        System.out.println(Integer.toHexString(x)); // Ox36f9d62f 十六进制形式表示
    }
}
