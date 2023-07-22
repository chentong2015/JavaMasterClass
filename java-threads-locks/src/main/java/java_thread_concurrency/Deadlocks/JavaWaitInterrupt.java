package java_thread_concurrency.Deadlocks;

public class JavaWaitInterrupt {

    // TODO. 使用wait()方法的标准模式: 用来使线程等待某个条件，它必须在同步区域内部被调用
    // 始终使用wait循环模式来调用，不要在循环外调用
    // public void testWait() {
    //     synchronized (obj) {
    //         while (<condition does not hold>) {
    //             obj.wait(); release lock
    //         }
    //     }
    // }

    public void testThreadInterrupt() {
        ThreadMessage message = new ThreadMessage();
        Thread writeThread = new Thread(() -> {
            String[] messages = {"message 01", "message 02"};
            for (int i = 0; i < messages.length; i++) {
                message.writeSync(messages[i]);
                // Thread.sleep(1000);
            }
            message.writeSync("Done");
        });
        Thread readThread = new Thread(() -> {
            for (String str = message.readSync(); !str.equals("Done"); str = message.readSync()) {
                System.out.println(str);
                // Thread.sleep(1000);
            }
        });
        writeThread.start();
        readThread.start();
        writeThread.interrupt();
    }

    // TODO: 线程自旋等待，直到循环判断的条件发生变化，判断再触发
    volatile boolean eventNotificationNotReceived;

    void waitForEventAndHandleIt() {
        while (eventNotificationNotReceived) {
            java.lang.Thread.onSpinWait();
        }
        readAndProcessEvent();
    }

    void readAndProcessEvent() {
        // Read event from some source and process it
        // . . .
    }
}

