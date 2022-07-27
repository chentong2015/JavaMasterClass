package JavaBasicLanguage.BaseEnum;

import JavaBasicLanguage.BaseEnum.entity.DemoEntity;
import JavaBasicLanguage.BaseEnum.entity.SampleEntity;

public enum EntityId {

    // 枚举的定义值范围，通过构造器设置初始化
    DEMO(DemoEntity.class),
    SAMPLE(SampleEntity.class);

    private final Class<?> entityClass;

    EntityId(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    // 定义Enum枚举类型的成员方法，通过枚举的实际值进行调用
    public Class<?> getEntityClass() {
        return entityClass;
    }

    public String getEntityName() {
        return getEntityClass().getName();
    }

    public String getField(String field) {
        return this + "." + field;
    }
}
