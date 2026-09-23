package java17.sealed_class.shape;

public sealed class Rectangle implements Shape permits Square {

    private final double length;
    private final double height;

    public Rectangle(double length, double height) {
        this.length = length;
        this.height = height;
    }

    @Override
    public double area() {
        return length * height;
    }

}
