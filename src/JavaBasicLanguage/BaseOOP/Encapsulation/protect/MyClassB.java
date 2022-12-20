package JavaBasicLanguage.BaseOOP.Encapsulation.protect;

public class MyClassB extends MyClassA {

    // 继承类是可以获取父类的protected和public属性
    public void testProtectedProperty() {
        this.name = "new name";
        this.title = "new title";
    }
}
