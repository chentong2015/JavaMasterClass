package JavaBasicLanguage.Base03Enum;

// TODO: Enum 枚举类型 ==> 限制类型的取值范围; 同时能在编译的时候发现类型错误
// https://howtodoinjava.com/java/enum/enum-tutorial/

// 1. 基本的Enum枚举类型：只是包含定义的值
// 2. Enum中可以定义Field; 构造方法; 自定义方法                    ====> C#区别：枚举可以定义指定的数值，或者使用2机制的机制 !!!
public enum BaseEnum {

    PENNY(1),
    NICKEL(5),
    DIME(15),
    QUARTER(25);

    private final int value;

    BaseEnum(int value) {
        this.value = value;
    }

    // 通过value()方法来调用到对应的数值 !!!
    public int value() {
        return value;
    }
}

