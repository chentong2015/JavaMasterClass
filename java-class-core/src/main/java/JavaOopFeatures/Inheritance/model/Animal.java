package JavaOopFeatures.Inheritance.model;

// TODO. 为了继承而设计的类型，子类和父类必须符合"IS-A"的关系
public class Animal {

    private int size;
    private String name;

    // 默认的无参构造器 在继承链上被调用
    public Animal() {
        this(100, "test");
    }

    public Animal(int size, String name) {
        this.size = size;
        this.name = name;
        // 注意父类构造器中调用的可被override的方法，可能导致父类的构造行为出错 !!
    }

    // 受保护的构造器只能在同一个包内或继承链上被调用
    // protected AbstractAnimal() { }

    // 受保护的方法成员，只能在当前package或者它的子类中被访问并重写
    protected void testProtectedMethod() {
    }

    protected void eatPlus() {
    }

    // TODO: Java中默认实例方法都是虚方法，可以被继承类型重写的，除非使用final关键字
    public void eat() {
        System.out.println("AbstractAnimal eat() called");
    }

    // TODO. 静态方法属于母类本身，必须通过母类名称来进行调用
    // 该static方法不能在子类中进行override
    public static void testStaticMethod() {
        System.out.println("Static method of super class");
    }
}
