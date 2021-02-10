package JavaBasicLanguage.Base05OOP.BaseAbstractClass;

import JavaBasicLanguage.Base05OOP.BaseAbstractClass.Model.Animal;

public class Dog extends Animal {

    public Dog(String name) {
        // 在这里同样会调用母类抽象类的构造器 来初始化
        // AbstractClass也是Super Class
        super(name);
    }

    // 同样需要实现抽象类型中的抽象方法，可以不写 @Override ===> C#中也是使用override来实现纯虚方法 !!
    @Override
    public void eat() {
        // 使用从抽象类型中继承下来的实现了的方法 getName()
        System.out.println(getName() + " is eating");
    }

    @Override
    public void breath() {
        System.out.println(getName() + " is breathing ");
    }
}
