package JavaProxy.MyBatisProxy;

// TODO: Mybatis源码动态代理案列
public class BaseMyBatisProxy {

    // MapperRegister > MapperProxyFactory > Proxy.java > MapperProxy !! > MapperMethod
    // MapperProxy 实现了InvocationHandler接口

    //  private SqlSessionManager(SqlSessionFactory sqlSessionFactory) {
    //    this.sqlSessionFactory = sqlSessionFactory;
    //    this.sqlSessionProxy = (SqlSession) Proxy.newProxyInstance(
    //        SqlSessionFactory.class.getClassLoader(),
    //        new Class[]{SqlSession.class},
    //        new SqlSessionInterceptor());
    //  }
}
