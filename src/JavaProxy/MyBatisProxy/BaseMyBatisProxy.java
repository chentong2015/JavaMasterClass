package JavaProxy.MyBatisProxy;

// TODO: Mybatis源码动态代理案列
public class BaseMyBatisProxy {

    // 1. 通过提供接口类型，MyBatis是如何创建接口类型的实例化对象的 ?
    //    BlogMapper mapper = session.getMapper(BlogMapper.class);
    //    MapperRegister > MapperProxyFactory > MapperProxy !! > MapperMethod
    //
    //  protected T newInstance(MapperProxy<T> mapperProxy) {
    //    return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, mapperProxy);
    //  }
    //
    //  public class MapperProxy<T> implements InvocationHandler, Serializable {
    //    ...
    //    @Override
    //    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    //       try {
    //         if (Object.class.equals(method.getDeclaringClass())) {
    //           return method.invoke(this, args);
    //         } else {
    //           // 下面调用到嵌套类型的PlainMethodInvoker.invoke()方法
    //           return cachedInvoker(method).invoke(proxy, method, args, sqlSession);
    //         }
    //       } catch (Throwable t) {
    //         throw ExceptionUtil.unwrapThrowable(t);
    //       }
    //    }
    //  }

    // 2. SqlSessionManager使用动态代理
    //  private SqlSessionManager(SqlSessionFactory sqlSessionFactory) {
    //    this.sqlSessionFactory = sqlSessionFactory;
    //    this.sqlSessionProxy = (SqlSession) Proxy.newProxyInstance(
    //        SqlSessionFactory.class.getClassLoader(),
    //        new Class[]{SqlSession.class},
    //        new SqlSessionInterceptor());
    //  }
    //
    //  private class SqlSessionInterceptor implements InvocationHandler {
    //    @Override
    //    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    //      final SqlSession sqlSession = SqlSessionManager.this.localSqlSession.get();
    //      if (sqlSession != null) {
    //        try {
    //          return method.invoke(sqlSession, args);
    //        } catch (Throwable t) {
    //          throw ExceptionUtil.unwrapThrowable(t);
    //        }
    //      }
    //   }
    // }

    // 3. MyBatis Plugin插件的实现/拦截器
    //   public class Plugin implements InvocationHandler {
    //      public static Object wrap(Object target, Interceptor interceptor) {
    //         Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);
    //         Class<?> type = target.getClass();
    //         Class<?>[] interfaces = getAllInterfaces(type, signatureMap);
    //         if (interfaces.length > 0) {
    //           return Proxy.newProxyInstance(
    //               type.getClassLoader(),
    //               interfaces,
    //               new Plugin(target, interceptor, signatureMap));
    //         }
    //         return target;
    //      }
    //   }
}
