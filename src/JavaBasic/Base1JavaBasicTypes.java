package JavaBasic;

public class Base1JavaBasicTypes {

    public static void testMain(String[] args) {
        System.out.println('3'); // 字符的字面值
        int height = 10;         // 所有的变量都有类型，存在类型的转换
        final double PI = 3.14;  // 默认浮点型是double类型
        System.out.println(height * PI);

        float f = 3.14f;         // 使用f注明float类型
        double d = 3.14f;        // d容纳的是什么的类型
        int y = 'a';             // y = 97; 根据字符对应的unicode码值来进行判断和计算
        int x = Integer.parseInt("123");
        int intNum = Integer.parseInt("2018");
        float floatNum = Float.parseFloat("1.23"); // 使用浮点型进行四则运算是精确值 !!
        double doubleNum = Double.parseDouble("2015.06"); // 使用double进行四则运算不是精确值
    }

    // 变量和内存 & 赋值操作；左值 = 右值
    public static void testMemory() {
        // 先分配指定的内存空间，变量x(标签)关联在4个字节上，最低的字节上
        // 0000 0000
        // 0000 0000
        // 0000 0000
        // 0000 1010 从低位开始存储
        int x = 10;

        // 所有引用类型的变量都分配4个字节, 用来存放内存地址(最低位置的地址 0x00F1)
        // 0000 0000
        // 0000 0000
        // 0000 0000
        // 1111 0001 => F1

        // Student student = new Student();
        // student引用变量存放引用对象的实际内存地址 @14ae5a5
    }
}



