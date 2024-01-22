package jvm_monitoring_optimization.memory_leak;

// TODO. 数组的大小存在极限值，触发极限会导致OOM
// java.lang.OutOfMemoryError: Requested array size exceeds VM limit
public class ArraySizeLimits {

    // TODO. 数组是引用类型(array is an object)，将在heap堆中分配内存空间进行存储
    public static void main(String[] args) {
        // 通过创建数组来测试Heap堆空间大小的设置
        // int intArray[];   //declaring array
        // intArray = new int[10];  // allocating memory to array

        byte[] array = new byte[256 * 1024 * 1024];
        System.out.println("done");
    }
}
