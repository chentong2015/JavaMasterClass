package JavaBasicLanguage.BaseOOP.Encapsulation.protect;

public class JavaProtectedDemo {

    // 类型的对象只能够获取protected和public属性
    public static void main(String[] args) {
        MyClassA myClassA = new MyClassB();
        myClassA.name = "new name";
        myClassA.title = "new title";
    }
}
