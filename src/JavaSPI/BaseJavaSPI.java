package JavaSPI;

// Java SPI机制动态加载指定接口的实现类型
// 1. 创建指定路径下文件/resources/META-INF/Services/file(接口全路径名)
// 2. 文件中注明接口的实现类型

// 不足之处:
// 1. java spi不能单独获取某个指定的实现类
// 2. java spi没有IOC和AOP机制
public class BaseJavaSPI {

    // 实用场景：Java JDBC面向接口编程
    // 不同的数据库都需要实现指定的Driver接口，在Java层面不需要去关心具体的实现类型
    // 1. 统一文件名称: /META-INF/Services/java.sql.Driver
    // 2. 实现类型实现: 不同的数据库jar包上配置的实现类型不同
}
