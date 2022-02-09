package jvm_basics.chapter13_Lock_ThreadSafety.JavaLockOptimization;

import base.DemoClass;

// 对于有并发问题的代码块，很多时候只有一个线程在执行，没有资源争抢，造成不必要的性能损失
// 通过给synchronized锁添加不同的状态来实现优化, 自动进行"锁升级"
// 1. 无锁状态:
// 2. 偏向锁 : 把线程id放到对象的Object Header中暂存，之后线程来判断线程id是否一致
//            必须要启用偏向锁，不会主动释放添加的线程id
// 3. 轻量级锁: 轻量竞争，通过CAS操作来执行
//             如果没有启用偏向锁，则直接升级成轻量级锁
// 4. 重量级锁: CAS锁不成功/自旋线程持续消耗CPU，对应高并发，重度竞争

// 另外两种锁优化机制
// 1. 锁消除: 对于一些要求同步的代码块，如果(逃逸分析)被检测到不可能存在共享数据竞争的问题
//           没有逃逸到别的线程中，则对锁进行消除
// 2. 锁粗化: 对应连续的对同一个对象加锁和解锁，为了避免对性能的影响，会将锁同步的范围进行扩展
public class LockOptimization {

    // 添加类库依赖: 打印对象内部的组成结构
    // <dependency>
    //    <groupId>org.openjdk.jol</groupId>
    //    <artifactId>jol-core</artifactId>
    //    <version>0.16</version>
    //    <scope>provided</scope>
    // </dependency>

    // 测试锁升级的过程
    // 1. JVM默认延时4s自动开启偏向锁，通过设置-XX:BiasedLockingStartupDelay=0来取消延时
    // 2. 通过设置-XX:UseBiasedLocking=false来取消偏向锁
    public void testLockOptimization() throws InterruptedException {
        DemoClass demoClass = new DemoClass(1, "chen");
        // ClassLayout.parseInstance(demoClass).toPrintable(); 锁是无状态 001

        Thread.sleep(5000);
        DemoClass demoClass1 = new DemoClass(2, "tong"); // 开启了偏向锁
        for (int i = 0; i < 2; i++) {
            synchronized (this) {
                System.out.println("偏向锁 101，在对象头中写入线程ID");
            }
        }

        // 当有第二个线程来争抢同一个锁时，原来对象开启的"偏向锁"会自动升级成"轻量级锁"
        new Thread(() -> {
            synchronized (demoClass1) {
                System.out.println("轻量级锁 00");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 在第二个线程sleep的过程中，第三个线程再来争抢，使得原来的"轻量级锁"自动升级成"重量级锁"
        // TODO：在何种情况情况下，多高的并发会升级到重量级锁 ?
        // 1. CAS自旋到一定的次数会做升级，默认自旋的次数是10次，-XX:PreBlockSpin设置
        //    自适应自旋: 自旋的次数不是固定的，根据上一次在同一个锁上的自旋时间和锁拥有者的状态
        // 2. 根据线程获得锁的难易程度来判断(重度竞争)
        // TODO: 如何更好的做锁升级 ?
        // 1. 使用分段CAS优化机制，使用于量不大的线程数量，提升性能
        Thread.sleep(1000);
        new Thread(() -> {
            synchronized (demoClass1) {
                System.out.printf("重量级锁 10");
            }
        }).start();
    }
}
