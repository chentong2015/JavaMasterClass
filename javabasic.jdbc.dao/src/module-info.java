/**
 * 1. Project settings > modules > package > 添加requires的自定义的module as a dependency
 * 2. Project settings > modules > package > 添加需要的JDBC Jar
 * 3. 避免形成cyclic dependency, 使用transitive dependencies可传递性的依赖
 * ... 所有require javabasic.jdbc.dao module的都不需要再注明requires javabasic.jdbc.common;
 * ... 可以将对javabasic.jdbc.common;的requires进行传递下去 !!!
 */
module javabasic.jdbc.dao {
    requires java.sql;
    // requires sqlite.jdbc; 忽略jar实际的版本号的信息

    requires transitive javabasic.jdbc.common;
    exports javabasic.jdbc.dao; // to javabasic.jdbc.ui; export给module ui，使module ui能够使用该module
}