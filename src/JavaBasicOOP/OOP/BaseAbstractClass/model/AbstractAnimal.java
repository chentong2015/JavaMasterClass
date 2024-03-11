package JavaBasicOOP.OOP.BaseAbstractClass.model;

import java.io.FileNotFoundException;
import java.sql.SQLException;

// 提供一种抽象的机制，提取共性，可以实现部分抽象
// Provide a common definition of a base class that multiple derived classes can share
// 1. 不能够实例化的抽象类型
// 2. 可以实现接口，可以继承具体类，也可以继承抽象类型
public abstract class AbstractAnimal {

    // 1. 抽象类中的可以包含被继承的成员变量
    private static String nickName;
    private String name;

    // TODO. 抽象类的构造器: 自定义含参构造器
    // 由于抽象类的继承类必须要能够调用父类的含参构造器
    // - private不能设置成私有，导致含参构造器无法被调用
    // - public语法上没错，但是不需要设置成公开，抽象类不可实例化，在外部公开调用它没有意义
    // - protected默认应该设置成受保护的权限，只允许在它的继承类中调用(包内 + 包外)
    // - package-private包私有权限，只能在当前的包路径下被访问到
    AbstractAnimal(String name) {
        this.name = name;
    }

    // TODO: 抽象类可以不包含抽象方法(不会报错)，但是一般会至少有一个未实现的方法
    // 1. 抽象方法不能是static静态的，因为静态方法不能再被重写
    // 2. 抽象方法不能是native本地方法，因为本地方法由本地代码（如C代码）实现的方法，而抽象方法目前还没有实现
    // 3. 抽象方法不能被synchronized修饰，因为还没有方法的实现细节，无法线程同步
    public abstract void eat();

    // TODO. 抽象方法定义抛出多种异常，在实现时可以只选择抛出其中一个异常
    // 抽象方法在实现时，所抛出的异常必须在定义的异常范围内
    public abstract String getDataString() throws SQLException, FileNotFoundException;

    // 4. 可以包含声明好的具体方法，并含有不同的访问权限
    public String getName() {
        return name;
    }
}
