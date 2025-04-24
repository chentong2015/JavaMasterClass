package JavaOOP.FinalStatic;

import java.util.HashSet;
import java.util.Set;

public class BaseStaticTesting {

    private static Set<String> set = new HashSet<>();

    public static void addItem(String item) {
        set.add(item);
    }

    public static Set<String> getSet() {
        return set;
    }

    public static void cleanSet() {
        set.clear();
    }

    // TODO. 测试静态属性时需要清除之前测试的静态数据
    // @Test
    public void testAddItem1() {
        BaseStaticTesting.cleanSet();
        BaseStaticTesting.addItem("item1");
        System.out.println(BaseStaticTesting.getSet().size() == 1);
    }

    //@Test
    public void testAddItem2() {
        BaseStaticTesting.cleanSet();
        BaseStaticTesting.addItem("item2");
        System.out.println(BaseStaticTesting.getSet().size() == 1);
    }
}
