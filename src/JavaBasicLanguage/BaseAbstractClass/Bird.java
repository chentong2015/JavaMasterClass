package JavaBasicLanguage.BaseAbstractClass;

import JavaBasicLanguage.BaseAbstractClass.model.Animal;
import JavaBasicLanguage.BaseAbstractClass.model.IFly;

// 抽象类继承自抽象类，均不能实例化
// 实现类型的同时，继承指定的接口(表示具备指定的能力)
public abstract class Bird extends Animal implements IFly {

    // TODO. 抽象类的构造器
    // 1. 抽象类的构造器通常设置成protected或者是package-private
    // 2. 当父类构造器含有参数时，子类的构造必须调用父类的构造器
    protected Bird(String name) {
        super(name);
    }

    @Override
    public void eat() {
        // super.eat(); 不能直接调用未实现的抽象方法
        System.out.println(getName() + " is eating ");
    }

    @Override
    public void fly() {
        System.out.println("Bird base fly ...");
    }
}
