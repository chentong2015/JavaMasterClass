package jvm_basics.chapter06_ClassFile_Structure;

// > javap -c file.class > file.txt 将字节码反汇编的指令
// file.class              file.txt
// cafe babe 0000 0034     public class com.test.jvm.Math {
// 0027 0a00 0200 260a        public static final int initData;
// 2609 6322 0a00 0700        public static void main(java.lang.String[]):
// cafe babe 0000 0034            code:
// 0027 0a00 0200 260a               0: new        #2   // class com/test/jvm/Math
// 2609 6322 0a00 0700               3: dup
// ...

// jclasslib Bytecode Viewer程序对应字节码查看器
// 1. 编译项目成bytecode字节码
// 2. 选择指定的java file查找编译后的信息

// Java程序反汇编代码查看
// 1. 下载包hsdis-amdb64.dll，然后放置到jre/bin/
// 2. 在运行时设置参数VM Options
//    -server -Xcomp -XX:+UnlockDisgnosticVMOptions -XX:+PrintAssembly
//    -XX:ComplileCommand=compileonly,*VolatileVisibilityTest.prepareData
public class ByteCodes {

    /*
     * javac编译之后的字节码: Java程序在运行过程中，内存数据的流转模型 !!
     * public int compute();
     *   Code:
     *     0:  iconst_1       将int型的1推送至栈顶                  ==> 压入到"操作数栈"
     *     1:  istore_1       将栈顶int型的数值存入第二个本地变量      ==> 对"局部变量表"中的局部变量设置值1 ==> a=1
     *     2:  iconst_2
     *     3:  istore_2
     *     4:  iload_1        将第二个int型本地变量推送至栈顶         ==> 装转到"操作数栈"中
     *     5:  iload_2
     *     6:  iadd           将栈顶两个int型的数值相加，将结果压入栈顶 ==> 装转到"操作数栈"中
     *     7:  bipush     10  将单字节的常量值(-128~127)推送至栈顶
     *     9:  imul           将栈顶两个int型的数值相乘，然后将结果压入栈顶
     *     10: istore_3
     *     11: iload_3
     *     12: ireturn       从当前方法返回int(不同的指令表示返回不同类型的数据)
     */
    public int compute() {
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }

    // 静态变量自加: 非原子操作: 在指令执行的过程中，可能被线程打断，造成数据结果的错误 !!
    // code:
    //    Stack=0, Locals=0, Args_size=0
    //       0:  getstatic
    //       3:  iconst_1
    //       4:  iadd
    //       5:  putstatic
    //       8:  ireturn
    private static volatile int race = 0;

    private static int increase() {
        return ++race;
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
