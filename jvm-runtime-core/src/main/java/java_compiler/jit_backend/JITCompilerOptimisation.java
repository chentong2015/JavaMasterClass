package java_compiler.jit_backend;

// TODO. JIT(后端编译) => 优化策略
// 把Class字节码文件转换成和二级制机器码(硬件指令级，操作系统)
// 1. HotSpot虚拟机中JIT编译器的优化技术，输出代码的质量决定了编译器的好坏
// 2. JIT即时编译的过程体现了虚拟机中最复杂的技术水平
public class JITCompilerOptimisation {

    // TODO: HotSpot虚拟机中JIT编译器做了那些优化
    // 基本优化技术测试
    // 1. 方法内联优化: 取消方法调用的成本(查找方法版本，建立栈帧等)
    // 2. 冗余访问消除: 除掉重复访问同一个成员的值(在值保存不变的情况下)
    // 3. 复写传播优化: 替换掉多余的复写的变量
    // 4. 无用代码消除: 取消掉没有意义的代码行
    public void testOptimisation() {
        // int a = classObject.getValue();
        // do something else ...
        // int b = classObject.getValue();
        // sum = a + b;

        // 最后优化的结果: 从代码中间的字节码表示到机器码之上优化后的效果 !!
        // int a = classObject.value;
        // do something else ...
        // sum = a + a;
    }

    // TODO: 方法内联: 优化之母，编译器最重要的优化手段
    // 1. Method Inline 方法内联(编译原理)
    //    将目标方法的的代码"Copy"到调用该方法的方法中，避免发生"真实的方法调用"而已
    //    由于Java的实例方法默认是虚方法，考虑到运行时的多态性，编译器很难静态的确定内联的方法版本
    //    1.1 CHA类型继承关系分析，一种激进预测性的优化
    //    1.2 如果CHA查询出来方法确实有多个目标版本可供选择，则使用内联缓存(Inline Cache)来缩减方法调用的开销
    //        > 单态内联缓存：通过缓存来调用，而不是通过不内联的非虚方法调用
    //        > 超多态内联缓存：当程序用到了虚方法的多态性特征，需要查找虚方法表来进行方法分配
    //
    // 2. Escape Analyse 逃逸分析
    //    分析对象动态作用域，当一个对象在方法里面被定义之后
    //    方法逃逸: 通过调用参数传递到其他线程中
    //    线程逃逸: 复制给其他线程访问的实例变量
    //    当一个对象不会逃逸到方法或者线程之外，可以实现不同程度的优化
    //    2.1 TODO: 将对象之间在线程栈上分配内存空间，然后随着方法的结束而销毁，减少堆上GC压力
    //    2.2 标量替换，将Java对象打散，创建若干个被这个方法使用的成员变量来替代
    //    2.3 没有线程逃逸，则没有同步竞争，则可以消除对这个变量的同步措施
    public int testEscapeAnalysis(int x) {
        // int xx = x + 2;
        // Point p = new Point(xx, 42); 没有逃逸则接将x,y坐标置换出来，分解成局部变量避免创建Point对象
        // return p.getX();

        // 内联优化 + 逃逸分析(标量优化) + 数据流分析
        return x + 2;
    }

    // 3. 公共子表达式消除: 局部或全局
    //    直接使用已经计算过的表示式E的结果(如果从先前到现在变量的值都没有发生变化)，而无需重新计算
    public void testCommonSubExpression() {
        // int d = (c * b) * 12 + a + (a + b * c);

        // JavaC编译不会对上述代码进行优化，而是交给虚拟机即时编译器进行优化 !!
        // 公共子表达式消除 + 代数化简
        // int d = E * 13 + a + a;
    }

    // 4. 数组边界检测消除
    //    在判断数组的下标不会越界的情况下，取消对数组下标的条件判断，因为一定不会越界
    //    隐式异常处理: 进入异常处理器需要从"用户态(用户线程)"转到"内核态(系统内核Kernel线程)"，然后再回到用户态 !!
    public void testImplicitException() {
        // if (foo != null) {
        //    return foo.value;
        // } else {
        //    throw new NullPointException();
        // }

        // 隐式优化 + 虚拟机注册Segment Fault异常处理器：当foo很少为空是，隐式优化很显著
        // try {
        //    foo.value;
        // } catch (Segment_Fault)
        //    uncommon_trap();
        // }
    }
}
