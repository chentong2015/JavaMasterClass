package JavaBasicLanguage.BaseInterface.master;

public interface IdConfigurable extends Configurable {

    // 实现父类接口中的方法，将其转换成default默认方法，包含方法的实现主体
    @Override
    default void configure(String value, int count) {
    }
}
