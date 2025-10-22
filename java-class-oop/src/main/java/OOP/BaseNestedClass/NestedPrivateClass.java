package OOP.BaseNestedClass;

public class NestedPrivateClass {

    // TODO. 外部类型可用访问内部类型的私有构造器
    public void test() {
        MemberClass memberClass = new MemberClass(10);
        System.out.println(memberClass.number);
        System.out.println(memberClass.getNumber());
    }

    class MemberClass {
        private int number;

        // 提供给外部类调用
        private MemberClass(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }
    }
}
