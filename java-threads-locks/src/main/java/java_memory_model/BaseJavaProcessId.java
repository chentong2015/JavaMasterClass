package java_memory_model;

// ProcessHandle 关于Java进程的处理接口
// 可以获取Process进程相关的信息和状态
public class BaseJavaProcessId {

    // 获取操作系统分配给Java进程的identification number, 设置到系统属性中
    public void testJavaProcessHandle() {
        long pid = ProcessHandle.current().pid();
        System.setProperty("pid", "" + pid);
    }
}
