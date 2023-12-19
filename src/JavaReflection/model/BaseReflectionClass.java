package JavaReflection.model;

public class BaseReflectionClass {

    private String name;

    public BaseReflectionClass() {
    }

    public BaseReflectionClass(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printString(String value) {
        System.out.println("print something: " + value);
    }
}
