package JNDI;

// Java提供的javax.sql.DataSource接口
// 1. A factory for connections to the physical data source that this DataSource object represents
//    本质上是一个连接物理数据源的工厂Connection Factory
// 2. An object that implements the DataSource interface will be registered with a naming service based on JNDI
//    实现DataSource接口的对象将注册到基于JNDI的命名服务，可以通过此服务来获取数据源
// 3. DataSource接口由驱动程序供应商实现
//    3.1 MyBatis > PooledDataSource
//    3.2 apache.commons.dbcp2 > BasicDataSource
public class JavaDataSource {

    // DataSource接口的三种实现方式
    // 1. Basic implementation: produces a standard Connection object
    // 2. Connection pooling implementation: 创建自动参与连接池的Connection对象，与中间层连接池管理器一起使用
    // 3. Distributed transaction implementation: 生成可用于分布式事务的Connection对象

    // TODO: 通过DataSource Object对象的属性(参数)配置，实现跨不同Server的使用
    //       只改变数据源properties，而无需改变源代码
    // A DataSource object has properties that can be modified when necessary.
    // For example, if the data source is moved to a different server, the property for the server can be changed.
    // The benefit is that because the data source's properties can be changed,
    // any code accessing that data source does not need to be changed.
    // https://docs.oracle.com/javase/8/docs/api/javax/sql/DataSource.html?is-external=true
}
