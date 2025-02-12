package JavaBasicOOP.OOP.Inheritance.samples;

// 使用标签类设计来替换“继承关系的类层次”
public class TagClassImpl {

    enum ShapeTag {
        RECTANGLE,
        CIRCLE
    }
    final ShapeTag shape;

    // Fields for RECTANGLE
    double length;
    double width;
    // Field for CIRCLE
    double radius;

    TagClassImpl(double radius) {
        this.shape = ShapeTag.CIRCLE;
        this.radius = radius;
    }

    TagClassImpl(double length, double width) {
        this.shape = ShapeTag.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    double area() {
        return switch (shape) {
            case RECTANGLE -> length * width;
            case CIRCLE -> Math.PI * (radius * radius);
            default -> throw new AssertionError(shape);
        };
    }
}
