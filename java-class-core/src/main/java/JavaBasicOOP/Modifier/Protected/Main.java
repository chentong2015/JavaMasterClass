package JavaBasicOOP.Modifier.Protected;

import JavaBasicOOP.Modifier.Protected.model.MyClassA;
import JavaBasicOOP.Modifier.Protected.model.MyClassB;

public class Main {

    // 包外无法获取protected受保护的属性
    public static void main(String[] args) {
        MyClassA myClassA = new MyClassB();
        // myClassA.name = "new name";
        myClassA.title = "new title";
    }
}
