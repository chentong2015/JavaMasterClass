package JavaBasicGrammar.BaseNestedClass;

public abstract class DataTransfer {

    protected String outsideName;

    static class InnerClass {

        public void testOutsideName() {
            // 不能直接调用外部的非静态属性
            // outsideName = "";

            // 可以调用外部静态的方法
            getName();
        }
    }

    // 静态的方法中不能调用非静态的成员
    private static String getName() {
        // return outsideName;
        return "null";
    }
}
