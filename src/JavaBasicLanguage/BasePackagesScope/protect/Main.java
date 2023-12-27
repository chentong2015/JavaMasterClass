package JavaBasicLanguage.BasePackagesScope.protect;

import JavaBasicLanguage.BasePackagesScope.protect.model.MyClassA;
import JavaBasicLanguage.BasePackagesScope.protect.model.MyClassB;

public class Main {

    // 包外只能获取public属性，无法获取protected受保护的属性
    public static void main(String[] args) {
        MyClassA myClassA = new MyClassB();
        // myClassA.name = "new name";
        myClassA.title = "new title";
    }
}
