package JavaUnitTestExceptions;

/**
 * TODO: https://junit.org/junit5/docs/current/user-guide/ 单元测试完整文档
 * 1. 验收测试是用来验证系统满足客户需求的<黑盒测试>
 * 2. 单元测试是用来验证系统中个别机制的<白盒测试>
 * <白盒测试>设计代码覆盖标准：控制流测试, 数据流测试, 分支测试, 语句覆盖, 判定覆盖, 修正条件/判定覆盖, 主要路径测试, 路径测试
 * 3. 单元测试的代码覆盖率：IntelliJ IDEA Code Coverage Agent
 * 4. 单元测试库：Groovy JUnit, Spock, TestNG, JUnit5 (推荐)
 * 5. Add JUnit4: Project Settings > Modules > Dependencies > Set JUnit to Compile
 */
public class BaseJUnit {

    private int balance = 100;
    private boolean isChecking = true;

    public int deposit(int balance) {
        this.balance += balance;
        return this.balance;
    }

    public boolean getChecking() {
        return isChecking;
    }

    public void testException(boolean withCheck) {
        if (withCheck) {
            System.out.println("Unit test");
        } else {
            throw new IllegalArgumentException();
        }
    }
}
