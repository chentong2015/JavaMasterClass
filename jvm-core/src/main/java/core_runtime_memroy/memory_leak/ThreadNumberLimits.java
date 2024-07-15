package core_runtime_memroy.memory_leak;

// 对于一个进程，OS拥有最大允许线程数量的限制，超过线程数极限将造成OOM
// Exception in thread "main" java.lang.OutOfMemoryError:
// unable to create native thread: possibly out of memory or process/resource limits reached
//   at java.base/java.lang.Thread.start0(Native Method)
//   at java.base/java.lang.Thread.start(Thread.java:802)
//   at memory.ThreadNumberLimits.main(ThreadNumberLimits.java:15)
public class ThreadNumberLimits {

    public static void main(String[] args) throws Exception {
        while (true) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000 * 60 * 60 * 24);
                            } catch (Exception ex) {
                            }
                        }
                    }
            ).start();
        }
    }
}
