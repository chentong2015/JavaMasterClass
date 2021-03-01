package JDBC.PostgreSQL;

import java.sql.*;

// Driver jar file => postgresql-8.0-310.jdbc3.jar
// Driver Java Class Name => org.postgresql.Driver
// URL Connection String  => jdbc:postgresql://server-name:server-port/database-name 默认的端口号是5432
public class PostgreSQLDriver {

    private static final String DB_NAME = "testDB";
    private static final String CONNECTION_STRING = "jdbc:postgresql://tuxa.sme.utc/" + DB_NAME;
    private static final String DRIVER_NAME = "org.postgresql.Driver";
    private static String username = "admin";
    private static String password = "password";

    // 利用反射：在运行时动态地返回指定名称的类型或者接口的Class对象
    // 动态检测 driver java class name
    private boolean isValidDriverClassName() {
        try {
            Class obj = Class.forName(DRIVER_NAME);
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
