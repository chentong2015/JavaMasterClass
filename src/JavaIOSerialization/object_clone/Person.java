package JavaIOSerialization.object_clone;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = -9102017020286042305L;

    private String name;
    private int age;
    private Car car;

    public Person(String name, int age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    public Car getCar() {
        return car;
    }
}
