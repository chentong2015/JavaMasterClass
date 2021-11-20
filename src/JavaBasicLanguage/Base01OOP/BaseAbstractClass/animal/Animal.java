package JavaBasicLanguage.Base01OOP.BaseAbstractClass.animal;

// 提供一种抽象的机制，提取共性，可以实现部分抽象
// provide a common definition of a base class that multiple derived classes can share
// 1. 不能够实例化的抽象类型
// 2. 可以实现接口，可以继承具体类，也可以继承抽象类型
public abstract class Animal {

    // 1. 抽象类中的可以包含被继承的成员变量
    private String nickName;
    private static String name;

    // 2. 可以在抽象类中声明自定义构造器
    //    不能设置成private的权限, 子类继承在实现的时候，必须调到这里的含参构造器
    //    提供的默认无参构造器是protected的
    public Animal(String name) {
        this.name = name;
    }

    // TODO: 抽象类可以不包含抽象方法(不会报错)，但是一般会至少有一个未实现的方法
    //  1. 抽象方法不能是静态的，因为静态方法不能再被重写
    //  2. 抽象方法不能是native本地方法，因为本地方法由本地代码（如C代码）实现的方法，而抽象方法目前还没有实现
    //  3. 抽象方法不能被synchronized修饰，因为还没有方法的实现细节，无法线程同步
    public abstract void eat();

    // 4. 可以包含声明好的具体方法
    // 5. 成员可以含有不同的访问权限
    public String getName() {
        return name;
    }
}
