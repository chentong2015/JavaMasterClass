package JavaBasicTypes.StringClass;

public class JavaStringEquals {

    // TODO. 注意字符串的两种比较方式
    private static void compareString() {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);      // false 比较的两个引用是不同的
        System.out.println(s1.equals(s2)); // true  不可变类型的对象(包含)值相同
        System.out.println(s1 == s3);      // true  两个引用所引用的是常量池中相同的字符串对象
    }

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

        // TODO. null空对象不能调用.equals()方法 !!
        String nullStr = null;
        if (nullStr.equalsIgnoreCase("ok")) {
            System.out.println("ok");
        }
    }
}
