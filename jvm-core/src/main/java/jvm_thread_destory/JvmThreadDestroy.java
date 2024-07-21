package jvm_thread_destory;

// TODO. JVM会在main()方法执行介绍后创建DestroyJavaVM线程来等待所有用户线程结束
// Once main completes, the JVM is told to shut down using a DestroyJavaVM thread
// which waits for all non-daemon threads to complete before doing its work.
// Ensure that any non-daemon threads run to completion before the JVM is torn down.
public class JvmThreadDestroy {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start main thread");
        new Thread(() -> {
            System.out.println("Starr thread 1");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            System.out.println("Starr thread 2");
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        Thread.sleep(10000);
        System.out.println("End main thread");
    }
}
