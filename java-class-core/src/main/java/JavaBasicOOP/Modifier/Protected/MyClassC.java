package JavaBasicOOP.Modifier.Protected;

import JavaBasicOOP.Modifier.Protected.model.MyClassA;

public class MyClassC extends MyClassA {

    // 包外的继承类可以获取父类的protected属性
    public void testProtectedProperty() {
        this.name = "new name";
        this.title = "new title";
    }
}
