import java.util.ArrayList;
import java.util.List;

public class JVMProfilingDemo {

    // 以下测试代码在运行时可以通过可视化工具观察
    // 从内存以及GC的角度分析源代码中的异常和错误
    public static void main(String[] args) throws InterruptedException {
        List<DemoClass> heapList = new ArrayList<>();
        int count = 10;
        while (true) {
            heapList.add(new DemoClass(1, "test"));
            Thread.sleep(500);
            if (count > 100) {
                break;
            }
            count++;
        }
        System.out.println(heapList.size());
    }

    static class DemoClass {
        private int id;
        private String name;

        public DemoClass(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
