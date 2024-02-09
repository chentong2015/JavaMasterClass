package JavaReflection;

import JavaReflection.model.BaseReflectionClass;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class JavaRefectionFields {

    private void getClassFields() throws ClassNotFoundException {
        Class clazz = Class.forName("com.example.main.ClassName");
        Field[] fieldList = clazz.getDeclaredFields();
        Field checkFiled = fieldList[0];

        // 返回属性所在类型的包路径
        checkFiled.getType().getPackageName();

        String fieldName = checkFiled.getName();
        Type fieldType = checkFiled.getType();
        String fieldTypeName = fieldType.getTypeName();
        int modifierId = checkFiled.getModifiers();
        // 解码成员访问修饰符
        String modifierName = Modifier.toString(modifierId);
    }

    // TODO. 通过反射获得类型private field字段
    private void getPrivateClassFields() throws NoSuchFieldException, IllegalAccessException {
        BaseReflectionClass instance = new BaseReflectionClass();

        // 1. 获取指定名称的字段
        Field field = BaseReflectionClass.class.getDeclaredField("name");

        // 2. 必须设置可访问性才能读写指定的字段
        field.setAccessible(true);

        // 3. 获取指定对象上的字段值
        String name = (String) field.get(instance);
        System.out.println(name);
    }

    private void testChangingFieldValue() {
        BaseReflectionClass invokeObject = new BaseReflectionClass();
        try {
            Class clszz = Class.forName("com.example.main.ClassName");
            Field field = clszz.getField("d");
            field.setDouble(invokeObject, 12.34);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
