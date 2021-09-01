package JNDI;

/**
 * TODO JNDI深入研究: https://docs.oracle.com/javase/tutorial/jndi/index.html
 * JNDI: Java Name and Directory Interface
 * 1. Name/Directory Service maps the names of network (address) resources
 * 2. Each resource on network is an object by the directory server   !!
 * 3. JNDI是一个访问名称或者目录服务的APIs, 它使用名称去定位和查找对应objects/resources !!
 */

/**
 * Naming service
 * 1. DNS(Domain name system): 域名解析系统, Map machine names to IP Address
 * .
 * Directory services
 * 1. LDAP: Lightweight Directory Access Protocol 轻量级目录访问协议
 * 2. Corba: Common Object Request Broker Architecture 通用对象请求代理架构，分布式对象系统
 * 3. RMI: Java Remote Method Invocation 远程方法调用
 * 4. EJB: Enterprise JavaBeans, Java platform's component technology 组件技术，方便重用软件的组件
 */
public class JavaJNDI {

    // Running an config on a Java EE application server, looks up and locates a database
    // using JNDI name of the datasource without having to know the details about the connection
    // 能够将APP移植和部署到使用不同data source的server服务器
    // Java Code只需要使用dataSourceName就能连接, 而不必关注具体的连接细节
}
