package java16;

public class Java16NewFeatures {

    // Records represent Data structure classes.
    // Record instances are immutable
    // In a single line it "generates"
    //  - private final fields
    //  - default constructor
    //  - read accessors
    //  - equals/hashcode/tostring methods
    record Point(int x, int y) {
    }

    public void testRecord() {
        Point myPoint = new Point(3, 4);
        int x = myPoint.x;
        int y = myPoint.y();
    }
}
