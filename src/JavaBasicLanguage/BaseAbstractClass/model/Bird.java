package JavaBasicLanguage.BaseAbstractClass.model;

// 抽象类继承自抽象类，均不能实例化
// 实现类型的同时，继承指定的接口(表示具备指定的能力)
public abstract class Bird extends AbstractAnimal implements IFly {

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
