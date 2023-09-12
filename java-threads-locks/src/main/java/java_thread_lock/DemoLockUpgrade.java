package java_thread_lock;

// 测试锁升级的过程
// 1. JVM默认延时4s自动开启偏向锁，通过设置-XX:BiasedLockingStartupDelay=0来取消延时
// 2. 通过设置-XX:UseBiasedLocking=false来取消偏向锁
public class DemoLockUpgrade {

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

    static class DemoClass {

        private int id;
        private String name;

        public DemoClass(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
