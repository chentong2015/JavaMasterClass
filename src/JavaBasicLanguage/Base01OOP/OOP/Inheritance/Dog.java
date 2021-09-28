package JavaBasicLanguage.Base01OOP.OOP.Inheritance;

import java.io.FileWriter;
import java.io.IOException;

// Dod is a Animal 构成的是IS-A的关系 !!
public class Dog extends Animal {

    private int weight;
    private String extraName;

    public Dog() {
        // 无参构造器中会默认调用super()
        // The call to super() must be the first statement in each constructor
        // super() 的调用一定是在第一行, 必须先"构造"母类 !!!
    }

    // 构造器的正确使用方式：一个构造器只能调用一个this()或者是super() !!!
    public Dog(int size, int weight, String name) {
        this(size, weight, name, "extra name");
    }

    // 数据的初始化应该交给一个完整的参数的构造器来完成 !!!!  ===> 避免代码的重复性
    public Dog(int size, int weight, String name, String extraName) {
        // Super : to call the parent class members 指定调用母类声明的方法
        // super() 调用继承的类(super class)的构造器
        super(size, name);
        this.weight = weight;
        this.extraName = extraName;
    }

    // @Override (annotation 注解) 重写母类的方法: Runtime Polymorphism运行时的多态性(由JVM来决定最终调用)
    // 0. 只能使用在子类中
    // 1. 不能够重写Static静态方法, 构造器, 私有方法, final修饰的方法
    // 2. 必须和母类"一样的方法签名"
    // -  2.1 重写方法修饰符不能用于更低的访问性
    // -  2.2 重写方法的返回类型(协变量)可以是母类方法返回类型的"子类" String -> Object
    // -  2.3 重写方法的方法签名上如果是抛出"check exception", 那么母类必须抛出同样的Exception
    // -      如果抛出"uncheck exception"则没有影响
    // -      或者在重写方法内部使用handler来捕获和处理异常
    @Override
    public void eat() throws IllegalStateException {
        super.eat();
        System.out.println("Dog eat() called");
    }

    @Override
    protected void eatPlus() throws NullPointerException { // 追加抛出(可以忽略的)异常，母类方法没有该异常抛出 !!
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
        System.out.println("The static method in Animal");
    }
}
