package JavaGenericType.model;

public class Box<T> {

    private T info;

    public void set(T t) {
        info = t;
    }

    public T getInfo() {
        return info;
    }
}
