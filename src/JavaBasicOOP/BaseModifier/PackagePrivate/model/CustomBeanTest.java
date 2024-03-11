package JavaBasicOOP.BaseModifier.PackagePrivate.model;

public class CustomBeanTest {

    public static void main(String[] args) {
        CustomBean customBean = new CustomBean();
        customBean.state = "my state";
        System.out.println(customBean.state);
    }
}
