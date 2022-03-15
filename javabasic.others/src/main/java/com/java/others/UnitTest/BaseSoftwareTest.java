package com.java.others.UnitTest;

// 软件测试：
// 1. 黑盒测试 (功能测试、数据驱动测试 DDT):
//    测试者不了解程序的内部情况，不需了解程序代码、内部结构和编程语言, 只需要程序输入输出和系统功能
//    用户的角度针对软件界面、功能及外部结构进行测试，而不考虑程序内部逻辑结构
// 2. 白盒测试 (透明盒测试、结构测试、逻辑驱动测试)
//    测试者了解待测试程序的内部结构、算法等信息，这是从程序设计者的角度对程序进行的测试
//    白盒测试可以应用于单元测试
public class BaseSoftwareTest {

    // 单元测试: 代码整洁之道
    //    TDD定律：在编写生成代码之前，先编写测试代码
    //    重要性：测试代码和生产代码一样重要，有了测试变能做改动
    //    双重标准：在生产环境中有所限制的，在测试环境中却没有问题
    //    FIRST：快速，独立，可重复，自足验证，及时(在生产代码之前)
    //    如果在编程生产代码之后编写测试，可能发现生产代码无法测试

    // 1. 验收测试是用来验证系统满足客户需求的<黑盒测试>
    // 2. 单元测试是用来验证系统中个别机制的<白盒测试>
    //    <白盒测试>设计代码覆盖标准：控制流测试, 数据流测试, 分支测试, 语句覆盖, 判定覆盖, 修正条件/判定覆盖, 主要路径测试, 路径测试
    // 3. 单元测试的代码覆盖率：IntelliJ IDEA Code Coverage Agent
    // 4. 单元测试库：Groovy JUnit, Spock, TestNG, JUnit5 (推荐)
    // 5. Add JUnit4: Project Settings > Modules > Dependencies > Set JUnit to Compile

    // 测试代码的覆盖率 Code Coverage
    // 1. IntelliJ IDEA code coverage:
    //    plugin Coverage 插件, 运行时选择Run with coverage, 查看覆盖报告信息和测试率
    // 2. JaCoCo (Java code coverage):
    //    Part of the Eclipse Foundation, integrate with Ant, Maven, Gradle, Jenkins, Visual Studio, IDEA ..
    // 3. EMMA & Cobertura 不再更新
}
