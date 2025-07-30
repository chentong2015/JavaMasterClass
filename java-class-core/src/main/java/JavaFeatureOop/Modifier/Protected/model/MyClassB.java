package JavaFeatureOop.Modifier.Protected.model;

public class MyClassB extends MyClassA {

    // 同包下的继承类可以获取父类的protected属性
    public void testProtectedProperty() {
        this.name = "new name";
        this.title = "new title";
    }
}
