package Enum.nested;

public enum NestedEnumDemo {

    IMPORT,
    MIGRATE,
    PATH;

    // 如果枚举类型仅限于当前类型中使用，可以考虑设计成嵌套Enum
    public static class Migration {

        private enum Mode {

            FirstMode(10),
            SecondMode(20);

            private final int value;

            Mode(int value) {
                this.value = value;
            }

            int getValue() {
                return value;
            }
        }

        public static int  getModeValue() {
             return Mode.FirstMode.getValue();
        }
    }
}
