package JavaThreadsConcurrency.LiveLocks;

public class Worker {

    private String name;
    private boolean active;

    public Worker(String name, boolean isActive) {
        this.name = name;
        this.active = isActive;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    /**
     * worker.work(sharedResource, otherWorker)
     * 调用work()方法的对象需要确保自己的状态，同时判断是否拥有共享资源 !!
     */
    public synchronized void work(SharedResource sharedResource, Worker otherWorker) throws InterruptedException {
        // 如果当前worker不是active状态，则不执行
        while (active) {
            // 如果拥有SharedResource的不是自己，则等待
            if (sharedResource.getWorker() != this) {
                wait(10);
                continue;
            }

            // 如果被的worker是active状态，则需要将共享资源转交
            if (otherWorker.isActive()) {
                System.out.println(getName() + " give the resource to worker " + otherWorker.getName());
                sharedResource.setWorker(otherWorker);
                continue;
            }

            // 跳出while循环的条件：当前worker拥有SharedResource, 同时otherWorker不能处于active状态 !!
            // 这时执行处理，之后取消active状态，然后转交给别的worker
            System.out.println(getName() + " working on the shared resource");
            active = false;
            sharedResource.setWorker(otherWorker);
        }
    }
}
