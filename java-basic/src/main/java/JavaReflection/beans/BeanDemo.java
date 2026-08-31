package JavaReflection.beans;

public class BeanDemo {

    private int id;
    private String name;

    public BeanDemo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // 提供属性获取的Getter方法
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
