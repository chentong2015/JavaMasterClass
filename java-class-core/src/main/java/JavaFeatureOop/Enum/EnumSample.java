package JavaFeatureOop.Enum;

public enum EnumSample {

    PENNY(1),
    NICKEL(5),
    DIME(15),
    QUARTER(25);

    // 枚举类型可以定义Field属性
    private final int value;

    // TODO. Enum类型不能被实例化，构造器默认就是私有的
    // Enum类型可通过私有构造器初始化属性的数据
    EnumSample(int value) {
        this.value = value;
    }

    // 调用枚举中的方法来获取属性值
    public int getValue() {
        return value;
    }

    // 枚举类型定义抽象方法(每个枚举值都需要实现@Override)
    // abstract void absFunction();

    // 枚举类型定义实例方法
    public void printEnumValue() {
        String message = "The value is: " + this;
        System.out.println(message);
    }
}

