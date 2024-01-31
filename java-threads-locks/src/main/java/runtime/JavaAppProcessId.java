package runtime;

// ProcessHandle 关于Java进程的处理接口: 获取Process进程相关的信息和状态
public class JavaAppProcessId {

    public static void main(String[] args) throws InterruptedException {
        testJavaProcessHandle();
        System.out.println(System.getProperty("pid"));

        // 同一个进程中包含另外一个线程
        new Thread(() -> {
            System.out.println("run new thread");
            try {
                Thread.sleep(200000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        while (true) {
            System.out.println("test");
            Thread.sleep(3000);
        }
    }

    // 获取操作系统分配给Java进程的identification number, 设置到系统属性中
    public static void testJavaProcessHandle() {
        // Returns a ProcessHandle for the current process.
        long pid = ProcessHandle.current().pid();
        System.setProperty("pid", "-" + pid);
    }
}
