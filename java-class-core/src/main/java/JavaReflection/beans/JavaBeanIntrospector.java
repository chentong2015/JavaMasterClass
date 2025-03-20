package JavaReflection.beans;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

// TODO. 在运行时通过.class获取Java Bean对象和类型信息
// - 验证类型的属性和方法
// - 触发方法并修改类型对象的属性值
public class JavaBeanIntrospector {

    // TODO. 属性具有Getter方法才能获取PropertyDescriptors
    public static void main(String[] args) throws Exception {
        PropertyDescriptor[] descriptors = Introspector.getBeanInfo(BeanDemo.class)
                .getPropertyDescriptors();
        System.out.println(descriptors[1].getPropertyType());

        BeanKeyword beanKeyword = new BeanKeyword(1, "id", "des");
        System.out.println("Before: " + beanKeyword.getKeywordSourceId());
        testBeanIntrospector(BeanKeyword.class, beanKeyword);
        System.out.println("After:" + beanKeyword.getKeywordSourceId());
    }

    // 遍历Object对象所有String类型的属性并执行额外操作
    private static void testBeanIntrospector(Class clazz, Object target) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        for (PropertyDescriptor propDesc : beanInfo.getPropertyDescriptors()) {
            if (String.class.equals(propDesc.getPropertyType())) {
                if (propDesc.getReadMethod() != null && propDesc.getWriteMethod() != null) {
                    // 获取Java Bean属性的名称
                    System.out.println(propDesc.getName());

                    // 获取Java Bean属性的值
                    String content = (String) propDesc.getReadMethod().invoke(target);
                    System.out.println(content);

                    // 调用Setter方法重写对象的属性值
                    propDesc.getWriteMethod().invoke(target, "new content");
                }
            }
        }
    }
}
