package JavaFeatureOopClass.Inheritance;

import java.io.FileWriter;
import java.io.IOException;

// TODO. Java单继承语言: 最多只能继承一个父类
public class AnimalImpl extends Animal {

    private int weight;
    private String extraName;

    public AnimalImpl() {
        // 无参构造器中会默认调用super()，并且一定是在第一行, 必须先"构造"母类
        // The call to super() must be the first statement in each constructor
    }

    // 构造器的正确使用方式：一个构造器只能调用一个this()或者是super()
    public AnimalImpl(int size, int weight, String name) {
        this(size, weight, name, "extra name");
    }

    // 数据的初始化应该交给一个完整的参数的构造器来完成，避免代码的重复性
    public AnimalImpl(int size, int weight, String name, String extraName) {
        // Super : to call the parent class members 指定调用母类声明的方法
        // super() 调用继承的类(super class)的构造器
        super(size, name);
        this.weight = weight;
        this.extraName = extraName;
    }

    // TODO: @Override 重写母类方法: Runtime Polymorphism运行时的多态性(JVM来决定调用)
    // 0. 只能使用在子类中
    // 1. 不能够重写Static静态方法, 构造器, 私有方法, final修饰的方法
    // 2. 必须和母类有相同的方法签名
    //    - 重写方法修饰符不能用于更低的访问性
    //    - 重写方法的返回类型(协变量)
    //      可以是母类方法返回类型的"子类"
    //      String -> Object，向下的类型必须兼容，否则抛出异常
    //    - 重写方法的方法签名
    //      如果是抛出"check exception", 那么母类必须抛出同样的Exception
    //      如果抛出"uncheck exception"，则子类可以使用相同的异常，或者不使用异常
    // 3. 如果重写方法和母类方法完全一致，则无需重写 !!
    @Override
    public void eat() throws IllegalStateException {
        // TODO. 在子类中使用super.调用父类的方法，避免造成递归 !!
        super.eat();
        System.out.println("Dog eat() called");
    }

    // 1. 不能在子类中抛出母类方法没有抛出的"check exception", 导致父类方法必须添加相同异常
    // 2. 重写方法中如果必须抛出"check exception", 可以在内部使用handler来捕获和处理
    @Override
    protected void eatPlus() { // throws Exception
        FileWriter localFile = null;
        try {
            localFile = new FileWriter("WorkFolder/locations.txt");
        } catch (IOException e) {
            System.out.println("logging");
        }
        try {
            assert localFile != null;
            localFile.close();
        } catch (IOException e) {
            System.out.println("logging");
        }
    }

    // Hide隐藏(覆盖)母类中声明的静态方法
    // 不能够@Override重写目录的静态方法
    public static void testStaticMethod() {
        System.out.println("Hide static method of super class");
    }
}
