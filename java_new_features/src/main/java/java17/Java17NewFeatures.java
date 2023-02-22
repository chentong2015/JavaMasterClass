package java17;

// TODO. Java Sealed Class https://openjdk.org/jeps/409
public class Java17NewFeatures {

    // Switch expressions : "switch" can be used as an expression
    // 将整个Switch写成一行的表达式
    public void testSwitch(Day day) {
        int numLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case THURSDAY, SATURDAY -> 8;
            case WEDNESDAY -> 9;
        };
    }

    // Switch Case可以写成表达式返回
    public String formatterPatternSwitch(Object o) {
        return switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> o.toString();
        };
    }

    // Text blocks : Support of "text blocks" with multiline strings
    public void testBlock() {
        String html = """
                <html>
                    <body>
                        <p>Hello, world</p>
                    </body>
                </html>
                """;
    }

    // Pattern matching : Avoid casting subsequent to instanceof
    // 在判断instanceof的同时自定完成类型转换(如果满足则强转)
    public void testPattern(Object obj) {
        if (obj instanceof String s) {
            // can use s here. No need to cast
            int length = s.length();
        } else {
            // can't use s here
        }
    }

    // NullPointerException错误信息中将会提示具体为null的变量
    // java.lang.NullPointerException: Cannot invoke "String.length()" because "value" is null
    public void testNullPointerException() {
        String value = null;
        System.out.println("ok");
        System.out.println(value.length());
    }
}
