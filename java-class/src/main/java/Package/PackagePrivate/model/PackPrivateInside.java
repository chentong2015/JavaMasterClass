package Package.PackagePrivate.model;

public class PackPrivateInside {

    public static void main(String[] args) {
        MyClassA customBean = new MyClassA();
        customBean.state = "my state";
        System.out.println(customBean.state);
    }
}
