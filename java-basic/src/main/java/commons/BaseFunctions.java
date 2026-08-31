package commons;

// TODO. 方法调用仅支持"By Value"值传递, 不支持"By Reference"引用传递
// Java无法在调用函数时将实际参数地址直接传递到函数
// Java不能直接获取到“实际参数的地址”, 使用引用去操作其指向地址的对象
public class BaseFunctions {

    // Java不支持为参数提供默认值
    // public void testFunctionDefaultParameter(String value = "default") { }

    // 值类型，直接Copy副本值传递给方法中的临时变量
    public static void testByValue(int value) {
        value += 10;
    }

    // String作为不可变类型，任何修改都将创建新的对象，对调用者不造成影响
    public static void testByString(String str) {
        str += "test";
    }

    // 引用类型，直接Copy对应的"引用副本值"传递给方法中的临时变量
    public static void testByClass(StringBuilder stringBuilder) {
        stringBuilder.append("t");
    }

    public static void main(String[] args) {
        int value = 10;
        System.out.println(value);
        testByValue(value);
        System.out.println(value);

        String str = "string";
        System.out.println(str);
        testByString(str);
        System.out.println(str);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("t");
        System.out.println(stringBuilder);
        testByClass(stringBuilder);
        System.out.println(stringBuilder);
    }
}