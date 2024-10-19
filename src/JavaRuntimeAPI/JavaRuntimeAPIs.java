package JavaRuntimeAPI;

import java.util.Scanner;

// TODO. Runtime.getRuntime()为java程序提供运行时相关的API
// - 查看程序运行时系统信息      Runtime.getRuntime().totalMemory();
// - 主动调用gc(可能被忽略)      Runtime.getRuntime().gc();
// - 调用其他exe程序或者脚本执行  Runtime.getRuntime().exec(cmd2)
// - 为程序设置shutdown hook    Runtime.getRuntime().addShutdownHook()
public class JavaRuntimeAPIs {

    // TODO. JVM可用处理器数可以用于判断并发的线程数量, 以最大限度完成并发操作
    // 返回processors处理器数量(Logical threads逻辑线程数量, 可能包含hyper-threading超线程)
    // 返回的结果不会考虑系统的physical cores物理核心数
    private static void testJvmProcessor() {
        int cores = Runtime.getRuntime().availableProcessors();
        // Split work to threads based on number of processors
        for(int i=0; i < cores; i++) {
            // Start new thread
        }
    }

    public static void main(String[] args) {
        // 创建程序关闭时的Hook，可在程序被异常关闭时执行额外操作
        // 例如程序被强制关闭，需要清理临时备份出来的文件
        Runtime.getRuntime().addShutdownHook(new Thread("app-shutdown-hook") {
            @Override
            public void run() {
                // Run after program shutdown
                System.out.println("Shutdown resource connection");
            }
        });
        System.out.println("Hook created and wait..");
        String input = new Scanner(System.in).nextLine();

        // 以某个指定的状态推出当前的JVM虚拟机
        // System.exit(0);
        Runtime.getRuntime().exit(0);
    }
}
