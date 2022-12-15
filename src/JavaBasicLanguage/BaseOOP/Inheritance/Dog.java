package JavaBasicLanguage.BaseOOP.Inheritance;

import java.io.FileWriter;
import java.io.IOException;

// Dod is a Animal 构成的是IS-A的关系 !!
public class Dog extends Animal {

    private int weight;
    private String extraName;

    public Dog() {
        // 无参构造器中会默认调用super()，并且一定是在第一行, 必须先"构造"母类
        // The call to super() must be the first statement in each constructor
    }

    // 构造器的正确使用方式：一个构造器只能调用一个this()或者是super()
    public Dog(int size, int weight, String name) {
        this(size, weight, name, "extra name");
    }

    // 数据的初始化应该交给一个完整的参数的构造器来完成，避免代码的重复性
    public Dog(int size, int weight, String name, String extraName) {
        // Super : to call the parent class members 指定调用母类声明的方法
        // super() 调用继承的类(super class)的构造器
        super(size, name);
        this.weight = weight;
        this.extraName = extraName;
    }

    // TODO: @Override 重写母类的方法
    //       Runtime Polymorphism运行时的多态性(由JVM来决定最终调用)
    // 0. 只能使用在子类中
    // 1. 不能够重写Static静态方法, 构造器, 私有方法, final修饰的方法
    // 2. 必须和母类有相同的方法签名
    //    2.1 重写方法修饰符不能用于更低的访问性
    //    2.2 重写方法的返回类型(协变量)可以是母类方法返回类型的"子类"
    //        String -> Object，向下的类型必须兼容，否则抛出异常 !!
    //    2.3 重写方法的方法签名上如果是抛出"check exception", 那么母类必须抛出同样的Exception
    //        如果抛出"uncheck exception"，则子类可以使用相同的异常，或者不使用异常
    @Override
    public void eat() throws IllegalStateException {
        super.eat();
        System.out.println("Dog eat() called");
    }

    // 1. 不能在子类中抛出母类方法没有抛出的"check exception"
    //    如果要添加"check exception"则父类也必须添加异常
    // 2. 重写方法中如果必须抛出"check exception", 可以在内部使用handler来捕获和处理
    @Override
    protected void eatPlus() { // throws Exception
        FileWriter localFile = null;
        try {
            localFile = new FileWriter("locations.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            localFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hide隐藏(覆盖)母类中声明的静态方法
    public static void testClassMethod() {
        // Hide
        System.out.println("Hide static method of super class");
    }
}
