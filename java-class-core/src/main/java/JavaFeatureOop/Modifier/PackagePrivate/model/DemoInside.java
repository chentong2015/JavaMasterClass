package JavaFeatureOop.Modifier.PackagePrivate.model;

public class DemoInside {

    public static void main(String[] args) {
        MyClassA customBean = new MyClassA();
        customBean.state = "my state";
        System.out.println(customBean.state);
    }
}
