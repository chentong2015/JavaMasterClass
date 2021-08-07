package JavaBasicLanguage.Base01OOP.BaseAbstractClass.animal;

public class Dog extends Animal {

    public Dog(String name) {
        // 同样调用母类抽象类的构造器来初始化, AbstractClass也是Super Class
        super(name);
    }

    // 实现抽象类型中的抽象方法，可以不写@Override         ===> C#中也是使用override来实现纯虚方法 !!
    @Override
    public void eat() {
        // 使用从抽象类型中继承下来的实现了的方法 getName()
        System.out.println(getName() + " is eating");
    }

}
