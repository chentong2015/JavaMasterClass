package JavaReflection.beans;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

// Introspector 在运行时修改Java Bean对象属性/验证对象的属性值
public class JavaBeanIntrospector {

    public static void main(String[] args) throws Exception {
        BeanKeyword beanKeyword = new BeanKeyword(1, "id", "des");

        System.out.println("Before: " + beanKeyword.getKeywordSourceId());
        testBeanIntrospector(BeanKeyword.class, beanKeyword);
        System.out.println("After:" + beanKeyword.getKeywordSourceId());
    }

    // 遍历Object对象所有String类型的属性，对其执行额外的操作
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
