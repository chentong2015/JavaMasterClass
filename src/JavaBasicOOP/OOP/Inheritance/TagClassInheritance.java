package JavaBasicOOP.OOP.Inheritance;

// 使用标签类设计来替换“继承关系的类层次”
public class TagClassInheritance {

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

    TagClassInheritance(double radius) {
        this.shape = ShapeTag.CIRCLE;
        this.radius = radius;
    }

    TagClassInheritance(double length, double width) {
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
