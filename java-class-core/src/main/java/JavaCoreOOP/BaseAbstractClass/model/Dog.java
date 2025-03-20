package JavaCoreOOP.BaseAbstractClass.model;

public abstract class Dog extends AbstractAnimal {

    protected Dog(String name) {
        super(name);
    }

    // 实现抽象类型中的抽象方法，推荐写上@Override注解
    @Override
    public void eat() {
        // 使用从抽象类型中继承下来的实现了的方法
        String name = getName();
        System.out.println(name + " is eating");
    }
}
