package runtime_api;

// ProcessHandle 关于Java进程的处理接口: 获取Process进程相关的信息和状态
public class JavaProcessId {

    // 获取操作系统分配给Java进程的identification number
    public static void main(String[] args) throws InterruptedException {
        // Returns a ProcessHandle for the current process.
        long pid = ProcessHandle.current().pid();
        System.out.println(pid);

        new Thread(() -> {
            System.out.println("run new thread");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        Thread.sleep(3000);
    }
}
