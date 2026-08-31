package main.features.java16;

// TODO. class中的一种数据结构，其中包含特定的一些属性和基础方法
// Records represent Data structure classes.
// Record instances are immutable, it "generates"
//  - private final fields
//  - default constructor
//  - read accessors
//  - equals/hashcode/toString methods
public class RecordClass {

    record Point(int x, int y) {
    }

    public static void main(String[] args) {
        Point myPoint = new Point(3, 4);
        System.out.println(myPoint.x);
        System.out.println(myPoint.y());
    }
}
