package JavaDataStructure.Collections.Compare;

import java.util.Comparator;

/**
 * 1. public interface Comparable<T>           ====> 直接实现compareTo()方法来指定排序时的比较判断逻辑
 * List<Seat> priceSeats = new ArrayList<Seat>();
 * priceSeats.add(new Seat("A00", 10.0));
 * priceSeats.add(new Seat("B00", 15.0));
 * Collections.sort(priceSeats);
 * .
 * 2. public interface Comparator<T>           ====> 通过实现compare()方法来定义排序list中item的逻辑
 * .                                                 T泛型并需要实现实现Comparable<T>接口
 * public static <T> void sort(List<T> list, Comparator<? super T> c) {}
 */

/**
 * 方式一: Create a comparator object within an class 使用方法成员(比较器)
 * Collections.sort(seats, BaseComparableComparator.PRICE_ORDER);
 */
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

/**
 * 方式二: Create a new class of implements Comparator 使用类型实例(比较器)
 * Collections.sort(seats, new SeatPriceComparator());
 */
class SeatPriceComparator implements Comparator<Seat> {

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

/**
 * 方式三: Lambda Expressions => Method Reference 使用方法引用
 * 1. Function<? super T, ? extends U> keyExtractor 提供比较的key键值的提取器, 同时方法对U泛型参数做了约束：U extends Comparable<? super U>
 * Collections.sort(seats, Comparator.comparing(Seat::getPrice)); 引用特定类型的"任意对象"的实例方法
 * .
 * 2. Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator 同时提供key键值的提取器, 和比较Key的方式 !!
 * Collections.sort(seats, Comparator.comparing(Seat::getPrice, new SeatPriceOrder()));
 */
// 这里的keyComparator比较器，可实现U类型的Comparator，或者它的任意上级类型super U
class SeatPriceOrder implements Comparator<Number> {

    @Override
    public int compare(Number o1, Number o2) {
        return 0;
    }

    // implements Comparator<Double>
    //    @Override
    //    public int compare(Double price1, Double price2) {
    //        if (price1 > price2) {
    //            return 1;
    //        } else if (price1 < price2) {
    //            return -1;
    //        }
    //        return 0;
    //    }
}
