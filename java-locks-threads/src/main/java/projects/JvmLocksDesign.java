package projects;

import java.util.HashSet;
import java.util.Set;

// 设计场景: 基于JVM级别的锁，限制两个package不能同时被两个UI端(线程)执行
// - 创建额外的线程来处理package耗时操作，避免主线程阻塞
// - 线程在获取锁之后，仍然需要判断package运行状态，避免重复并发执行
public class JvmLocksDesign {

    // TODO. 由于集合中数据的修改都在Lock Block锁范围内执行，因此不需要线程安全的Set
    private final Set<String> runningPackageSet = new HashSet<>();

    private boolean isRunInProgress(String packageName) {
        return runningPackageSet.contains(packageName);
    }

    private void addRunningPackage(String packageName) {
        if (runningPackageSet.contains(packageName)) {
            return;
        }
        runningPackageSet.add(packageName);
    }

    private void removeRunningPackage(String packageName) {
        if (packageName == null || !runningPackageSet.contains(packageName)) {
            return;
        }
        runningPackageSet.remove(packageName);
    }

    // 如果不判断运行状态，会导致在线程A获取锁并返回方法后
    // 新的线程B拿到锁并且可能执行同一个PackageID
    public void runPackage(String packageId, String label) {
        synchronized (runningPackageSet) {
            if (isRunInProgress(packageId)) {
                System.out.println("Concurrent: The package is running.");
                return;
            }
            // 同一个时刻，只有一个线程能够记录(修改)Package的运行状态
            addRunningPackage(packageId);
            try {
                new Thread(() -> {
                    System.out.println("run asynchronously");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Finish running package");
                    // 移除记录的PackageID运行状态，唤醒正在等待运行它的线程
                    removeRunningPackage(packageId);
                }).start();
            } catch (Throwable throwable) {
                // 在异常情况下，确保能够移除正在运行的package
                removeRunningPackage(packageId);
            }
            // try block语句块之后立即返回，并释放线程获取到的锁
        }
    }

    public static void main(String[] args) throws Exception {
        JvmLocksDesign jvmLocksDesign = new JvmLocksDesign();
        new Thread(() -> jvmLocksDesign.runPackage("001", "label")).start();
        new Thread(() -> jvmLocksDesign.runPackage("001", "label")).start();
        Thread.sleep(6000);
        new Thread(() -> jvmLocksDesign.runPackage("001", "label")).start();
        new Thread(() -> jvmLocksDesign.runPackage("001", "label")).start();
    }
}
