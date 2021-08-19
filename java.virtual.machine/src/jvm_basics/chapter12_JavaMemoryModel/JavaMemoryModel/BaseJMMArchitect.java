package jvm_basics.chapter12_JavaMemoryModel.JavaMemoryModel;

// JMM: Java Memory Model
// 屏蔽各种硬件和OS的内存访问差异，让java程序在各个平台都能达到一致的内存访问效果，让并发内存访问操作不会产生歧义
public class BaseJMMArchitect {

    // JMM内存模型的测试：
    // 主内存和工作内存的拆分，共享变量会加载到工作内存的"共享变量的副本"，然后进行操作
    // 使用volatile来保证"可见性"
    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("waite data ...");
            while (!flag) {
            }
            System.out.println("finish");
        }).start();
        Thread.sleep(200);
        new Thread(() -> refreshFlag());
    }

    private static void refreshFlag() {
        System.out.println("set flag");
        flag = true;
        System.out.println("set ok");
    }

    // 硬件级别的数据交互: 原子操作
    //          CPU                  CPU
    //        线程"use"              线程"use"/"assign"
    //        工作内存"load"         工作内存"load"
    //        flag=false            flag --> true
    // 总线 ---------------------------------------- "read"
    //                     主内存                     "store"
    //                   flag=false             <--  "write"
}
