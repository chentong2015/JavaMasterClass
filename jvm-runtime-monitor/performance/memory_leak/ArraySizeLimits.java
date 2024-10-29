package garbage_collection.memory_leak;

// TODO. 数组的大小存在极限值，触发极限会导致OOM
// 数组是引用类型(array is an object)，将在heap堆中分配内存空间进行存储
public class ArraySizeLimits {

    // 通过创建数组来测试Heap堆空间大小的设置
    public static void main(String[] args) {
        // int intArray[];          //declaring array
        // intArray = new int[10];  // allocating memory to array

        // java.lang.OutOfMemoryError: Requested array size exceeds VM limit
        byte[] array = new byte[256 * 1024 * 1024];
        System.out.println("done");
    }
}
