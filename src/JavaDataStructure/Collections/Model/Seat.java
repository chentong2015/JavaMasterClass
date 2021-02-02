package JavaDataStructure.Collections.Model;

import java.util.Comparator;

// 通过实现Comparable接口，可以使用优化排序和查找的collection集合数据类型
public class Seat implements Comparable<Seat>, Comparator<Seat> {

    private final String seatNumber;
    private double price;
    private boolean reserved = false;

    public Seat(String seatNumber, double price) {
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public boolean reserve() {
        if (!reserved) {
            reserved = true;
            return true;
        }
        return false;
    }

    // 自定义比较类型：判断条件是什么(结合多个属性), 同时返回int值 <0 =0 >0 三种情况
    @Override
    public int compareTo(Seat seat) {
        return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
    }

    // 使用Comparator<T>的另外一种方式: 实现自定义的比较逻辑 !!
    @Override
    public int compare(Seat o1, Seat o2) {
        return 0;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }
}