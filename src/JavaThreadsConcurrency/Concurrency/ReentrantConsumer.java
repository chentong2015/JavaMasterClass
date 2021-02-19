package JavaThreadsConcurrency.Concurrency;

import java.util.List;

public class SyncConsumer implements Runnable {

    private List<String> buffer;
    private String color;

    public SyncConsumer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        // 实战项目，不可保持这个Thread持续的运行，而是使用wait(); notify()机制
        while (true) {
            synchronized (buffer) {
                if (buffer.isEmpty()) {
                    continue;
                }
                if (buffer.get(0).equals("EOF")) {
                    System.out.printf(color + "Existing..");
                    break;
                } else {
                    System.out.println(color + "removed " + buffer.remove(0));
                }
            }
        }
    }
}
