package Package.PackagePrivate;

import Package.PackagePrivate.model.MyClassA;

public class PackPrivateOutside {

    // 包外的对象无法访问"包私有"的属性
    public static void main(String[] args) {
        MyClassA customBean = new MyClassA();
        // custom.state
    }
}
