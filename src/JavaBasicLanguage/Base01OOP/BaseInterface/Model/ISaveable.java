package JavaBasicLanguage.Base01OOP.BaseInterface.Model;

import java.util.List;

public interface ISaveable extends ITelephone {

    // 1. 接口中不能声明以下语句块(当作初始化), 编译器会为接口生成"<clinit>()"类构造器, 用于初始化所定义的成员变量
    // static { }

    // 2. 使用泛型接口, 最大限度的解耦, 增加可扩展性
    List<String> write();

    void read(List<String> savedValues);

    // 3. 使用default关键字声明一个方法  ==> 被implements的时候，不用再重写   > Java 8
    // 已经提供了方法的默认实现
    default void testDefaultMethod() {
        System.out.println("Default method");
    }

    // 4. 使用static声明一个方法        ==> 被implements的时候，不用再重写   > Java 9
    // 可以声明静态的方法
    static void testStaticMethod() {
        System.out.println("Static method");
    }
}

//  对接口的实际使用
//  public static void saveObject(ISaveable objectToSave) {
//      // 依次存储要存储对象中的数据
//      for (int i = 0; i < objectToSave.write().size(); i++) {
//          System.out.println("Saving " + objectToSave.write().get(i));
//      }
//  }

//  public static void loadObject(ISaveable objectToLoad) {
//      ArrayList<String> array = new ArrayList<>();
//      // 将数据加载到要加载的对象中
//      objectToLoad.read(array);
//  }