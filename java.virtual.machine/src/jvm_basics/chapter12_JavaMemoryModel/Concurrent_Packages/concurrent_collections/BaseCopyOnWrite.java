package jvm_basics.chapter12_JavaMemoryModel.Concurrent_Packages.concurrent_collections;
// CopyOnWrite思想:
// 读写分离，解决高并发读写的问题，保证数据的实时一致性(存在感知的时间差)
// 1. 在"写线程"写的过程中(几步操作)，"读线程"可能读取到半初始化的数据(脏数据)
// 2. 为了防止读写并发冲突，CopyOnWrite在操作时将原内存结构Copy一份副本，操作完成之后再真正的替换
// 3. 在这个过程中运行存储替换的感知时间差，同时支撑了写的并发操作

// 使用场景：
// 1. 黑白名单
// 2. 读多写少
// 3. Nocas微服务架构注册中心
// 4. 数据库高级优化策略
public class BaseCopyOnWrite {

    // CopyOnWriteArrayList 线程安全的动态数组  https://mikechen.cc/1045.html
    // CopyOnWriteArraySet

}
