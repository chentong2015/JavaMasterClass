package JavaBasicOOP.BaseModifier.PackagePrivate.model;

public class PackagePrivateDemo {

    public static void main(String[] args) {
        MyClassA customBean = new MyClassA();
        customBean.state = "my state";
        System.out.println(customBean.state);
    }
}
