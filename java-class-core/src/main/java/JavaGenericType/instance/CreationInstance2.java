package JavaGenericType.instance;

public class CreationInstance2<T> {

    static class TypeInstance<T> {
        private T key;

        private TypeInstance() {
        }

        private TypeInstance(T key) {
            this.key = key;
        }

        // TODO. 创建一个泛型类型的实例对象, 使用Row Type类型作为对象引用
        public static TypeInstance create() {
            return new TypeInstance<>();
        }

        // TODO. 创建一个泛型类型的实例对象, 使用泛型类型作为对象的引用 => 推荐方式
        public static <T> TypeInstance<T> createObject() {
            return new TypeInstance<T>();
        }

        public void testType(T value) {
            System.out.println(value);
        }
    }

    public static void main(String[] args) {
        // 在创建类型对象时(调用构造器)，会根据实际的参数进行类型推断: <T>被推断成Integer
        TypeInstance<Integer> typeInstance = new TypeInstance<>(10);
        typeInstance.testType(20);

        // 使用Raw Type作为变量类型，将会做uncheck call
        TypeInstance typeInstance1 = TypeInstance.create();
        typeInstance1.testType(30);
        typeInstance1.testType("test");

        TypeInstance<Integer> typeInstance2 = TypeInstance.createObject();
        typeInstance2.testType(40);
        // typeInstance2.testType("test"); check call
    }
}
