package JavaThreadsConcurrency.BaseThreads.model;

import static JavaThreadsConcurrency.BaseThreads.model.ThreadColor.ANSI_RED;

public class DemoRunnable implements Runnable {

    // 注入后续执行的Thread线程
    // private Thread nextThread;
    // public DemoRunnable(Thread nextThread) {
    //     this.nextThread = nextThread;
    // }

    @Override
    public void run() {
        System.out.println(ANSI_RED + "Runnable thread");
    }
}
