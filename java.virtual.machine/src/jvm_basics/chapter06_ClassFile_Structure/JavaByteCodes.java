package jvm_basics.chapter06_ClassFile_Structure;

// TODO. 字节码: Java程序在运行过程中，内存数据的流转模型
public class JavaByteCodes {

    /*
     * javac将如下方法编译成bytecode
     * public int compute();
     * Code:
     *   0:  iconst_1       将int型的1推送至栈顶                  ==> 压入到"操作数栈"
     *   1:  istore_1       将栈顶int型的数值存入第二个本地变量      ==> 对"局部变量表"中的局部变量设置值1 ==> a=1
     *   2:  iconst_2
     *   3:  istore_2
     *   4:  iload_1        将第二个int型本地变量推送至栈顶         ==> 装转到"操作数栈"中
     *   5:  iload_2
     *   6:  iadd           将栈顶两个int型的数值相加，将结果压入栈顶 ==> 装转到"操作数栈"中
     *   7:  bipush     10  将单字节的常量值(-128~127)推送至栈顶
     *   9:  imul           将栈顶两个int型的数值相乘，然后将结果压入栈顶
     *   10: istore_3
     *   11: iload_3
     *   12: ireturn        从当前方法返回int(不同的指令表示返回不同类型的数据)
     */
    public int compute() {
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }
}
