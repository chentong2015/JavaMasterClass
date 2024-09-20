package concurrent_apis.copywrite;

import java.util.concurrent.CopyOnWriteArrayList;

// CopyOnWrite: 读写分离思想 => 适用于“读多写少”场景
// 解决高并发读写的问题，保证数据的实时一致性(存在感知的时间差)
// 1. 在"写线程"写的(几步操作)过程中，"读线程"可能读取到半初始化的数据(脏数据)
// 2. 为了防止读写并发冲突，CopyOnWrite在操作时将原内存结构Copy一份副本，操作完成之后再替换旧副本
// 3. 在这个过程中运行存储替换的感知时间差，同时支撑了写的并发操作
public class BaseCopyOnWriteArrayList {

    // CopyOnWriteArrayList实现原理
    // 0. 使用数组来存储实际的值，在添加和删除时通过Arrays.copyOf()来动态改变数组的长度
    //    每次复制数组的过程中，会有一定的内存占用的问题
    //    private transient volatile Object[] array;
    //
    // 1. 读的线程之间没有互斥, 之间返回指定位置的元素
    //    public E get(int index) {
    //        return elementAt(getArray(), index);
    //    }
    //
    // 2. TODO: 写入时互斥，直接使用一个Object类型的对象作为锁，在操作的过程中，复制出一份新的数组，然后将元素追加到最后
    //    早期版本使用ReentrantLock来加锁
    //    新版本使用object对象来锁定 final transient Object lock = new Object();
    //    public boolean add(E e) {
    //        synchronized (lock) {
    //            Object[] es = getArray();
    //            int len = es.length;
    //            es = Arrays.copyOf(es, len + 1);
    //            es[len] = e;
    //            setArray(es);
    //            return true;
    //        }
    //    }

    public void testCopyOnWriteArrayList() {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("item");
        copyOnWriteArrayList.addIfAbsent("new item"); // 避免重复添加已经存在的数据

        String value = copyOnWriteArrayList.get(0);
        copyOnWriteArrayList.remove(1);
    }

    // 3. 将指定index位置的元素取出，然后取它前面和后面的值，拼接成新的结果数组
    //   public E remove(int index) {
    //     synchronized (lock) {
    //         Object[] es = getArray();
    //         int len = es.length;
    //         E oldValue = elementAt(es, index);
    //         int numMoved = len - index - 1;
    //         Object[] newElements;
    //         if (numMoved == 0)
    //             newElements = Arrays.copyOf(es, len - 1);
    //         else {
    //             newElements = new Object[len - 1];
    //             System.arraycopy(es, 0, newElements, 0, index);
    //             System.arraycopy(es, index + 1, newElements, index, numMoved);
    //         }
    //         setArray(newElements);
    //         return oldValue;
    //     }
    //   }
}
