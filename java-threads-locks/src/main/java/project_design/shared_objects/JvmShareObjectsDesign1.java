package project_design.shared_objects;

// 设计场景: 多线程共享Java对象时抛出异常状态
// - 线程并发运行时，避免共享对象被修改
// - 线程切换运行时，保证对象一致性和方法执行的独立性
public class JvmShareObjectsDesign1 {

    // 多个线程所共享的对象，启动包含所共享的object
    private HandlerClass sharedHandler;

    // TODO. 每个线程都会修改共享对象，创建新的Handler Object
    //  导致线程A在调用对象方法时，可能调用到的不是自己线程所创建的Handler Object
    //  导致线程A在执行时，使用了错误的object对象，与自己初始的object对象不一致
    public void testSharedObject() {
        for (int i=0; i < 100; i++) {
            new Thread(() -> {
                sharedHandler = new HandlerClass(new Object());
                sharedHandler.handleObject();
            }).start();
        }
    }

    public static void main(String[] args) {
        JvmShareObjectsDesign1 design1 = new JvmShareObjectsDesign1();
        design1.testSharedObject();
    }

    class HandlerClass {
        private final Object object;

        public HandlerClass(Object object) {
            this.object = object;
            System.out.println("Init Object:" + object);
        }

        // 处理时，object可能已经被被的线程修改，造成异常
        public void handleObject() {
            System.out.println("Current Object:" + object);
            if (object == null) {
                // throw new RuntimeException("null object");
                System.out.println("Cause Other Exceptions");
            }
        }
    }
}
