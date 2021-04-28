package jvm;

public class BaseJVM {
    
    /**
     * Java源码
     * public static void main(String[] args) {
     *   System.out.println("Test OK");
     * }
     *
     * ByteCode字节码: 编译之后的Class文件
     * // access flags 0x9
     * public static main([Ljava/lang/String;)V
     *  L0
     *   LINENUMBER 18 L0
     *   GETSTATIC java/lang/System.out : Ljava/io/PrintStream;         ==> getstatic是JVM的指令
     *   LDC "Test OK"
     *   INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
     *  L1
     *   LINENUMBER 19 L1
     *   RETURN
     *  L2
     *   LOCALVARIABLE args [Ljava/lang/String; L0 L2 0
     *   MAXSTACK = 2
     *   MAXLOCALS = 1
     */
}
