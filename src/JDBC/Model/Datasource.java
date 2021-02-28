package JDBC.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FACADE模式
 * 1. 为一组具有复杂且全面的接口的对象提供一个简单且特定的接口
 * 2. Datasource隐藏java.sql的复杂性，关于数据库的操作都是透过Datasource来实现
 * 大数据库的设计模式：
 * 1. 数据库的连接Connection来自Connection pool
 * 2. 针对每一个table创建一个model class
 * 3. 封装在数据库模型类型中的方法，不应该对外暴露实现的细节，比如：不直接返回查询的结果ResultSet给调用者 !!
 */
public class Datasource {

    private Connection connection;
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    public boolean connect() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException exception) {
            System.out.println("cannot connect to database");
            return false;
        }
    }

    // 返回经过处理之后的数据, 对table进行建模model
    public List<Album> getAlbums() {
        String query = "SELECT * FROM albums";
        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(query)) {
            return parseAlbumResults(results);
        } catch (SQLException ex) {
            System.out.println("Query error" + ex.getMessage());
        }
        return new ArrayList<>(); // 不要返回null空值
    }

    private List<Album> parseAlbumResults(ResultSet results) throws SQLException {
        List<Album> albums = new ArrayList<>();
        while (results.next()) {
            Album album = new Album();
            album.setId(results.getInt("_id"));
            album.setName(results.getString("name"));
            album.setArtistId(results.getInt("artist"));
            albums.add(album);
        }
        return albums;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException exception) {
            System.out.println("Error closing connection");
        }
    }
}
