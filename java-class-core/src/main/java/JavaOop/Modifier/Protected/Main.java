package JavaOop.Modifier.Protected;

import JavaOop.Modifier.Protected.model.MyClassA;
import JavaOop.Modifier.Protected.model.MyClassB;

public class Main {

    // 包外无法获取protected受保护的属性
    public static void main(String[] args) {
        MyClassA myClassA = new MyClassB();
        // myClassA.name = "new name";
        myClassA.title = "new title";
    }
}
