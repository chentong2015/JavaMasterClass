package jvm.chapter13.model;

// this逃逸：指在构造函数返回之前其他线程就持有该对象的引用
public class DemoEscapeClass {

    public DemoEscapeClass() {
        new Thread(new EscapeRunnable()).start();
        // Construct the class
    }

    // 在类型完全构造好之后，再启动其他的线程
    public void initThread() {
        // thread.start();
    }

    private class EscapeRunnable implements Runnable {
        @Override
        public void run() {
            // DemoEscapeClass.this.fieldName
            // 通过DemoEscapeClass.this就可以引用外围类对象
            // 但是此时外围类对象可能还没有构造完成, 即发生了外围类的this引用的逃逸
        }
    }
}
