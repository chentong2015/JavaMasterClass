package JNDI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

public class BaseJavaJndiLDAP {

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
            LdapContext ldapContext = (LdapContext) ctx.lookup("cn=Rosanna Lee,ou=People");
            ldapContext.lookup("xxx");
            ctx.close();
        } catch (NamingException e) {
            System.out.println("Lookup failed: " + e);
        }
    }
}
