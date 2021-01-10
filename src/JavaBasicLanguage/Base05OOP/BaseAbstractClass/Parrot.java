package JavaBasicLanguage.Base05OOP.BaseAbstractClass;

public class Parrot extends Bird {

    public Parrot(String name) {
        super(name);
    }

    // 对父类实现的方法进行重写，同时可以调用到母类的方法(非抽象方法)
    @Override
    public void fly() {
        super.fly();
        System.out.println(getName() + " is flying ...");
    }
}
