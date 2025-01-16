package JavaGenericType.instance;

public class CreationInstance1 {

    // TODO. 具有泛型参数的类型在被实例化时，泛型参数需要被特化
    // Box类型是泛型类型Box<T>的原始类型(raw type)，类型参数为Object
    static class Box<T> {
        private T info;

        public void set(T t) {
            info = t;
        }
    }

    // TODO. Warning: Raw use of parameterized class 'Box'
    // - 应该使用泛型类型本身，而不是使用它的原始类型
    // - 泛型参数被特化后将会对实参进行Check，以保证类型一致
    // - 原始类型不会对实参做check，不会保证数据类型的安全性
    public static void main(String[] args) {
        Box<Integer> stringBox = new Box<>();
        stringBox.set(10);
        // stringBox.set("test"); 传递的参数必须是特化类型的参数

        Box rawBox = new Box<>();
        rawBox.set(100);
        rawBox.set("test"); // 原始类型在调用泛型方法时，不做类型验证

        Box box = new Box();
        stringBox = box;
        stringBox.set(20);
        // stringBox.set("test"); 变量的类型确定了实参类型的约束
    }
}
