package main.features.java15;

public class JavaNullPointer {
    
    // NullPointerException错误信息中将会提示具体为null的变量
    // java.lang.NullPointerException: Cannot invoke "String.length()" because "value" is null
    public void testNullPointerException() {
        String value = null;
        System.out.println("ok");
        System.out.println(value.length());
    }
}
