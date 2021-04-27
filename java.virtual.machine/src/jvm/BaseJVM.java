package jvm;

public class BaseJVM {

    // TODO: https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
    // string objects are used within the JVM
    // JVM uses string pools for allocation of string objects  ===> C#：字符串的留用 ?
    
    /**
     * 源码
     * public static void main(String[] args) {
     *   System.out.println("Test OK");
     * }
     *
     * TODO: ByteCode文件，编译之后的字节码
     * // access flags 0x9
     *   public static main([Ljava/lang/String;)V
     *    L0
     *     LINENUMBER 18 L0
     *     GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
     *     LDC "Test OK"
     *     INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
     *    L1
     *     LINENUMBER 19 L1
     *     RETURN
     *    L2
     *     LOCALVARIABLE args [Ljava/lang/String; L0 L2 0
     *     MAXSTACK = 2
     *     MAXLOCALS = 1
     */
}
