package runtime;

import java.util.Scanner;

// TODO. Runtime.getRuntime()为java程序提供运行时相关的API
// - 主动调用gc(可能被忽略) Runtime.getRuntime().gc();
// - 查看程序运行时的memory内存情况  Runtime.getRuntime().totalMemory();
// - 调用其他exe程序或者bash脚本执行(linux命令)
// - 为程序设置shutdown hook
public class JavaRuntimeAPIs {

    // 为java进程设置shutdown的hook钩子
    // 创建新的线程来检测程序是否被shutdown(ctrl+c), 在异常关闭时执行流的关闭和数据清理
    public static void main(String[] args) {
        System.out.println("Start app");
        Runtime.getRuntime().addShutdownHook(new Thread("app-shutdown-hook") {
            @Override
            public void run() {
                System.out.println("Shutdown resource connection");
            }
        });
        System.out.println("Hook created and wait..");
        String input = new Scanner(System.in).nextLine();
    }
}
