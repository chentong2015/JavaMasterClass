package JavaBasicLanguage.Base01OOP;

// 0. User-defined data type 可以视为是用户自定义的数据类型
// 1. State 状态 -> fields
// 2. behaviors 行为 -> methods
// 3. 所有的class类型都是继承自java.long.Object类型 !!! extends Object
// 4. 可以声明final class 不能被继承的类型

// TODO Java 16: Sealed + Record Classes:
// Sealed classes and interfaces restrict which other classes or interfaces may extend or implement them.
// https://docs.oracle.com/en/java/javase/16/language/sealed-classes-and-interfaces.html
// 一种特殊的类，比常规类更少的ceremony来建模纯数据聚合
// https://docs.oracle.com/en/java/javase/16/language/records.html
public class BaseClass {

    // 对于Fields封装; 允许内部访问, 作为类型的状态state
    private int id; // 默认值是0
    private String username; // 默认值是null

    // Constructor 自带默认的构造器
    public BaseClass() {
        // 可以调用自定义的含参构造器 ===> 设置默认的值
        this(100, "OtherTech");
    }

    public BaseClass(int id) {
        // this.id = id;
        this(id, "OtherTech");
    }

    // Constructor overloading 创建类型的实例的时候，会自动的找到指定的构造器 !!
    public BaseClass(int id, String username) {
        this.id = id;
        this.username = username;
    }

    // 对于普通的Getter和Setter 可以直接公开数据 Fields !!
    // Getter & Setter ==> 使用工具自动的生成 Generate ...
    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    // 通过setter来做数据的约束和验证 validation
    public void setUsername(String username) {
        String name = username.toLowerCase();
        if (name.equals("OtherTech")) {
            this.username = name;
        } else {
            this.username = "Unknown";
        }
    }
}
