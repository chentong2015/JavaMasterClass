package JavaBasicOOP.BaseEnum;

public class EnumSampleTest {

    // 默认枚举类型带有int序列值
    enum SampleAction {
        SKIP, // 0
        AUTO, // 1
        MANUAL // 2
    }

    // 无法获取一个没有定义"名称"的枚举类型值，否则抛出IllegalArgumentException异常
    // Switch在比较的时候调用Enum的ordinal()方法比较值
    private static void testEnumSwitch(String enumName) {
        SampleAction action1 = SampleAction.valueOf(enumName);
        switch (action1) {
            case AUTO -> System.out.println("check auto");
            case MANUAL -> System.out.println("check manual");
            case SKIP -> System.out.println("check skip");
            default -> System.out.println("error");
        }
    }

    // 枚举类型默认的Internal方法
    public static void main(String[] args) {
        // TODO. Enum类型不能被实例化，没有构造器的概念
        // SampleAction action0 = new SampleAction();

        SampleAction[] sampleActions = SampleAction.values();
        System.out.println(sampleActions.length);

        SampleAction action = SampleAction.AUTO;

        System.out.println(action.name());    // AUTO
        System.out.println(action.ordinal()); // 1
    }
}
