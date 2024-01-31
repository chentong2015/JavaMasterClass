package runtime;

import java.util.Scanner;

public class JavaRuntimeProcess {

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
