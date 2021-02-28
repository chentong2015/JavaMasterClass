package JDBC.SQLite;

import JDBC.Model.Album;
import JDBC.Model.Datasource;

import java.sql.*;

// sqlite-jdbc-3.20.1.jar 内置提供默认的DB数据库, 不需要在做配置, 内置指定的版本
// DB Browser for SQLite 数据库GUI用户操作界面(前端)
public class BaseSQLiteDriver {

    public static final String DB_NAME = "demo.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    /**
     * 1. 不同的JDBC Driver连接字符串有所不同, 可以使用绝对路径
     * 2. 创建数据库连接时, 如果DB不存在, 则会创建出来
     * 3. Connection和Statement(关联指定的Connection)资源需要在执行结束的时候关闭，避免资源的浪费和对性能的影响
     * 4. 声明SQL执行的query，结尾不需要添加分号
     */
    // 代码优化1. 将关键字和指定字符串，声明成常量，避免硬编码字符串出错
    // 代码优化2. 将要直接的SQL查询语句独立出来，成单独的方法，实现指定的一个功能 !!
    // 代码优化3. 使用statement.executeQuery("sql");直接拿到查询的结果ResultSet
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING);
            // connection.setAutoCommit(false); 是否在执行statement之后，立即auto commit changes
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS contacts");
            statement.execute("CREATE TABLE IF NOT EXISTS contacts (name TEXT, phone INTEGER, email TEXT)");
            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('chen', 12345, 'chen@gmail.com')");
            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('tong', 12345, 'tong@gmail.com')");
            statement.execute("UPDATE contacts SET phone=232323 WHERE name='chen'");
            statement.execute("DELETE FROM contacts WHERE name='chen'");

            // 如果需要使用不同的查询数据，则需要对应的不同的statement, statement只能关联一个结果数据
            statement.execute("SELECT * FROM contacts");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                String name = results.getString("name");
                int phone = results.getInt("phone");
                String email = results.getString("email");
            }
            results.close(); // ResultSet是一个resource资源，需要关闭

            statement.close(); // 自定义关闭，注意关闭的顺序
            connection.close(); // 关闭连接之后，任何的statement将不能再使用
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void testDataSource() {
        Datasource datasource = new Datasource();
        if (!datasource.connect()) {
            System.out.println("Connection error");
        } else {
            for (Album album : datasource.getAlbums()) {
                System.out.println(album.getId() + album.getName() + album.getArtistId());
            }
        }
        datasource.close();
    }
}
