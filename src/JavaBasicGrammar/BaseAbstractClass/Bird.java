package JavaBasicGrammar.BaseAbstractClass;

import JavaBasicGrammar.BaseAbstractClass.animal.Animal;
import JavaBasicGrammar.BaseAbstractClass.bird.IFly;

// 实现类型的同时，继承指定的接口(表示具备指定的能力)
public class Bird extends Animal implements IFly {

    public Bird(String name) {
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
