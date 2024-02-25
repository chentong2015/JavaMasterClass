package JavaBasic;

import java.io.Serializable;

// Java Keywords ~57:
// abstract   continue   for          new         switch
// assert     default    if           package     synchronized
// boolean    do         goto         private     this
// break      double     implements   protected   throw
// byte       else       import       public      throws
// case       enum       instanceof   return      transient
// catch      extends    int          short       try
// char       final      interface    static      void
// class      finally    long         strictfp    volatile
// const      float      native       super       while
public class Base4Keywords implements Serializable {

    // TODO. 特殊关键字说明
    // goto   保留关键字，不使用
    // const  保留关键字，不使用
    // _      保留关键字以备将来在参数声明中使用
    // inline 保留关键字，Java的值类型方案"内联类型"

    // transient 标明对象的属性不被序列化
    private transient String name;

}
