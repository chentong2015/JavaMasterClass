package JDBC;

/**
 * 对于有用户界面的Application, 需要解决以下问题
 * 1. Datasource该如何设计，类型如何访问data source，何时Open connection ? 何时Close Connection ?
 * 2. 如何在执行数据库操作的时候，避免UI阻塞 ?
 * 3. 如何执行数据的刷新操作，将数据结果返回给用户?
 */
public class BaseJDBCWithGUI {

    /**
     * 解决方案 1.
     * 1. 一般Datasource应使用Singleton pattern单列模型, 只创建一个DB的instance对象
     * 2. 类型可以通过调用指定的方法获取instance, 然后调用对象的方法
     * 3. 一般在启动时Open Connection获取数据, 在UI完全显示出来后加载; 如果open失败，则不显示UI
     * 4. 一般在关闭Application时Close Connection 关闭数据库的连接
     */
    private void testLifeCycle() {
        // For JavaFX 重写两个方法, 自定义生命周期
        // @Override public void init()
        // @Override public void start()
        // @Override public void stop()
    }

    /**
     * 解决方案 2.
     * 使用异步线程来处理背后耗时的操作, 获取和加工数据
     * UI界面应该给用户提示背后的运行进度
     */

    /**
     * 解决方案 3.
     * 可以使用Controller / Data binding来加载和渲染数据
     * 保持UI和Database的数据同步性，在UI执行数据更新时，需要确定Database的数据得到更新之后，才刷新UI界面的数据 !!
     * 需要更新Database中一切相关的数据，可能执行Transactions
     */
}