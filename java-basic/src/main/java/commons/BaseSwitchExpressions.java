package commons;

public class BaseSwitchExpressions {

    // TODO. Switch-case语句通常判断Primitive Type原生类型(判断值)
    public static void testSwitchStatement(int switchValue, double longValue) {
        // switch不能判断long类型和double非精确类型
        // switch (longValue) {
        //     case 1:
        //         System.out.println("find 1");
        //         break;
        //     case 2:
        //         System.out.println("find 2");
        //         break;
        // }

        // switch不能判断null空(抛出NullPointerException)
        String value = null;
        switch (value) {
            case "A":
                System.out.println("AA");
            case "B":
                System.out.println("BB");
        }

        // switch-case判断后需要执行break跳出, 避免继续执行 !!
        switch (switchValue) {
            case 1:
                System.out.println("Find 1");
                break;
            case 2:
            case 3:
            case 4:
                // 支持整合多个case判断一起break
                System.out.println("Find 2 or 3 or 4");
                break;
            default:
                System.out.println("Find others ");
        }
    }

    // TODO. JDK 14: "switch" can be used as an expression
    public void testSwitch(Day day) {
        int numLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case THURSDAY, SATURDAY -> 8;
            case WEDNESDAY -> 9;
        };
    }

    enum Day {
        MONDAY, FRIDAY, SUNDAY, TUESDAY, THURSDAY, SATURDAY, WEDNESDAY,
    }

    // TODO. JDK 21: Pattern Matching 支持case类型匹配判断
    public String formatterPatternSwitch(String str, Object o) {
        switch (str) {
            case null  -> System.out.println("Oops");
            case "Foo", "Bar" -> System.out.println("Great");
            default    -> System.out.println("Ok");
        }

        return switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> o.toString();
        };
    }
}

