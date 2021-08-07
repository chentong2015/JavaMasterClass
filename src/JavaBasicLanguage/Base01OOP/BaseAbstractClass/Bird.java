package JavaBasicLanguage.Base01OOP.BaseAbstractClass;

import JavaBasicLanguage.Base01OOP.BaseAbstractClass.animal.Animal;
import JavaBasicLanguage.Base01OOP.BaseAbstractClass.bird.IFly;

public class Bird extends Animal implements IFly {

    public Bird(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " is eating ");
    }
    
    // 抽象出来的fly方法，针对具体类型的鸟类具有不同的实现 !!!
    // 不能在子类中被直接调用，因为未实现
    // 问题是，非鸟类，也有具有飞行的能力 ==> Can-do
    // public abstract void fly();

    @Override
    public void fly() {
        System.out.println("Bird base fly ...");
    }
}
