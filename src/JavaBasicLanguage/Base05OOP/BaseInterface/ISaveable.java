package JavaBasicLanguage.Base05OOP.BaseInterface;

import java.util.List;

// 1. 接口可以继承自另外一个接口 !!!!
public interface ISaveable extends ITelephone {

    // 2. 使用泛型接口, 最大限度的解耦, 增加可扩展性
    List<String> write();

    void read(List<String> savedValues);

    // 3. 使用default关键字声明一个方法  ==> 被implements的时候，不用再重写   > Java 8
    default void testDefaultMethod() {
        System.out.println("Can define a default method");
    }

    // 4. 使用static声明一个方法 ==> 被implements的时候，不用再重写          > Java 9
    static void testStaticMethod() {
        System.out.println("Can define a static method");
    }
}

//    public static void saveObject(ISaveable objectToSave) {
//        // 依次存储要存储对象中的数据
//        for (int i = 0; i < objectToSave.write().size(); i++) {
//            System.out.println("Saving " + objectToSave.write().get(i));
//        }
//    }
//
//    public static void loadObject(ISaveable objectToLoad) {
//        ArrayList<String> array = new ArrayList<>();
//        // 将数据加载到要加载的对象中
//        objectToLoad.read(array);
//    }