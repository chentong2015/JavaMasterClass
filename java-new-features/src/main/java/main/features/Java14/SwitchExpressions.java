package main.features.Java14;

public class SwitchExpressions {

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
}
