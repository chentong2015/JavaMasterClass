package JavaBasicLanguage.Base03Enum;

// Enum 枚举: 一种Class类型
// 1. 限制类型的取值范围(如果某参数或者变量只能取到特定的值范围，则优先考虑构建枚举类型)
// 2. 在编译的时候发现类型错误

// TODO: 两种关于Enum的数据结构
// 1. java.util.EnumSet
//    Set enumSet = EnumSet.of(EnumBasic.OK, EnumBasic.NOK);
//    for (Object item : enumSet) {
//        System.out.println(item);
//    }
// 2. java.util.EnumMap
//    Map enumMap = new EnumMap(EnumBasic.class);
//    enumMap.put(EnumBasic.OK, "OK");
//    System.out.println(enumMap.get(EnumBasic.OK));
public enum EnumBasic {

    // 只定义特定的枚举值，可以通过ordinal()方法返回枚举值的index位置(从0开始)
    OK,
    NOK,
    CHECK;
}
