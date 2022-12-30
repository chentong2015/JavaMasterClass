package JavaBasicLanguage.BaseAbstractClass.animal;

// AbstractClass也是Super Class
public class Dog extends Animal {

    // 同样调用母类抽象类的构造器来初始化
    public Dog(String name) {
        super(name);
    }

    // 实现抽象类型中的抽象方法，可以不写@Override
    @Override
    public void eat() {
        // 使用从抽象类型中继承下来的实现了的方法 getName()
        System.out.println(getName() + " is eating");
    }

}
