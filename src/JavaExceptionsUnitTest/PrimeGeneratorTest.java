package JavaExceptionsUnitTest;
// 使用Open Module Setings 来添加.jar的类库和框架

import junit.framework.TestCase;

// JUnit 3.X: junit.framework.Assert
// JUnit 4.X: org.junit.Assert
// import static org.junit.Assert.*;

public class PrimeGeneratorTest extends TestCase {

   /* public static void main(String[] args) {
        junit.swingui.TestRunner.main(new String[] {"PrimeGeneratorTest"});
    }
    */

    private int num;

    public void setUp() {

    }

    public PrimeGeneratorTest(String name) {
        super(name);
    }

    // 最简单的单元测试方法，可以直接进行编译
    public void testPrimes() {
        // int[] nullArray = PrimeGenerator.generatePrimes(0);
        assertEquals(10, 0);
    }

    public void testValues() {
        // 每写的一个方法都是一个单独的Test Case
        assertEquals(10, 10);
    }

}


