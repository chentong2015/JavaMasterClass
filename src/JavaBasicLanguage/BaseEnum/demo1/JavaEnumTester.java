package JavaBasicLanguage.BaseEnum.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaEnumTester {

    public static void main(String[] args) {
        List<EntityId> entities = new ArrayList<>();
        entities.add(EntityId.DEMO);
        entities.add(EntityId.SAMPLE);
        // FROM DemoEntity DEMO, SampleEntity SAMPLE 使用entityId作为EntityName(推荐使用全路径)的别名
        // FROM JavaBasicLanguage.BaseEnum.entity.DemoEntity DEMO, JavaBasicLanguage.BaseEnum.entity.SampleEntity SAMPLE
        String result = " FROM " + entities.stream().map(entityId -> entityId.getEntityName() + " "
                + entityId).collect(Collectors.joining(", "));
        System.out.println(result);
    }
}
