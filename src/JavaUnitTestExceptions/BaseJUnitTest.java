package JavaUnitTestExceptions;

import static org.junit.jupiter.api.Assertions.*;

// 如何在一个测试Suite中统一初始化对象 ??
// 单元测试的覆盖率问题 ??

// Test后缀说明是对指定类型的测试, 与测试的类型相互分离 : Test Suite
public class BaseJUnitTest {

    // Unit test 测试的单元是方法 ==> 可以单独运行 : Test Case
    // public void testName 测试方法的声明名称可以和源类型中的方法不同 ==> 注明测试的重点
    // JUnit4 @org.junit.Test 注解表明这是一个测试方法 ==> Android开发中标注 @Test
    @org.junit.jupiter.api.Test
    public void testUnitMethod() throws Exception {
        // 未完成的测试需要注明fail()
        // 出错才会有输出的信息
        fail("This test has yet to be implemented");
    }

    // Unit Test 单元测试方法之间相互独立
    // assertEquals 一个测试最好使用一个断言
    @org.junit.jupiter.api.Test
    public void testBaseJUnit() {
        BaseJUnit baseJUnit = new BaseJUnit();
        int balance = baseJUnit.deposit(50);
        assertEquals(150, balance, 0); // 断言, 在数值比较时可以设定比较的误差
    }

    // 直接比较boolean的值
    // 在测试错误时输出指定的日志信息
    @org.junit.jupiter.api.Test
    public void testAssertBoolean() {
        BaseJUnit baseJUnit = new BaseJUnit();
        assertTrue(baseJUnit.getChecking(), "The account is not checking");
    }
}