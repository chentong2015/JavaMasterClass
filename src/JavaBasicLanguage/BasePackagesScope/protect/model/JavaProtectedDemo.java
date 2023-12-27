package JavaBasicLanguage.BasePackagesScope.protect.model;

public class JavaProtectedDemo {

    // 同一个package中可以获取protected和public属性
    public static void main(String[] args) {
        MyClassA myClassA = new MyClassB();
        myClassA.name = "new name";
        myClassA.title = "new title";
    }
}
