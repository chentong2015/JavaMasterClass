package JavaBasic;

public class JavaChar {

    private final static int MY_INT = 10;

    // Char字符对应到Unicode码值，
    // Char字符可以转换int值比较计算
    // Int值可转换成对于的Char值，再转换成字符串
    public static void main(String[] args) {
        char myChar = 'D';
        char offsetChar = 'A' + 15; // 1. 使用常量进行偏移量计算, 隐式转换 !!
        char constChar = 'A' + MY_INT;

        int offset = 10;
        char convertChar = (char) ('A' + offset); // 2. 使用变量进行偏移计算，显示强制转换 !!
        char convertChar2 = (char) (65 + offset);

        String value = "asa";
        System.out.println((int) value.charAt(0));

        String str1 = String.valueOf((char)3);
        String str2 = String.valueOf((char)34);
        System.out.println(str2);
    }
}
