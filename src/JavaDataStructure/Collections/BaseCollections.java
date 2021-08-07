package JavaDataStructure.Collections;

import JavaDataStructure.Collections.Model.Seat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collections.sort(teams);    实现了Comparable<T>该泛型接口的类型的List, 通过compareTo()中重写的逻辑来实现排序
 * Collections.swap();         交换Array中的两个值，或者泛型List中的两个值 !!
 * Collections.reverse();      颠倒list中存储对象的地址的顺序 !!!
 * Collections.shuffle();      随机洗牌
 * Collections.binarySearch(seats, requestSeat); 实现了Comparable<T>该泛型接口的类型Seat的list，可以对其中元素进行二分法查找
 * Collections.min();
 * Collections.max();
 * Collections.copy(des, src)
 * Collections.unmodifiable...() 返回一个不可变的View"视图"; 对于返回的集合是"read-only"只读形式的 -> UnSupportedOperationException
 * .                             但是可以通过对象自身的方法实现修改 It's the collection itself, not exactly the object !
 * .                             get()提供了修改对象的可能 stockList.getList().get(key).setChange(newValue);
 */
public class BaseCollections {

    // private Collection<Seat> seats = new ArrayList<>();
    private List<Seat> seats = new ArrayList<>();     // 这里的list符合参数：@NotNull List<? extends Comparable<? super T>> list

    public BaseCollections(String name, int numRows, int seatPerRows) {
        loadAllSeats(numRows, seatPerRows);
    }

    // 初始的时候已经按照sort的排序进行初始化
    private void loadAllSeats(int numRows, int seatPerRows) {
        int lastRow = 'A' + (numRows - 1);  // 约束偏移量小于26(字母总数)
        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNum = 1; seatNum <= seatPerRows; seatNum++) {  // 这里可以更加具体的位置指定不同的价格
                seats.add(new Seat(row + String.format("%02d", seatNum), 10.0));
            }
        }
    }

    // 在SortedList列表中的普通检索 !!
    public boolean reserveSeat(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getSeatNumber().equals(seatNumber)) {
                return seat.reserve();
            }
        }
        return false;
    }

    // 二分法查找：在"排序List"中查找指定Item的最快算法 !!! 10次以内可从1024个值中找到需要的值
    // 区间低值 int low = 0;
    // 区间高值 int high = list.size()-1;
    public boolean reserveSeatWithBinarySearch(String seatNumber) {
        Seat requestSeat = new Seat(seatNumber, 10.0);
        int foundIndex = Collections.binarySearch(seats, requestSeat);
        if (foundIndex >= 0) {
            return seats.get(foundIndex).reserve();  // 设置list里面的对象的值 !!!
        }
        return false;
    }

    /**
     * 该Copy方法实现，以及如何体现出Deep Copy和Shadow Copy的效果
     * 由于存在限制的初始化要求，所以不推荐使用该方法
     */
    private void testCollectionsCopy() {
        // Collections.copy(List<? super T> dest, List<? extends T> src) {}
        // 1. dest可以是T的父类以上类型，而src源list必须是一个具体的List
        // 2. dest的集合可容纳的对象长度必须大于等于src提供的长度
        // 3. 在copy时，当T类型为不可变类型，则表现为Deep Copy
        // 4. 在copy时，当T类型为可变类型，则表现为Shadow Copy
    }
}
