package JavaDataStructure.ArrayAndList.ArrayList;

import java.util.ArrayList;
import java.util.List;

// 使用列表的优缺点：
// 1. 查找数据的速度快(直接使用下标索引值), 存储的地址是连续的, 只需计算偏移量
// 2. 在中间插入新的元素速度很慢(需用移动后面所有位置的值)
//
// List列表：动态的数组
// 1. Super interface:
//    Collection<E> extends Iterable<E> 上一级的接口：泛型集合可被迭代
// 2. public interface List<E> 泛型接口 size()
// 3. Implementing Classes:
//    AbstractList, ArrayList, CopyOnWriteList, LinkedList, Stack, Vector<T>
public class BaseArrayList {

    // 自定义列表初始化的容量，将添加的数据过多时会自动扩容
    private ArrayList<String> myList = new ArrayList<>(10);

    // TODO. ArrayList底层使用数组来存储
    public void testArrayList() {
        List<Integer> list = new ArrayList<>();
        list.add(10); // 默认添加在list的末尾
        list.get(1); // 通过下标来获取元素，效率高

        // 指定添加的位置，index位置后面的元素移动一个位置
        // System.arraycopy(elementData, index,elementData, index + 1,s - index);
        // elementData[index] = element;
        list.add(1, 10);
    }

    /**
     * ArrayList的复制: 核心方法 System.arraycopy(elementData, 0, a, 0, size);
     * 1. 直接赋值引用的方式，Shadow Copy
     * 2. new ArrayList<>(myList) 直接初始化构造
     * 3. addAll(myList) 直接复制ArrayList中的所有值
     * 4. 使用循环，依次复制
     */
    public void copyArrayList() {
        ArrayList<String> copyArray = myList;
        // 1. 如果ReferenceType是不可变类型(String, Integer); 则体现为Deep Copy的效果
        // 2. 如果ReferenceType是可变类型(自定义Class); 则体现为Shadow Copy的效果
        // 3. 对原始的列表追加新的元素，则不属于拷贝的内容
        ArrayList<String> nextArray = new ArrayList<>(myList);
        ArrayList<String> newArray = new ArrayList<>();
        newArray.addAll(myList);
    }

    private void convertListToArray() {
        String[] myArray = new String[myList.size()];
        myArray = myList.toArray(myArray); // 将要转换成的数组作为参数传递 !!!

        String[] myArray02 = (String[]) myList.toArray(); // Object[] -> String[]
    }

    public void testInsertItemToArrayList() {
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(3);
        intList.add(1, 2); // 导致index=1往后的值都会移动，以完成列表长度的自动扩充 !!
        intList.remove(1); // 后面位置的值自全部向前一位填充
    }
}
