package JavaFeatureOop.Enum.enumap;

import java.util.EnumMap;
import java.util.Map;

public class JavaEnumMap {

    enum Field {
        ID(1), TYPE(3), USER(5);

        final int priority;

        Field(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }

    // TODO. 使用EnumMap来存储以Enum类型作为key的Map
    //  使用TreeMap没有办法获取以Enum为key的value值
    public static void main(String[] args) {
        Map<Field, String> infoMap = new EnumMap<>(Field.class);
        infoMap.put(Field.ID, "id");
        infoMap.put(Field.USER, "user");

        System.out.println(Field.ID.getPriority());
        System.out.println(infoMap.get(Field.USER));
    }
}
