package JavaThreadsConcurrency.DeadlocksWaitNotify;

import JavaThreadsConcurrency.DeadlocksWaitNotify.Model.Message;
import JavaThreadsConcurrency.DeadlocksWaitNotify.Model.Reader;
import JavaThreadsConcurrency.DeadlocksWaitNotify.Model.Writer;

// 只能在同步化的语句块里面被调用的方法
public class ProducerConsumer {

    public static void main(String[] args) {
        Message message = new Message();
        Thread writeThread = new Thread(new Writer(message));
        writeThread.start();
        Thread readThread = new Thread(new Reader(message));
        readThread.start();
    }

}
