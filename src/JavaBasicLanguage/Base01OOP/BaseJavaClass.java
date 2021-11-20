package JavaBasicLanguage.Base01OOP;

// 0. User-defined data type用户自定义数据类型
// 1. State 状态 -> fields
// 2. behaviors 行为 -> methods
// 3. 所有类型都是继承自java.long.Object类型
// 4. final class表示不能被继承的类型

// TODO: modifier限定符的使用
// 同一个文件中，只能有一个public的类型声明
// 默认不写, 表示该类型只能在当前package中被访问
//                   public  protected  private   static
// Top Level Class:  YES     NO         NO        NO
// Member Class   :  YES     YES        YES       YES
// Local Class    :  NO      NO         NO        NO
// Anonymous class:  NO      NO         NO        NO
// Class Fields   :  YES     YES        YES       YES
public class BaseJavaClass {

    // 对于Fields封装, 允许内部访问, 作为状态stated的体现 ------------------------
    private int id;          // 默认值是0
    private String username; // 默认值是null

    // Constructor 自带默认的构造器
    public BaseJavaClass() {
        // 可以调用自定义的含参构造器: 设置默认的值
        this(100, "OtherTech");
    }

    public BaseJavaClass(int id) {
        // this.id = id;
        this(id, "OtherTech");
    }

    // Constructor overloading 创建类型的实例的时候，会自动的找到指定的构造器
    public BaseJavaClass(int id, String username) {
        this.id = id;
        this.username = username;
    }

    // 对于普通的Getter和Setter 可以直接公开数据 Fields -----------------------
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

    // 成员类型可以使用4种修饰符 ---------------------------------------------
    // private static class InnerClass { }

    // 受包含的成员，只能在当前的package和它的子类中被访问到 !!
    protected void testProtectedMethod() {
    }

    // 默认不写约束, 方法只能在Package内被访问到, 实列方法和静态方法都是如此 !!
    static int getNumber() {
        return 10;
    }

    // TODO: Java中默认实例方法都是虚方法，可以被继承类型重写的，除非使用final关键字
    public void testVirtualMethod() {
        System.out.println("This is a virtual method");
    }
}
