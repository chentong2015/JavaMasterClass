package projects.shared_objects;

public class JvmShareObjectsDesign2 {

    private HandlerClass sharedHandler;

    public JvmShareObjectsDesign2() {
        this.sharedHandler = new HandlerClass();
    }

    // TODO. 所有多线程共享一个创建好的Handler对象
    public void testSharedObject() {
        for (int i=0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                sharedHandler.handleObject(new Object(), finalI);
            }).start();
        }
    }

    public static void main(String[] args) {
        JvmShareObjectsDesign2 design2 = new JvmShareObjectsDesign2();
        design2.testSharedObject();
    }

    class HandlerClass {
        public HandlerClass() {
            System.out.println("Init Object");
        }

        // TODO. 每个线程处理自己独有的object对象(通过参数传递)
        //  Object对象不与别的线程共享, 在方法层面不需要添加任何的同步锁设置
        public void handleObject(Object object, int currentId) {
            System.out.println("Current id:" + currentId);
            if (object == null) {
                // throw new RuntimeException("null name");
            }
        }
    }
}
