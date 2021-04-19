package JavaBasicLanguage.Base01OOP.Inheritance;

public class Animal {

    private int size;
    private String name;

    // 这里的权限不能设置成 private ==> 如果是确定要被继承 !!
    public Animal() {
        // 默认的无参构造器 在继承链上被调用 !!!!
    }

    public Animal(int size, int weight, String name) {
        // this : to call current class members
        this.size = size;
        this.name = name;
    }

    public void eat() {
        System.out.println("Animal eat() called");
    }

    // protected 能够被子类访问到，并且重写的方法
    protected void eatPlus() throws NullPointerException {
        System.out.println("this is a test");
    }

    // 母类中声明的静态方法，属于这个类型
    public static void testClassMethod() {
        System.out.println("The static method in Animal");
    }
}
