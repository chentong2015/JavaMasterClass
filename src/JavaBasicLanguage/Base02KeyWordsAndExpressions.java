package JavaBasicLanguage;

public class Base02KeyWordsAndExpressions {

    /**
     * Key words Java关键字: 50 + 3个
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
     * _ (underscore) 保留关键字_(下划线), 以备将来在参数声明中使用
     * 同时const和goto也是保留的关键字 (虽然不再使用)
     * 如何解释native关键字: protected native Object clone() throws ...
     * 没有internal关键字 :: 区别C#
     */

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
