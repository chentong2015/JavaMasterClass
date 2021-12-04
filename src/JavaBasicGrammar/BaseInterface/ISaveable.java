package JavaBasicGrammar.BaseInterface;

import java.util.List;

public interface ISaveable extends ITelephone {

    // 1. 接口中不能使用静态语句块 static { }
    //    编译器会为接口生成"<clinit>()"方法, 完成变量初始化的赋值
    //    执行接口的<clinit>()方法时，不需要先执行父接口的<clinit>()方法

    // 2. 使用泛型接口, 最大限度的解耦, 增加可扩展性
    List<String> write();

    void read(List<String> savedValues);

    // 3. 使用default关键字声明一个方法, 提供方法的默认实现
    //    被implements的时候，不用再重写   > Java 8
    default void testDefaultMethod() {
        System.out.println("Default method");
    }

    // 4. 使用static声明一个方法, 提供方法的默认实现
    //    被implements的时候，不用再重写   > Java 9
    static void testStaticMethod() {
        System.out.println("Static method");
    }
}

//  对接口的实际使用: 读取加载，存储数据
//  public static void loadObject(ISaveable objectToLoad) {
//      ArrayList<String> array = new ArrayList<>();
//      objectToLoad.read(array);
//  }
//
//  public static void saveObject(ISaveable objectToSave) {
//      ArrayList<String> result = objectToSave.write();
//      for (int i = 0; i < result.size(); i++) {
//          System.out.println("Saving " + result.get(i));
//      }
//  }