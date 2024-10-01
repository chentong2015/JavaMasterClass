package concurrent_program.features.Visibility;

// TODO. 如果没有同步，就无法保证一个线程所作的修改被另一个线程所获知
public class BaseVisibility {

    // 1. 主内存和工作内存的拆分，共享变量会加载到工作内存的"共享变量的副本"，然后进行操作
    // 2. 使用volatile来保证"可见性"
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
}
