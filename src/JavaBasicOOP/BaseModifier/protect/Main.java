package JavaBasicOOP.BaseModifier.protect;

import JavaBasicOOP.BaseModifier.protect.model.MyClassA;
import JavaBasicOOP.BaseModifier.protect.model.MyClassB;

public class Main {

    // 包外无法获取protected受保护的属性, 只能获取public属性
    public static void main(String[] args) {
        MyClassA myClassA = new MyClassB();
        // myClassA.name = "new name";
        myClassA.title = "new title";
    }
}
