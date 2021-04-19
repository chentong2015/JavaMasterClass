package JavaBasicLanguage.Base01OOP;

// .                      public  protected  private   static
// Top Level Class :        YES       NO       NO        NO
// Member Class    :        YES       YES      YES       YES
// Local Class     :        NO        NO       NO        NO
// Anonymous class :        NO        NO       NO        NO
// * Class Filed * :        YES       YES      YES       YES

// 默认不写, 表示该类型只能在当前package中被访问
public class MasterClass {

    // Member Class 允许作用4种
    private static class InnerClass {
    }

    // 受包含的成员，只能在当前的package和它的子类中被访问到 !!
    protected void testProtectedMethod() {
    }

    // 默认不写约束, 方法只能在Package内被访问到, 实列方法和静态方法都是如此 !!
    static int getNumber() {
        return 10;
    }

    // TODO: Sealed Classes
    // https://docs.oracle.com/en/java/javase/16/language/sealed-classes-and-interfaces.html

    // TODO: Record Classes
    // https://docs.oracle.com/en/java/javase/16/language/records.html
}
