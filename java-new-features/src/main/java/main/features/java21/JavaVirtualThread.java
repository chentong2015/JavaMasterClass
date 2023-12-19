package main.features.java21;

// 相比较与传统的线程池线程, 虚拟线程带来了哪些优势
// 1. 虚拟线程能够并发执行大量的task, 仅依赖很少的OS Threads, 并且能够在较短时间内完成
// 2. Executors.newFixedThreadPool(200) 传统的线程池线程会创建大量的platform threads,
//    造成性能问题或者程序崩溃

public class JavaVirtualThread {

    public void testVirtualThread() {
        // try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
        //     IntStream.range(0, 10_000).forEach(i -> {
        //         executor.submit(() -> {
        //             Thread.sleep(Duration.ofSeconds(1));
        //             return i;
        //         });
        //     });
        // }  // executor.close() is called implicitly, and waits
    }
}
