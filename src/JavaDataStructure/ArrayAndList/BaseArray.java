package JavaDataStructure.ArrayAndList;

import java.util.Arrays;

// 静态数组: 存放相同类型的一组数据
// Array: store multiple values with the same types
// Arrays 存放基本的静态方法(直接调用) Arrays.toString()
public class BaseArray {

    // 1. 基本Value Types类型都可构建数组 + 自定义的类型也可以创建数组 TestClass[] test = new TestClass[10];  !!!! C#同理
    // 2. 使用new声明引用类型的实例, 数组具有固定的长度
    private int[] intArray = new int[10];  // par default = 0
    private String[] stringArray = new String[10];  // par default = null 
    private boolean[] boolArray = new boolean[10];  // par default = false

    // 3. 直接赋初值，同时确定数组的长度
    private double[] doubleArray = {1.0, 2.0, 3.0};
    private double[] doubleArray2 = new double[]{1.0, 2.0, 3.0};

    public BaseArray() {
        // 4. 从下标0开始
        intArray[0] = 10;
        intArray[1] = 10;
        // 5. 使用循环来初始化 ==> 这里使用的length field 设置成利于动态修改的  ===> C#中也是Length表示长度 !!!
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = "item " + i;
        }
    }

    // 数组排序的算法: Arrays.sort(); 10种算法 https://www.cnblogs.com/onepixel/articles/7674659.html

    // 1. 冒泡法(每次冒泡的最值排到最后)
    private static int[] bubbleSort(int[] array) {
        int[] sortedArray = Arrays.copyOf(array, array.length);  // 使用Java定义好的方法 !!!
        if (sortedArray.length > 1) {
            for (int i = 0; i < sortedArray.length - 1; i++) {
                for (int j = 0; j < sortedArray.length - 1 - i; j++) {
                    if (sortedArray[j] > sortedArray[j + 1]) {
                        swapValuesWithIndex(sortedArray, j, j + 1);
                    }
                }
            }
        }
        return sortedArray;
    }

    // 2. 反复循环 (直到不再有顺序的交换为止, 复杂度为n*n)
    private static int[] simpleSort(int[] array) {
        int[] sortedArray = Arrays.copyOf(array, array.length);
        boolean isAlreadySorted = false;
        while (!isAlreadySorted) {
            isAlreadySorted = sortArray(sortedArray);
        }
        return sortedArray;
    }

    // 以传引用的形式传递参数 !!!
    // 1. 方法中的修改的值，在方法外部也能看到
    // 2. 这里的array参数会拿到引用类型中存储的地址，成为又一个引用变量 !!!
    // 3. 如果array参数在方法中引用创建的新对象，则它的修改不再影响到外部 !!!
    public static boolean sortArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < array[i + 1]) {
                swapValuesWithIndex(array, i, i + 1);
                return false;
            }
        }
        return true;
    }

    public static void swapValuesWithIndex(int[] array, int firstIndex, int secondIndex) {
        int tempValue = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tempValue;
    }

    // 数组反转的算法：只需要遍历一半的值，然后交换镜像的两个值 !!!
    // 如果不减半的话，则会恢复成原来的值
    public static void reverse(int[] array) {
        int maxLength = array.length - 1;
        for (int i = 0; i < maxLength / 2; i++) {
            swapValuesWithIndex(array, i, maxLength - i);
        }
    }
}

        
