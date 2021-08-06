package jvm_basics.chapter06_ClassFile_Structure;

// 常见字节码指令的理解
public class BaseByteCode {

    // 静态变量，运算操作
    // class文件中主要字节码
    // code:
    //   Stack=0, Locals=0, Args_size=0
    //   0:  getstatic
    //   3:  iconst_1
    //   4:  iadd
    //   5:  putstatic
    private static volatile int race = 0;

    private static void increase() {
        race++;
    }

    // main()方法编译出来的字节码
    // public static main([Ljava/lang/String;)V
    // L0
    //   LINENUMBER 18 L0
    //   GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
    //   LDC "Test OK"
    //   INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
    // L1
    //   LINENUMBER 19 L1
    //   RETURN
    // L2
    //   LOCALVARIABLE args [Ljava/lang/String; L0 L2 0
    //   MAXSTACK = 2
    //   MAXLOCALS = 1
    public static void main(String[] args) {
        System.out.println("Test OK");
    }
}
