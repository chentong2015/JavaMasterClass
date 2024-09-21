package project_concurrent_programming.features.Ordering;

import java.util.HashSet;
import java.util.Set;

// Happen-Before先行发生原则
// 辅助保证程序执行的原子性，可见性，和有序性，用于判断数据是否竞争，线程是否安全，操作数之间的偏序关系
// 先行发生原则和时间先后顺序之间没有必然的因果关系，时间先并不代表操作是"先行发生"的
// 一共8条规则，判断线程是否安全，不允许重排序 !!
// 1. 顺序原则
// 2. 锁规则: unlock必须发生在同一个锁的lock之前，先解锁才能再加锁
// 3. 传递性: A先于B，B先于C，必然A先于C
// 4. 对象的初始化动作需要先于finalize()调用之前

// as-if-serial(语义)规范
// TODO: 原则只定义在单线程，不考虑多线程下造成问题，多线程之间依赖关系造成的问题"无法"从语义上面直接分析出来
// 不管怎么重排序(编译器和处理器为了提高并行度)，单线程的执行结果不能被改变，必须遵守as-if-serial语义
// 也即编译器和处理器不会对"存在数据依赖关系的操作做重排序"，不会改变执行的结果
public class CodeReordering {

    // 指令重排序: 在不影响单线程程序执行结果的前提下，处理器会对程序执行的顺序进行重排，发给各个相应的电路单元来执行
    // 源代码 --> 编译器优化重排序
    //       --> 指令并行重排序
    //       --> 内存系统重排序
    //       --> 最终执行指令序列

    private int x;
    private int y;
    private int a;
    private int b;

    // 以下代码测试出来的a,b值一共有4种可能
    // a = 0, b = 0  AB交叉执行
    // a = 1, b = 0  B先执行
    // a = 0, b = 1  A先执行
    // a = 1, b = 1  AB线程各自"指令重排"执行
    public void testOrdering() throws InterruptedException {
        Set<String> results = new HashSet<>();

        for (int index = 0; index < 1000; index++) {
            x = 0;
            y = 0;
            Thread threadA = new Thread(() -> {
                // 以下两个操作在"此单线程"中交换执行顺序后，不影响结果，可以重排执行 !!
                a = y;
                // 添加内存屏障，这个屏障前后的代码不能做重排序
                x = 1;
            });
            Thread threadB = new Thread(() -> {
                b = x;
                y = 1;
            });
            threadA.start();
            threadB.start();
            threadA.join();
            threadB.join();
            results.add("a=" + a + ",b=" + b);
        }
    }
}
