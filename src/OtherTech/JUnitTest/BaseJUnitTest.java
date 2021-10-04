package OtherTech.JUnitTest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// For JUnit4: @org.junit.Before / .BeforeClass / @org.junit.Test / .After / .AfterClass
// Test后缀说明是对指定类型的测试, 与测试类型相互分离: Test Suite
// Unit JavaUnitTestExceptions.test 测试的单元是方法, 可单独运行 : Test Case
// Unit Test 单元测试方法之间相互独立, 一个测试最好使用一个断言: 最好少的断言
public class BaseJUnitTest {

    private static int count; // Test class 共享的数据
    private BaseJUnit baseJUnit;

    // BeforeEach 在测试每一个方法之前，都执行 (设置统一配置的信息) ==> 声明成static
    @BeforeAll
    public static void init() {
        System.out.println("Count = " + count++);
        System.out.println("Before any JavaUnitTestExceptions.test cases"); // Console显示的输出结果可能滞后，由IO控制
    }

    // BeforeAll 在测试所有的方法之前，执行一次
    @BeforeEach
    public void setUp() {
        baseJUnit = new BaseJUnit();
        System.out.println("Setup JavaUnitTestExceptions.test case");
    }

    // public void methodName 方法名称可与测试源类型中的不同 ==> 源方法必须是public的才能测试
    @Test
    public void testBaseJUnit() {
        int balance = baseJUnit.deposit(50);
        assertEquals(150, balance, 0); // 断言, 在数值比较时可以设定比较的误差
    }

    // 在测试错误时输出Message指定的日志信息
    @Test
    public void testAssertBoolean() {
        assertTrue(baseJUnit.getChecking(), "The account is not checking");
    }

    // 未完成的测试需要注明fail()
    @Test
    public void testFail() {
        fail("To be implemented");
    }

    // 测试抛出指定的异常: 允许指定的Exception  ==> For JUnit4 (expected = IllegalArgumentException.class)
    @Test
    public void testMethodException() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            baseJUnit.testException(false);
            fail("Should have thrown an Exception");
        });
    }

    // 指定重复测试的次数
    @RepeatedTest(5)
    public void repeatedTest() {

    }

    // 在每个Test case之后执行清理
    @AfterEach
    public void cleanUp() {
        System.out.println("Count = " + count++);
    }

    // 在一次Test Suite之后执行一次清理
    @AfterAll
    public static void cleanUpAll() {
        System.out.println("Clean up all");
    }
}