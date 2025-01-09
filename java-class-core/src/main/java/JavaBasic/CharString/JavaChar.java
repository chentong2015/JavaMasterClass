package JavaBasic.CharString;

public class JavaChar {

    private final static int MY_INT = 10;

    // Char字符对应到Unicode码值，
    // Char字符可以转换int值比较计算
    // Int值可转换成对于的Char值，再转换成字符串
    public static void main(String[] args) {
        char myChar = 'D';

        // 1. 使用常量进行偏移量计算, 隐式转换
        char offsetChar = 'A' + 15;
        char constChar = 'A' + MY_INT;

        // 2. 使用变量进行偏移计算，显式制转换
        int offset = 10;
        char convertChar = (char) ('A' + offset);
        char convertChar2 = (char) (65 + offset);

        String value = "asa";
        System.out.println((int) value.charAt(0));

        String str1 = String.valueOf((char)3);
        String str2 = String.valueOf((char)34);
        System.out.println(str2);

        // TODO. Character API判断单个字符的类型
        String str = "1test";
        boolean isDigit = Character.isDigit(str.charAt(0));
    }
}
