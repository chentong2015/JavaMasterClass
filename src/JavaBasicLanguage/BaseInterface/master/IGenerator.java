package JavaBasicLanguage.BaseInterface.master;

// 定义接口包含常量名称和默认方法(可在实现类型中重写)
public interface IGenerator {

    String ENTITY_NAME = "entity_name";

    default void configure() {
    }

    default boolean supportBatchInserts() {
        return true;
    }
}
