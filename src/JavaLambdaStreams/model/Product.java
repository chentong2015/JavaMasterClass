package JavaLambdaStreams.model;

public class Product {
    final double price;
    final String name;

    public Product(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
