package JavaBasicLanguage.Base05OOP.Inheritance;

// Dod is a Animal 构成的是IS-A的关系 !!!
public class Dog extends Animal {

    private int tail;
    private String coat;

    public Dog() {
        // 无参构造器中会默认调用super()
        // The call to super() must be the first statement in each constructor
        // super() 的调用一定是在第一行, 必须先"构造"母类 !!!
    }

    // 构造器的正确使用方式：一个构造器只能调用一个this()或者是super() !!!
    public Dog(int size, int weight, String name) {
        this(size, weight, name, 0, "Test");
    }

    // 数据的初始化应该交给一个完整的参数的构造器来完成 !!!!  ===> 避免代码的重复性
    public Dog(int size, int weight, String name, int tail, String coat) {
        // Super : to call the parent class members
        // super() 调用继承的类(super class)的构造器
        super(size, weight, name);
        this.tail = tail;
        this.coat = coat;
    }

    // 子类所独有的方法/行为
    private void walk() {
        super.move(10); // 指定调用母类声明的方法 !!! 如果没有被重写，这不需要使用super
        System.out.println("Dog walk() called");
    }

    private void run() {
        move(10); // 调用已经被重写Override的方法 !!!
        System.out.println("Dog run() called");
    }

    // *****  Runtime Polymorphism 运行时的多态性，在运行的时候由JVM来决定最终的调用  *******
    // @Override (annotation 注解) 重写母类的方法
    // 0. 只能使用在子类中
    // 1. 必须和母类一样的方法签名, 抛出的异常也必须一致
    // 2. 不能够重写Static静态方法, 构造器, 私有方法, final修饰的方法
    // 3. 重载方法的返回类型可以是原来方法的返回类型的"子类": covariant return type ==> 协变量(返回类型) !!!!
    // 4. 重载的方法不能比原来的方法拥有更低的可访问性 protected -> public 而不能是 private !!
    @Override
    public void eat() {
        super.eat();
        System.out.println("Dog eat() called");
    }

    // 构成一个方法的重载：可以是重载自身的方法，也可以重载继承来的方法 !!!
    public void eat(int id) {
        System.out.println("Test overloading ");
    }

    @Override
    public void move(int speed) {
        System.out.println("Dog move() called");
    }

}
