module javabasic.jdbc.ui {
    requires java.base;

    requires javabasic.jdbc.dao;

    // 由于javabasic.jdbc.dao中所requires的common module是可传递的
    // 这里不需要再声明
    // requires javabasic.jdbc.common;

    exports javabasic.jdbc.ui to java.logging;
}