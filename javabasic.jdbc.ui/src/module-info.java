module javabasic.jdbc.ui {
    requires java.base;

    requires javabasic.jdbc.dao;
    // requires javabasic.jdbc.common; 由于javabasic.jdbc.dao中所requires的common module是可传递的，所以这里不需要再声明 !!

    exports javabasic.jdbc.ui to java.logging;
}