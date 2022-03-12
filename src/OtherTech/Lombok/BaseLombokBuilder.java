package OtherTech.Lombok;

// 为类型注入builder设计模式
// @Builder
public class BaseLombokBuilder {

    private int id;
    private String name;

    // 自带标准的build构建
    private void testClassBuilder() {
        // BaseLombokBuilder.BaseLombokBuilderBuilder builder = BaseLombokBuilder.builder();
        // builder.id(1);
        // builder.name("test");
        // BaseLombokBuilder instance = builder.build();
    }
}
