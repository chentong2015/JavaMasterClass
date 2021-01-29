package JavaDataStructure.Collections;

import JavaDataStructure.Collections.Model.Seat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * * 1. public interface Comparable<T> ====> 直接实现compareTo()方法来指定排序时的比较判断逻辑
 * List<Seat> priceSeats = new ArrayList<Seat>();
 * priceSeats.add(new Seat("A00", 10.0));
 * priceSeats.add(new Seat("B00", 15.0));
 * Collections.sort(priceSeats);
 * -----------------------------------------------------------------------------------
 * * 2. public interface Comparator<T> 通过实现compare()方法来定义排序list中item的逻辑
 * List<Seat> priceSeats = new ArrayList<Seat>();
 * priceSeats.add(new Seat("A00", 10.0));
 * priceSeats.add(new Seat("B00", 15.0));
 * Collections.sort(priceSeats, BaseComparableComparator.PRICE_ORDER);  // 指定要排序的逻辑
 * 源码定义：
 * public static <T> void sort(List<T> list, Comparator<? super T> c) {  list.sort(c); } ===> T类型可以不通过实现Comparable<T>来比较 !!!
 */

// 方式一: Create a comparator object within an class
public class BaseComparableComparator {

    // 定义一个内部的const比较器, 用来指定类型T的List在比较时候所遵循的条件和逻辑
    public static final Comparator<Seat> PRICE_ORDER;

    static {
        // 静态构造器中初始化常量 ==> 使用匿名类型 (实现了Comparator<T>接口的类型) !!!
        PRICE_ORDER = new Comparator<Seat>() {
            @Override
            public int compare(Seat seat1, Seat seat2) {
                if (seat1.getPrice() < seat2.getPrice()) {
                    return -1;
                } else if (seat1.getPrice() > seat2.getPrice()) {
                    return 1;
                }
                return 0; // 实现的equal只体现在价格, 而非对象本身
            }
        };
    }
}

// 方式二: Create a new class of implements Comparator
class Test implements Comparator<Seat> {

    @Override
    public int compare(Seat seat1, Seat seat2) {
        if (seat1.getPrice() < seat2.getPrice()) {
            return -1;
        } else if (seat1.getPrice() > seat2.getPrice()) {
            return 1;
        }
        return 0;
    }
}

class TestMain {

    public static void main(String[] args) {
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat("25", 12.0));
        seats.add(new Seat("22", 11.0));
        Test test = new Test();
        // Collections.sort(seats, Comparator.comparing(Seat::getPrice, new Test()));
    }

}
