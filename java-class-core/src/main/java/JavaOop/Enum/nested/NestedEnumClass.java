package JavaOop.Enum.nested;

public class NestedEnumClass {

    // 枚举类型可以定义为嵌套类
    public enum ResourceType {
        XML {
            @Override
            String getTypeString() {
                return "xml";
            }
        },
        FLAT_XML {
            @Override
            String getTypeString() {
                return "flat xml";
            }
        },
        CSV {
            @Override
            String getTypeString() {
                return "csv";
            }
        };

        // TODO. 枚举类型中可以定义抽象方法，并且所有枚举类型都需要实现该方法
        abstract String getTypeString();
    }

    public static void main(String[] args) {
        NestedEnumClass.ResourceType resourceType = ResourceType.CSV;
        System.out.println(resourceType.getTypeString());
    }
}
