package JavaThreadsConcurrency.Base;

import static JavaThreadsConcurrency.Base.ThreadColor.ANSI_RED;

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
