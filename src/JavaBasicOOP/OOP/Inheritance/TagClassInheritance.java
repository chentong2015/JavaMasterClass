package JavaBasicOOP.OOP.Inheritance;

// 使用标签类设计来替换“继承关系的类层次”
public class TagClassInheritance {

    enum Shape {RECTANGLE, CIRCLE}

    // Tag field - the shape of this figure
    final Shape shape;
    // These fields are used only if shape is RECTANGLE
    double length;
    double width;
    // This field is used only if shape is CIRCLE
    double radius;

    // Constructor for circle
    TagClassInheritance(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    // Constructor for rectangle
    TagClassInheritance(double length, double width) {
        shape = Shape.RECTANGLE;
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
