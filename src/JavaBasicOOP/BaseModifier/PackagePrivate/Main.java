package JavaBasicOOP.BaseModifier.PackagePrivate;

import JavaBasicOOP.BaseModifier.PackagePrivate.model.CustomBean;

public class Main {

    // 包私有的属性在包外是无法访问的
    public static void main(String[] args) {
        CustomBean customBean = new CustomBean();
        // custom.state
    }
}
