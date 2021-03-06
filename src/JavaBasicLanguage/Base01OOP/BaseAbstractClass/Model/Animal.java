package JavaBasicLanguage.Base01OOP.BaseAbstractClass.Model;

// abstract 不能够实例化的抽象类型  ===> 提供一种抽象的机制，提取共性，可以实现部分抽象
// provide a common definition of a base class that multiple derived classes can share !!!
public abstract class Animal {

    // 1. 抽象类中的可以包含被继承的成员变量
    //    非static也可以
    private String name;

    // 2. 可以在抽象类中声明自定义构造器 ==> 不能设置成private的权限 !! 子类继承在实现的时候，必须调到这里的含参构造器
    //    提供的默认无参构造器是protected的
    public Animal(String name) {
        this.name = name;
    }

    // 3. 至少包含一个抽象方法，没有被实现的方法
    //    以下都是动物的基本的行为方法，不应该使用接口的逻辑来实现 !!!
    public abstract void eat();

    public abstract void breath();

    // 4. 可以包含声明好的具体方法
    // 5. 成员可以含有不同的访问权限 !!!
    public String getName() {
        return name;
    }
}
