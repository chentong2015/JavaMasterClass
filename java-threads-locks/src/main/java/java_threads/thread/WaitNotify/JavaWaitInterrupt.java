package java_threads.thread.WaitNotify;

public class JavaWaitInterrupt {

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

