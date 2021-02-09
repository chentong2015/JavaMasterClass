package JavaBasicLanguage;

// TODO: Enum 枚举类型 ==> 限制类型的取值范围; 同时能在编译的时候发现类型错误
// 1. 基本的Enum枚举类型：只是包含定义的值
// 2. Enum中可以定义Field; 构造方法; 自定义方法                    ====> C#区别：枚举可以定义指定的数值，或者使用2机制的机制 !!!
public enum Base07Enum {
    // 对每一种类型添加一个附加值value
    // 一般可以结合switch语句一起使用 !!!
    PENNY(1),
    NICKEL(5),
    DIME(15),
    QUARTER(25);

    private final int value;

    Base07Enum(int value) {
        this.value = value;
    }

    // 通过value()方法来调用到对应的数值 !!!
    public int value() {
        return value;
    }
}

