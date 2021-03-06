package OtherTech.JPMS;

/**
 * Module System Demo:
 * 1. 自定义创建java module, 将Application拆分成不同的module组成, 相互依赖且独立
 * 2. 区别project module dependencies和自定义module dependencies, 构建时将JAR/Libraries添加到指定的依赖下面 !!
 * 3. 注意module下面文件的相对路径，在引用时使用 "\ 表示module的root path (src目录)"
 */
public class DemoModuleSystem {

    // Module Configuration 3 elements：unique name + inputs + outputs !!

    /**
     * Module 1:
     * javabasic.jdbc.common
     * javafx.base --> imports (Modules) | Exports (Packages) --> javabasic.jdbc.common
     * Module code
     * Export packages
     * Package [Album, Artist .. ]
     */

    /**
     * Module 2:
     * javabasic.jdbc.dao
     * java.sql/sqlite.jdbc/javabasic.jdbc.common --> imports (Modules) | Exports (Packages) --> javabasic.jdbc.dao
     * Module code
     * Export packages
     * Package [Datasource .. ]
     */

    /**
     * Module 3:
     * javabasic.jdbc.ui
     * javafx.fxml/javafx.base/javabasic.jdbc.dao --> imports (Modules) | Exports (Packages) --> javabasic.jdbc.ui
     * Module code
     * Export packages
     * Package [Controller, Main, main.fxml .. ]
     */
}
