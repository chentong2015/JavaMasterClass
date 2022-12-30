package JavaBasicLanguage.BaseOOP;

// TODO. 限制源文件单个顶级类，确保在编译时一个类不会有多个定义
//  同一个文件中，只能有一个public的类型声明，允许有多个非public class并存
public class BaseJavaClass {

    // 对于Fields封装, 允许内部访问, 作为状态stated的体现
    private int id;          // 默认值是0
    private String username; // 默认值是null

    // Constructor 自带默认的构造器
    // TODO: 当默认构造器被自定义改写之后，将不再隐式具有该默认构造器，必须显式的写出来
    public BaseJavaClass() {
        // 可以调用自定义的含参构造器: 设置默认的值
        this(100, "OtherTech");
    }

    // 添加自定义的含参构造器
    public BaseJavaClass(int id) {
        // this.id = id;
        this(id, "OtherTech");
    }

    // Constructor overloading 创建类型的实例的时候，会自动的找到指定的构造器
    public BaseJavaClass(int id, String username) {
        this.id = id;
        this.username = username;
    }

    // 对于普通的Getter和Setter 可以直接公开数据 Fields
    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    // 通过setter来做数据的约束和验证
    public void setUsername(String username) {
        String name = username.toLowerCase();
        if (name.equals("OtherTech")) {
            this.username = name;
        } else {
            this.username = "Unknown";
        }
    }
}
