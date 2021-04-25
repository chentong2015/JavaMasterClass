package JDBC;

/**
 * JDBC: Java Database connectivity  ===> 无论DB是运行在本地机器还是远端，JDBC都会走网络(带端口)去要数据 !!!
 * 1. API for DB connectivity between Java Programming and Databases / SpreadSheets / flat files 纯文本格式存储数据的数据库
 * 2. JPA Java持久层API / Dao数据持久层: java程序和DB的中间层
 * 3. 不同的数据库提供不同的数据库驱动driver, java程序只需要使用JDBC驱动就能和不同的数据库交互 ==> 实现跨数据库
 * .
 * 4. JDBC Architecture: JDBC API + JDBC Driver API
 * ---> Java application     Java代码
 * ---> JDBC API             提供接口, 比如Connection
 * ---> JDBC Driver Manager  统一管理不同的JDBC Driver, 确保使用正确的驱动程序来访问每个数据源
 * ---> JDBC Driver          每个数据库所提供的driver引擎, 自定义使用Java开发/实现java.sql.driver interface
 * ---> SQLite, SQL Server.. 具体的数据库(数据)
 * .
 * 5. Packages
 * ---> java.sql (core JDBC)
 * ---> javax.sql (optional JDBC) when working with database servers !!
 */
// SQL指令全集:  https://sql.sh/cours/
// JDBC详解入门: https://www.tutorialspoint.com/jdbc/jdbc-introduction.htm
public class BaseJDBC {

    // 数据库开发规范：访问数据库必须使用连接池
    // Java JDBC提供了很多可靠性的保护：网络中断自动重连, 连接数限制，定期回收连接，设置超时时间等
    // 1. 必须具有自动重连的功能
    // 2. 必须设置何理的超时时间, 防止程序访问数据库长时间的挂死
    // 3. 数据库连接池必须设置最大值, 默认是500个连接
    // 4. Java 语言推荐使用mybatis持久层框架来访问数据库

    // SELECT @@system_time_zone;  MySQL会追踪time zone用于有时间关联的functions, 可以在System Variables中看到
    // SELECT @@time_zone;
    // SET GLOBAL time_zone='+1:00' 重新设置时区
}
