package JavaOOP.Enum;

import JavaOOP.Enum.dbtype.EnumDBType;

public class EnumTesting {

    public static void main(String[] args) {
        // TODO. Enum枚举类型包含两个特定静态方法 .valueOf() .values()
        // 枚举类型的字面名称就是它的name
        // 可以通过name来获取特对特定的枚举类型, 未定义的name会抛出异常
        EnumDBType dbType1 = EnumDBType.valueOf("name");
        EnumDBType[] enumDBTypes = EnumDBType.values();

        // Switch在比较时自动调用ordinal()方法比较值
        EnumAction action = EnumAction.valueOf("sample");
        switch (action) {
            case AUTO -> System.out.println("check auto");
            case MANUAL -> System.out.println("check manual");
            case SKIP -> System.out.println("check skip");
            default -> System.out.println("error");
        }

        // TODO. 调用枚举类型默认包含的内部方法.name() .ordinal()
        System.out.println(action.name());
        System.out.println(action.ordinal());
    }
}
