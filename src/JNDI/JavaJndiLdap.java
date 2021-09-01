package JNDI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

// JNDI: Java Name and Directory Interface 访问名称或者目录服务的APIs
// 1. Name/Directory Service maps the names of network (address) resources 使用名称去定位和查找对应objects/resources
// 2. Each resource on network is an object by the directory server
// https://docs.oracle.com/javase/tutorial/jndi/index.html

// Naming service 名称服务
// 1. DNS(Domain name system): Map machine names to IP Address

// Directory services 目录服务
// 1. RMI: Java Remote Method Invocation
// 2. LDAP: Lightweight Directory Access Protocol
// 3. Corba: Common Object Request Broker Architecture 分布式对象系统
// 4. EJB: Enterprise JavaBeans, Java platform's component technology 组件技术，方便重用软件的组件
public class JavaJndiLdap {

    // TODO: 通过JNDI名称服务获取应用的Data Source, 只需要使用dataSourceName就能连接, 不必关注具体的连接细节
    // 能够将APP移植和部署到使用不同data source的server服务器, 只要名称一致

    // 测试通过LDAP轻量级目录访问协议，查找网络路径上的资源(object)
    //  o=JNDITutorial
    //    ou=People
    //      cn=RosannaLee
    //         LdapContext
    public static void main(String[] args) {
        // Set up the environment for creating the initial context
        Hashtable<String, Object> env = new Hashtable<>(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389/o=JNDITutorial");
        try {
            Context ctx = new InitialContext(env);
            LdapContext b = (LdapContext) ctx.lookup("cn=Rosanna Lee,ou=People");
            System.out.println(b);
            ctx.close();
        } catch (NamingException e) {
            System.out.println("Lookup failed: " + e);
        }
    }
}
