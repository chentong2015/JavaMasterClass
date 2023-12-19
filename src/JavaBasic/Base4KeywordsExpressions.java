package JavaBasic;

// syntactic sugar 语法糖
public class Base4KeywordsExpressions {

    /**
     * Key words Java: 约57个关键字
     * abstract   continue   for          new         switch
     * assert     default    if           package     synchronized
     * boolean    do         goto         private     this
     * break      double     implements   protected   throw
     * byte       else       import       public      throws
     * case       enum       instanceof   return      transient
     * catch      extends    int          short       try
     * char       final      interface    static      void
     * class      finally    long         strictfp    volatile
     * const      float      native       super       while
     */
    // transient      标明对象的属性不被序列化 Not part of the persistent state of an object
    // const & goto   保留关键字，不再实际使用
    // _ (underscore) 保留关键字以备将来在参数声明中使用
    // inline         保留关键字，Java的值类型方案"内联类型"
    public static void testExpressions() {
        // 除了double类型之外, kilometres = (100 * 1.67235) 构成一个表达式Expression
        // 加上double类型，构成一个声明Statement
        double kilometres = (100 * 1.67235);
        int highScore = 50;
        if (highScore == 50) {  // highScore == 50 is a Expression
            System.out.println("Expression"); // "Expression" is a Expression
        }
    }
}
