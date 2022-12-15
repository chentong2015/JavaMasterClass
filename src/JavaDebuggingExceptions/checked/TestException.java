package JavaDebuggingExceptions.checked;

// 自定义异常的类型, 提供两种构造器, 允许提供异常的信息
// TODO: 考虑不同异常的可恢复情况，是否真的是异常
public class TestException extends Exception {

    public TestException() {
        super();
    }

    public TestException(String str) {
        super(str);
    }

    // 自定义异常类型中封装的属性
    private boolean status;

    public TestException(String str, boolean status) {
        super(str);
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }
}
