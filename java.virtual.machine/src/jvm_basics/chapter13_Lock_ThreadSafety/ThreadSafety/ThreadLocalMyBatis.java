package jvm_basics.chapter13_Lock_ThreadSafety.ThreadSafety;

public class ThreadLocalMyBatis {

    // 一般ThreadLocal需要设置成final
    //  private final ThreadLocal<SqlSession> localSqlSession = new ThreadLocal<>();
    //
    //  private class SqlSessionInterceptor implements InvocationHandler {
    //
    //    @Override
    //    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    //      TODO: 嵌套类可以直接访问外部类的所有成员，而无需使用外部类.this来调用 !!
    //      final SqlSession sqlSession = SqlSessionManager.this.localSqlSession.get();
    //      if (sqlSession != null) {
    //        try {
    //          return method.invoke(sqlSession, args);
    //        } catch (Throwable t) {
    //          throw ExceptionUtil.unwrapThrowable(t);
    //        }
    //      } else {
    //        try (SqlSession autoSqlSession = openSession()) {
    //         ...
    //        }
    //      }
    //    }
    //  }
}
