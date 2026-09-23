package io;

// TODO: Try With Resources Statement 该声明保证IO资源流的关闭
// - 支持多个Resources的同时声明，使用;分隔
// - 对应的Resources资源对象必须实现close()方法，保证在try结束时被调用
public class TryWithResourcesStatement {

    // try-with-resources can only manage resources that are declared for the statement
    class InJava8 {
        void doSomethingWith(Connection connection) throws Exception {
            try (Connection c = connection) {
                c.doSomething();
            }
        }
    }

    // in Java 9, all effectively final variables work
    class InJava9 {
        void doSomethingWith(Connection connection) throws Exception {
            try (connection) {
                connection.doSomething();
            }
        }
    }

    class Connection implements AutoCloseable {

        void doSomething() {}

        @Override
        public void close() throws Exception {}
    }
}
