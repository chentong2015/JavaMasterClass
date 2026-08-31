package Package.Protected;

import Package.Protected.model.MyClassA;
import Package.Protected.model.MyClassB;

public class ProtectedOutside {

    // 包外无法获取protected受保护的属性
    public static void main(String[] args) {
        MyClassA myClassA = new MyClassB();
        // myClassA.name = "new name";
        myClassA.title = "new title";
    }
}
