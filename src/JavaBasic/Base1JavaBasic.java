package JavaBasic;

// Java不支持Tuple元组       ===> C++, C#支持(固定大小的不同类型值的集合)
// Java不支持Struct结构体    ===> C++, C#支持
// Java不支持Union联合体     ===> C++支持
public class Base1JavaBasic {

    // 类型和值的所用: 所有的值都是有类型(强类型)
    // 1. 存储在内存中的时候，有类型约束，决定存储空间的大小
    // 2. 该类型的值具有指定的操作
    // 3. 使用类型声明变量，决定和约束变量(在内存中关联关系)

    // 变量Identifier(ID在指定的Scope范围中是唯一)的声明
    // 1. 不能用保留字符keywords
    // 2. 开头字符: 非数字 + 后续字符: 非标点符号，除了_和$
    // 3. 大概分为4种变量: 类型成员变量, 局部变量, 参数变量, 对象的引用变量

    // TODO: Java基本数据类型，所有类型都是继承自java.long.Object类型
    // 1. Primitive type 原生类型 (value type直接存值的类型)
    // 2. Reference type 引用类型 (String, List, Class, Interface, Array数组(支持协变, 转换成它的基类))
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
        double doubleNum = Double.parseDouble("2015.06");
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



