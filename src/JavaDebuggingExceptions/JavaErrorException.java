package JavaDebuggingExceptions;

// Error extends Throwable, 是所有errors的superclass
// - Error本身是一种unchecked exception
// - 不期望恢复, 一种严重的错误, OOM

// TODO. Error通常是被JVM保留下来使用，最好不要实现任何Error子类，也不应该抛出AssertionError
public class JavaErrorException {

}
