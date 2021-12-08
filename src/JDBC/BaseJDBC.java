package JDBC;

// TODO: 无论DB是运行在本地机器还是远端，JDBC都会走网络(带端口)去要数据
//   关系到数据库底层的"数据库引擎"的设计和实现
// 1. API for DB connectivity between Java Programming and Databases / SpreadSheets / flat files
// 2. JPA Java持久层API / Dao数据持久层: java程序和DB的中间层
// 3. 不同的数据库提供不同的数据库驱动driver, java程序只需要使用JDBC驱动就能和不同的数据库交互
// 4. JDBC Architecture: JDBC API + JDBC Driver API
//    Java application     Java代码
//    JDBC API             提供接口, 比如Connection
//    JDBC Driver Manager  统一管理不同的JDBC Driver, 确保使用正确的驱动程序来访问每个数据源
//    JDBC Driver          每个数据库所提供的driver引擎, 自定义使用Java开发/实现java.sql.driver interface
//    SQLite, SQL Server.. 具体的数据库(数据)
// 5. Packages
//    java.sql (core JDBC)
//    javax.sql (optional JDBC) when working with database servers

// TODO: JDBC的弊端
// 1. jdbc底层没有使用连接池，操作数据库需要频繁的连接和关闭链接，消耗资源
// 2. jdbc的sql写在代码中，如果修改需要重新编译
// 3. 使用PreparedStatement预编译，需要用对parameterIndex123，不利于维护
// 4. 还需要对返回的ResultSet结果解析

// Java的开源数据库连接池:
// C3P0、Proxool、DBCP、BoneCP、Druid
public class BaseJDBC {

    // SQL指令全集:  https://sql.sh/cours/
    // JDBC详解入门: https://www.tutorialspoint.com/jdbc/jdbc-introduction.htm

    // SELECT @@system_time_zone;  MySQL会追踪time zone用于有时间关联的functions, 可以在System Variables中看到
    // SELECT @@time_zone;
    // SET GLOBAL time_zone='+1:00' 重新设置时区
}
