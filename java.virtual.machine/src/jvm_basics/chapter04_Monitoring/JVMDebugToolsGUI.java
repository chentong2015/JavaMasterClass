package jvm_basics.chapter04_Monitoring;

import jvm_basics.base.DemoClass;

import java.util.ArrayList;
import java.util.List;

// JVM 可视化故障处理工具
public class JVMDebugToolsGUI {

    // 1. JConsole Java监视和管理控制台

    // 2. Java Visual VM for Windows
    //    展示java进程运行中内部的一些情况，检测对象在堆内存中的流转
    //    安转Visual GC插件，测试对象在堆空间的流转和GC回收情况

    // 3. Arthas for Linux Ali开源的Java诊断工具
    //    使用场景: > 查看那里占用了CPU
    //             > 多线程是否有死锁，有阻塞么
    //             > 程序的什么位置运行耗时
    //             > 类所加载的jar类库，为何会报相关的Exception
    //             > 监控JVM的实时运行状态
    //    > java -jar arthas-boot.jar 检测本机的所有jvm进程
    //    > dashboard        展示线程的所有情况，运行状况
    //    > thread thread_ID 查看线程占用CPU的情况，可能出问题的代码 !!
    //    > jad com.ctong.test.DemoClass 用于反编译线上的代码，确认发布成功
    //    > 支持直接修改内存中的数据，不需要重新发布代码
    public static void main(String[] args) throws InterruptedException {
        List<DemoClass> heapList = new ArrayList<>();
        while (true) {
            heapList.add(new DemoClass(1, "test"));
            Thread.sleep(10);
        }
    }
}
