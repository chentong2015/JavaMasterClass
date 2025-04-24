package JavaOOP.OOP.BaseAbstractClass;

import JavaOOP.OOP.BaseAbstractClass.model.Bird;

import java.sql.SQLException;

// 从抽象类再过度到具体的类型
public class Parrot extends Bird {

    public Parrot(String name) {
        super(name);
    }

    // 在重写抽象方法时，可以只抛出其中一个异常
    @Override
    public String getDataString() throws SQLException {
        return null;
    }

    // 对父类实现的方法进行重写，同时可以调用到母类的方法(非抽象方法)
    @Override
    public void fly() {
        super.fly();
        System.out.println(getName() + " is flying ...");
    }
}
