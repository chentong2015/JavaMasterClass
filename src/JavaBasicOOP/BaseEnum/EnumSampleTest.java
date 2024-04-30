package JavaBasicOOP.BaseEnum.sample;

public class EnumSampleTest {

    enum SampleAction {
        SKIP,
        AUTO,
        MANUAL
    }

    public static void main(String[] args) {
       SampleAction action = SampleAction.AUTO;
        // 枚举类型默认带有两个Internal方法
        System.out.println(action.name());    // AUTO
        System.out.println(action.ordinal()); // 1

        // java.lang.IllegalArgumentException
        SampleAction action1 = SampleAction.valueOf("Test");
        switch (action1) {
            case AUTO -> System.out.println("check auto");
            case MANUAL -> System.out.println("check manual");
            case SKIP -> System.out.println("check skip");
            default -> System.out.println("error");
        }
    }

}
