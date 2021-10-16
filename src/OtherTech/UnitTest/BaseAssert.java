package OtherTech.UnitTest;

// Assert断言
// 一种常用的调试方式, 断言用于保证程序最基本、关键的正确性
// 启动JVM时使用-enableassertions或者-ea标记, 否则断言不会起作用
public class BaseAssert {

    // 常见的断言:
    // assertEqual(), assertNotEqual()
    // assertArrayEquals() 比较两个arrays数组是否数据完全一致 ==> assertEquals()只比较两个array是否是同一个实例
    // assertNull(), assertNotNull()
    // assertTrue(), assertFalse()
    // assertSame(), assertNotSame() 比较对象的引用，是否是同样的对象 ==> assertEquals()使用对象的equals()方法进行比较
    // assertThat()  此方法将实际值与匹配器进行比较
    // assertThrows() 指定异常的抛出
}
