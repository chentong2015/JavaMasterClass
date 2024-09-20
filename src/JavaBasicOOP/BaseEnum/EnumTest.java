package JavaBasicOOP.BaseEnum;

// Enum 枚举: 一种Class类型，和类声明一致
// 1. 限制类型的取值范围(如果参数或者变量只能取到特定范围，则优先考虑构建枚举类型)
// 2. 有利于在编译的时候发现类型错误
public class EnumTest {

    enum SampleAction {
        SKIP, // 0 默认枚举类型带有int序列值
        AUTO, // 1
        MANUAL // 2
    }

    // TODO. Mock类型使用规范
    // 1. Enum类型不能被实例化，没有构造器的概念
    // 2. 枚举类型的字面名称就是它的name，可以通过name来获取特对特定的枚举类型。未定义的name会抛出异常
    // 3. 枚举类型本身包含特定的内部方法.values() .name() .ordinal()
    public static void main(String[] args) {
        // SampleAction action0 = new SampleAction();  KO
        EnumDBType dbType = EnumDBType.MS_SQL_SERVER;

        // Returns the enum constant of this type with the specified name.
        // The string must match exactly an identifier used to declare an enum constant in this type.
        EnumDBType dbType1 = EnumDBType.valueOf("name");

        // Switch在比较时自动调用ordinal()方法比较值
        SampleAction action = SampleAction.valueOf("sample");
        switch (action) {
            case AUTO -> System.out.println("check auto");
            case MANUAL -> System.out.println("check manual");
            case SKIP -> System.out.println("check skip");
            default -> System.out.println("error");
        }

        // 调用默认的Internal方法
        SampleAction[] sampleActions = SampleAction.values();
        System.out.println(action.name());
        System.out.println(action.ordinal());
    }
}
