package concurrent_program.issues.liveLocks;

// 解决方案1: 从程序逻辑(设计)上面避免反复循环的产生
public class BaseLiveLocks01 {

    /**
     * worker1和worker2在交替的拥有sharedResource, 两个线程在不停的执行相同的交换操作
     * 最终导致两个线程都在执行looping, 但是都无法执行正在的操作，从未无法改变自身的active状态 !!
     */
    private void testLiveLocks() {
        final Worker worker1 = new Worker("worker 1", true);
        final Worker worker2 = new Worker("worker 2", true);
        final SharedResource sharedResource = new SharedResource(worker1);

        new Thread(() -> {
            try {
                worker1.work(sharedResource, worker2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                worker2.work(sharedResource, worker1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

class SharedResource {

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

class Worker {

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
     * 调用work()方法的对象需要确保自己的状态，同时判断是否拥有共享资源, 并且不能在别的worker处于active的状态下执行 !!
     */
    public synchronized void work(SharedResource sharedResource, Worker otherWorker) throws InterruptedException {
        while (active) {
            // 如果拥有SharedResource的不是自己，则等待
            if (sharedResource.getWorker() != this) {
                wait(10);
                continue;
            }

            // 如果被的worker是active状态，则需要将共享资源转交
            if (otherWorker.isActive()) {
                System.out.println(getName() + " give resource to other worker " + otherWorker.getName());
                sharedResource.setWorker(otherWorker);
                continue;
            }

            // 跳出while循环的条件：当前worker拥有SharedResource, 同时otherWorker不能处于active状态 !!
            // 执行处理，之后取消active状态，然后转交给别的worker
            System.out.println(getName() + " working on the shared resource");
            active = false;
            sharedResource.setWorker(otherWorker);
        }
    }

    /**
     * 修正程序逻辑：
     * worker如果拥有SharedResource，并且处于active的状态，就能得到执行
     * 最后将SharedResource转交个active的其他worker
     */
    public synchronized void workOK(SharedResource sharedResource, Worker otherWorker) throws InterruptedException {
        while (active) {
            if (sharedResource.getWorker() != this) {
                wait(10);
                continue;
            }
            System.out.println(getName() + " working on the shared resource");
            active = false;

            if (otherWorker.isActive()) {
                System.out.println(getName() + " give resource to other worker " + otherWorker.getName());
                sharedResource.setWorker(otherWorker);
            }
        }
    }
}
