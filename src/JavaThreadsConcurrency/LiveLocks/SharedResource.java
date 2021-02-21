package JavaThreadsConcurrency.LiveLocks;

public class SharedResource {

    private Worker worker;

    public SharedResource(Worker worker) {
        this.worker = worker;
    }

    public Worker getWorker() {
        return worker;
    }

    // 必须synchronized才能操作共享的数据
    public synchronized void setWorker(Worker worker) {
        this.worker = worker;
    }
}
