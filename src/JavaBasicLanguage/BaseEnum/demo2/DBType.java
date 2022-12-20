package JavaBasicLanguage.BaseEnum.demo2;

import java.util.HashMap;
import java.util.Map;

public enum DBType {

    // TODO. 定义Enum Constants所有常量，DBType.valueOf()直接通过名称来创建 => 挑选一个类型
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

    private static Map<String, DBType> lowerCaseNames = new HashMap();
    private static Map<String, DBType> pascalCaseNames = new HashMap();

    // TODO. values()返回声明的枚举类型所定义的所有的常量的名称
    // Returns an array containing the constants of this enum type, in the order they're declared.
    static {
        for (DBType dbType : values()) {
            lowerCaseNames.put(dbType.lowerCaseName(), dbType);
            pascalCaseNames.put(dbType.pascalCaseName(), dbType);
        }
    }

    private final String defaultDriver;
    private final String defaultDriverClass;
    private final String defaultXADataSource;
    private final Map<String, String> defaultProperties;

    DBType(String defaultDriver, String defaultDriverClass, String defaultXADataSource, Map<String, String> defaultProperties) {
        this.defaultDriver = defaultDriver;
        this.defaultDriverClass = defaultDriverClass;
        this.defaultXADataSource = defaultXADataSource;
        this.defaultProperties = defaultProperties;
    }

    // parse() & parsePascalCase()通过名称创建指定的DBType枚举类型 !!
    public static DBType parse(String value) {
        DBType dbType = lowerCaseNames.get(value.toLowerCase());
        if (dbType == null) {
            throw new IllegalArgumentException("Invalid DBType: " + value);
        }
        return dbType;
    }

    public static DBType parsePascalCase(String value) {
        DBType dbType = pascalCaseNames.get(value);
        if (dbType == null) {
            throw new IllegalArgumentException("Invalid DBType: " + value);
        }
        return dbType;
    }

    public boolean thisCodeIsSqlServerSpecific() {
        return this == DBType.MS_SQL_SERVER;
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

    // TODO. 这里的抽象方法交给Enum Constant来实现
    abstract String buildURL();

    String getDefaultDriver() {
        return defaultDriver;
    }

    String getDefaultDriverClass() {
        return defaultDriverClass;
    }

    String getDefaultXADataSource() {
        return defaultXADataSource;
    }

    Map<String, String> getDefaultProperties() {
        return defaultProperties;
    }
}
