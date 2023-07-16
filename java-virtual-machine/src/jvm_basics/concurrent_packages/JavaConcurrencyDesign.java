package jvm_basics.concurrent_packages;

import java.util.HashSet;
import java.util.Set;

// 基于JVM级别的锁，限制两个package不能同时被两个UI Client端执行
public class JavaConcurrencyDesign {

    private Set<String> runPackageByName = new HashSet<>();

    private boolean isRunInProgress(String packageName) {
        return runPackageByName.contains(packageName);
    }

    private void addRun(String packageName) {
        if (runPackageByName.contains(packageName)) {
            return;
        }
        runPackageByName.add(packageName);
    }

    private void removePackage(String packageName) {
        if (packageName == null || !runPackageByName.contains(packageName)) {
            return;
        }
        runPackageByName.remove(packageName);
    }

    // TODO. 为什么synchronized锁范围内还需要检查是否在运行
    // 1. 在加锁的返回内开启新的线程，执行耗时的操作
    // 2. 在新线程A执行的过程中，主线程返回，导致另一个package拿到锁
    // 3. 另一个package获得到锁，开启新线程B执行
    // 4. 新线程A和新线程B同时执行，造成并发冲突 !!
    public void runPackage(String packageId, String label) throws Exception {
        // 对线程加锁，只有一个线程能够获取到锁
        synchronized (runPackageByName) {
            // 拿到锁之后，再做一个运行状态的判断 !!
            if (isRunInProgress(packageId)) {
                throw new Exception("Concurrent Run Exception: The same package is running.");
            }
            // 如果当前package没有正在运行，则添加到
            addRun(packageId);

            // 开启一个新的线程去"异步运行"指定的逻辑
            try {
                // TODO. 异步的线程运行完成之后，在Runnable Run()方法的实现最后需要调用removePackage()
                //   在调用removePackage()之后，会唤醒一个正在等待运行的package(如果存在)
                new Thread(() -> System.out.println("run package asynchronously")).start();
            } catch (Throwable throwable) {
                // In case an exception before the thread is started, ensure remove the compilation status
                // 在异常情况下，确保能够移除正在运行的package Id
                removePackage(packageId);
            }
        }
    }
}
