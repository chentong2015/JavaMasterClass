package jvm_basics.chapter05_Optimisation;

import jvm_basics.chapter05_Optimisation.model.DemoClass;

import java.util.ArrayList;
import java.util.List;

// JVM线上的一些诊断和辅助调优的一些手段
// 1. Java Visual VM for Windows
//    展示java进程运行中内部的一些情况，检测对象在堆内存中的流转
//    安转Visual GC插件，测试对象在堆空间的流转和GC回收情况
// 2. 调优工具参见命令
//    > Jmap 查看内存信息，实例格式及占内存的大小
//           jmp -histo 14660  查看历史生成的实例
//           jmp -histo:live 14660 查看当前存活的实例，可能触发full gc
//    > Jstack 查看线程的堆栈情况(需要转换)
//             用jstack+线程id可以查找死锁，找出占用CPU最高的线程的堆栈信息
//    > jps 显示系统中所有的虚拟机进程
//    > jstat 收集虚拟机的运行数据
//    > jinfo 显示虚拟机的配置信息
public class BaseJVMDebugTools {

    // 3. Arthas for Linux Ali开源的Java诊断工具
    //    使用场景: > 查看那里占用了CPU
    //             > 多线程是否有死锁，有阻塞么
    //             > 程序的什么位置运行耗时
    //             > 类所加载的jar类库，为何会报相关的Exception
    //             > 监控JVM的实时运行状态
    //    > java -jar arthas-boot.jar 检测本机的所有jvm进程
    //    > dashboard        展示线程的所有情况，运行状况
    //    > thread thread_ID 查看线程占用CPU的情况，可能出问题的代码 !!
    //    > jad com.ctong.test.DemoClass TODO: 反编译线上的代码，确认发布成功 !!
    //    > 支持直接修改内存中的数据，不需要重新发布代码
    public static void main(String[] args) throws InterruptedException {
        List<DemoClass> heapList = new ArrayList<>();
        while (true) {
            heapList.add(new DemoClass());
            Thread.sleep(10);
        }
    }
}


