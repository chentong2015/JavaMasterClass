package NewFeature;

public class SwitchExpressions {

    // JDK 14: "switch" can be used as an expression
    public void testSwitch(Day day) {
        int numLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case THURSDAY, SATURDAY -> 8;
            case WEDNESDAY -> 9;
        };
    }

    enum Day {
        MONDAY,
        FRIDAY,
        SUNDAY,
        TUESDAY,
        THURSDAY,
        SATURDAY,
        WEDNESDAY,
    }
}
