package jvm_basics.chapter12_JavaMemoryModel.Concurrency.Ordering.MemoryReordering;

import java.util.HashSet;
import java.util.Set;

// 指令重排序
// 在不影响单线程程序执行结果的前提下，处理器会对程序执行的顺序进行重排，发给各个相应的电路单元来执行
// 源代码 --> 编译器优化重排序 --> 指令并行重排序 --> 内存系统重排序 --> 最终执行指令序列
public class BaseMemoryReordering {

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
