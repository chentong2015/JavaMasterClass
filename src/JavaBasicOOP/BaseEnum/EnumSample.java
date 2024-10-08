package JavaBasicOOP.BaseEnum;

// 1. 枚举值可以定义Field属性, 通过构造方法初始化，调用枚举中的方法来获取属性值
// 2. 可以定义实例方法或抽象方法(每个枚举值都需要实现@Override)
public enum EnumSample {

    // 只定义特定的枚举值，可以通过ordinal()方法返回枚举值的index位置(从0开始)
    PENNY(1),
    NICKEL(5),
    DIME(15),
    QUARTER(25);

    private final int value;

    EnumSample(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public void printEnumValue() {
        String message = "The value is: " + this;
        System.out.println(message);
    }
}

