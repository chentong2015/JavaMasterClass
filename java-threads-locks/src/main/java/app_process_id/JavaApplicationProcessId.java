package app_process_id;

// ProcessHandle 关于Java进程的处理接口: 获取Process进程相关的信息和状态
public class JavaApplicationProcessId {

    public static void main(String[] args) {
        testJavaProcessHandle();
        System.out.println(System.getProperty("pid"));
    }

    // 获取操作系统分配给Java进程的identification number, 设置到系统属性中
    public static void testJavaProcessHandle() {
        // Returns a ProcessHandle for the current process.
        long pid = ProcessHandle.current().pid();
        System.setProperty("pid", "-" + pid);
    }
}
