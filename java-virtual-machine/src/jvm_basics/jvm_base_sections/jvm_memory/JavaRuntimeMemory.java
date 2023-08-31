package jvm_basics.jvm_base_sections.jvm_memory;

public class JavaRuntimeMemory {

    public static void main(String[] args) {
        System.out.println("Total Memory: " + Runtime.getRuntime().totalMemory());
        System.out.println("Free Memory: " + Runtime.getRuntime().freeMemory());

        // Allocate 256m 在堆中分配指定大小的空间
        // OOM 测试堆空间分配的设置是否过小，是否有溢出的情况
        byte[] array = new byte[256 * 1024 * 1024];
    }
}
