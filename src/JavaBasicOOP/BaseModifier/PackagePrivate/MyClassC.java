package JavaBasicOOP.BaseModifier.PackagePrivate;

import JavaBasicOOP.BaseModifier.PackagePrivate.model.MyClassA;

public class MyClassC extends MyClassA {

    // 包外的继承类无法访问"包私有"的属性
    public void test() {
        // System.out.println(state);
    }
}
