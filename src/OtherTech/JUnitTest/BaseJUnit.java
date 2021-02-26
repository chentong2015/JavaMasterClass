package OtherTech.JUnitTest;

/**
 * TODO: https://junit.org/junit5/docs/current/user-guide/ 单元测试完整文档
 * 1. 验收测试是用来验证系统满足客户需求的<黑盒测试>
 * 2. 单元测试是用来验证系统中个别机制的<白盒测试>
 * <白盒测试>设计代码覆盖标准：控制流测试, 数据流测试, 分支测试, 语句覆盖, 判定覆盖, 修正条件/判定覆盖, 主要路径测试, 路径测试
 * 3. 单元测试的代码覆盖率：IntelliJ IDEA Code Coverage Agent
 * 4. 单元测试库：Groovy JUnit, Spock, TestNG, JUnit5 (推荐)
 * 5. Add JUnit4: Project Settings > Modules > Dependencies > Set JUnit to Compile
 * <单元测试: 代码整洁之道>
 * TDD定律：在编写生成代码之前，先编写测试代码
 * 重要性：测试代码和生产代码一样重要，有了测试变能做改动
 * 双重标准：在生产环境中有所限制的，在测试环境中却没有问题
 * FIRST：快速，独立，可重复，自足验证，及时(在生产代码之前) ==> 如果在编程生产代码之后编写测试，可能发现生产代码无法测试 !!
 */

/**
 * 测试代码的覆盖率 Code Coverage
 * 1. IntelliJ IDEA code coverage: plugin Coverage 插件, 运行时选择Run with coverage => 查看覆盖报告信息和测试率
 * 2. JaCoCo (Java code coverage): Part of the Eclipse Foundation, integrate with Ant, Maven, Gradle, Jenkins, Visual Studio, IDEA ..
 * 3. EMMA & Cobertura 不在更新
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
            System.out.println("Unit JavaUnitTestExceptions.test");
        } else {
            throw new IllegalArgumentException();
        }
    }
}
