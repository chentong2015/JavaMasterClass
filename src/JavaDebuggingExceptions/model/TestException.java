package JavaDebuggingExceptions.model;

// 自定义异常的类型, 提供两种构造器, 允许提供异常的信息
public class TestException extends Exception {

    public TestException() {
        super();
    }

    public TestException(String str) {
        super(str);
    }
}
