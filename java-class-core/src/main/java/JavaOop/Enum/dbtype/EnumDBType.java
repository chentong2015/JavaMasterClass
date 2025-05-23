package JavaOop.Enum.dbtype;

import java.util.HashMap;
import java.util.Map;

public enum EnumDBType {

    // TODO. 定义Enum Constant所有常量，通过DBType.valueOf()名称创建某个类型
    //  Constant会调用Enum定义的构造器来初始化
    MS_SQL_SERVER("jdbc:sqlserver://",
            "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            "com.microsoft.sqlserver.jdbc.SQLServerXADataSource", null) {
        // 每一个枚举类型都必须实现DBType定义的抽象方法
        @Override
        String buildURL() {
            return "sql sever connection string";
        }
    };

    private static Map<String, EnumDBType> lowerCaseNames = new HashMap();
    private static Map<String, EnumDBType> pascalCaseNames = new HashMap();

    // TODO. values()返回声明的枚举类型所定义的所有的常量的名称
    // Returns an array containing the constants of this enum type, in the order they're declared.
    static {
        for (EnumDBType dbType : values()) {
            lowerCaseNames.put(dbType.lowerCaseName(), dbType);
            pascalCaseNames.put(dbType.pascalCaseName(), dbType);
        }
    }

    private final String defaultDriver;
    private final String defaultDriverClass;
    private final String defaultXADataSource;
    private final Map<String, String> defaultProperties;

    EnumDBType(String defaultDriver, String defaultDriverClass, String defaultXADataSource, Map<String, String> defaultProperties) {
        this.defaultDriver = defaultDriver;
        this.defaultDriverClass = defaultDriverClass;
        this.defaultXADataSource = defaultXADataSource;
        this.defaultProperties = defaultProperties;
    }

    // TODO. 这里的抽象方法交给Enum Constant来实现
    abstract String buildURL();

    // parse() & parsePascalCase()通过名称创建指定的DBType枚举类型 !!
    public static EnumDBType parse(String value) {
        EnumDBType dbType = lowerCaseNames.get(value.toLowerCase());
        if (dbType == null) {
            throw new IllegalArgumentException("Invalid DBType: " + value);
        }
        return dbType;
    }

    public static EnumDBType parsePascalCase(String value) {
        EnumDBType dbType = pascalCaseNames.get(value);
        if (dbType == null) {
            throw new IllegalArgumentException("Invalid DBType: " + value);
        }
        return dbType;
    }

    public boolean thisCodeIsSqlServerSpecific() {
        return this == EnumDBType.MS_SQL_SERVER;
    }

    public String lowerCaseName() {
        return this.name().toLowerCase();
    }

    public String pascalCaseName() {
        String lowerCase = lowerCaseName();
        StringBuilder stringBuilder = new StringBuilder(lowerCase.length());
        boolean toUpper = true;
        for (int i = 0; i < lowerCase.length(); ++i) {
            if (lowerCase.charAt(i) == '_') {
                toUpper = true;
            } else {
                stringBuilder.append(toUpper ? Character.toUpperCase(lowerCase.charAt(i)) : lowerCase.charAt(i));
                toUpper = false;
            }
        }
        return stringBuilder.toString();
    }
}
