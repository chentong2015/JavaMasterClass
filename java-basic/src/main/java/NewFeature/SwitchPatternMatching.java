package NewFeature;

public class SwitchPatternMatching {

    // JDK 21: Pattern Matching for switch
    public String formatterPatternSwitch(Object o) {
        return switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> o.toString();
        };
    }

    // 允许做类型匹配判断
    static void testFooBarNew(String s) {
        switch (s) {
            case null  -> System.out.println("Oops");
            case "Foo", "Bar" -> System.out.println("Great");
            default    -> System.out.println("Ok");
        }
    }
}
