/**
 * module declaration: 用于表示不同module之间的关系
 * > open: declares an open module that exports all it's packages to be used by reflective access !!
 * > normal modules: without [open], grants access at "compile time and run time" to only those packages "explicitly exported"
 * > open modules: with [open}, grants access at "compile time" to only those packages explicitly exported, "run time" to all
 * 使用模板:
 * [open] module <moduleName> {
 * ...... <module-statement>
 * ...... <module-statement>
 * ...... ...
 * ...... exports statement  声明export的packages, 如果不export则在外部不可使用
 * ...... opens statement    不能再在 [open] 中使用
 * ...... required statement 声明depend on some other module; 可以声明对多个module的依赖关系
 * ...... uses statement     声明当前module所consume的service
 * ...... provides statement 声明当前module提供的service implementations
 * }
 */
module javabasic.jdbc.common {
    requires java.base;
    requires java.sql;

    opens javabasic.jdbc.ui to java.desktop; // opens => 当后面的module需要inject or set前面的package中对象的fields
    exports javabasic.jdbc.ui to java.logging, java.net.http; // 将指定的package export给指定的module
}