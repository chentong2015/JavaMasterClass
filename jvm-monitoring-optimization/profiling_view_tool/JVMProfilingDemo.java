package profiling_view_tool;

import java.util.ArrayList;
import java.util.List;

public class DemoJVMProfiling {

    // 以下测试代码在运行时可以通过可视化工具观察
    // 从内存以及GC的角度分析源代码中的异常和错误
    public static void main(String[] args) throws InterruptedException {
        List<DemoClass> heapList = new ArrayList<>();
        while (true) {
            heapList.add(new DemoClass(1, "test"));
            Thread.sleep(10);
        }
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
