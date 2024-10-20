package java_compiler.jit_backend;

public class DemoThisEscape {

    // this逃逸:
    // 指在构造函数返回之前"其他线程"就持有该对象的引用, 而被引用的对象还没有构建完成
    public DemoThisEscape() {
        new Thread(new EscapeRunnable()).start();
        // Construct the class
    }

    // this逃逸解决方案:
    // 在类型完全构造好之后，再启动其他的线程
    public void initThread() {
        // thread.start();
    }

    // 在另一个线程中通过this来引用外围类对象，但此时外围类对象可能还没有构造完成
    private class EscapeRunnable implements Runnable {
        @Override
        public void run() {
            DemoThisEscape.this.initThread();
            // to do ...
        }
    }
}
