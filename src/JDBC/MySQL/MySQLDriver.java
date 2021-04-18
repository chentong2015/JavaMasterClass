package JDBC.MySQL;

import java.sql.*; // 不能引入 com.mysql.jdbc.* 否则会出错 !!

// Driver Jar Files  => mysql-connector-java-3.0.11-stable-bin.jar
// Driver Java Class Name => com.mysql.jdbc.Driver
// URL Connection String  => jdbc:mysql://server-name:server-port/database-name 默认的端口号是3306
public class MySQLDriver {

    /**
     * Spring boot框架构建datasource
     * 指定数据库对应的driver名称应该是一致的
     * spring:
     *   datasource:
     *     driver-class-name: com.mysql.jdbc.Driver
     *     url: jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf-8
     *     username: admin
     *     password: admin
     *   jpa:
     *     hibernate:
     *       ddl-auto: update
     *     show-sql: true
     */

    /**
     * 通用架构: 通过Driver连接字符串进行连接
     * 本机测试：url = "jdbc:mysql://127.0.0.1:3306/database?autoReconnect=true&maxReconnects=100" 可以配置连接属性
     */
    private static final String DB_NAME = "testDB";
    private static final String CONNECTION_STRING = "jdbc:mysql://tuxa.sme.utc/" + DB_NAME;
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static String username = "admin";
    private static String password = "password";

	/**
	 * Specify to the DriverManager which JDBC drivers to try to make Connections with. 
	 * Use Class.forName() on the class that implements the java.sql.Driver interface
	 * 向DriverManager指定尝试与之建立连接的JDBC驱动程序, 该驱动程序必须实现了指定的接口 !!
	 * 每一个数据库实现接口的Class Name都有不同 !!
	 */
    private boolean isValidDriverClassName() {
        try {
            Class obj = Class.forName(DRIVER_NAME);
			// Class.forName("com.mysql.jdbc.Driver").newInstance();
            return true;
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: " + e.getMessage());
            return false;
        }
    }

    private void testPSQLDriver() {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING, username, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM table";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
            }
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
        } catch (SQLException exception) {
            System.out.println("Connection error");
        }
    }
}
