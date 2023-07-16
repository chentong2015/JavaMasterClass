package JavaBasicLanguage.BaseAbstractClass.model;

public class Main {

    // 匿名类，对抽象类的直接实现，没有名称，不能重复使用
    public static void main(String[] args) {
        Animal animal = new Animal("name") {
            @Override
            public void eat() {
                // Impl method
            }
        };
        System.out.println(animal);
    }
}
