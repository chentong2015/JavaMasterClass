package OOP.Inheritance;

public class Animal {

    private int size;
    private String name;

    // 默认的无参构造器 在继承链上被调用
    public Animal() {
        this(100, "test");
    }

    // 在父类构造器中调用可被override的方法，可能导致父类的构造行为出错 !!
    public Animal(int size, String name) {
        this.size = size;
        this.name = name;
        testProtectedMethod();
    }

    // 受保护的构造器只能在同包内或继承链上被调用
    // protected AbstractAnimal() { }

    // 受保护的方法成员，只能在当前package或者它子类中被访问并重写
    protected void testProtectedMethod() {
    }

    protected void eatPlus() {
    }

    // TODO: 默认实例方法都是虚方法，可以被继承类型重写，除非使用final关键字
    public void eat() {
        System.out.println("AbstractAnimal eat() called");
    }

    // TODO. 静态方法属于母类本身，必须通过母类名称来进行调用
    // 该static方法不能在子类中进行override
    public static void testStaticMethod() {
        System.out.println("Static method of super class");
    }
}
