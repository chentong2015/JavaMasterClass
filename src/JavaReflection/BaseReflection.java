package JavaReflection;

// https://www.oracle.com/technical-resources/articles/java/javareflection.html
// 86. 如何通过反射创建对象？
// 87. 如何通过反射获取和设置对象私有字段的值？
// 88. 如何通过反射调用对象的方法？
public class BaseReflection {

    // 通过反射拿到添加在Method方法上的注解
    // Method method;
    // Class<? extends Annotation> type;  必须要是指定的注解@interface
    // Annotation annotation = method.getAnnotation(type);

    // object.getClass()
    // Returns the runtime class of this Object. 返回对象运行时的内存

    /**
     * Class.forName("BaseMonitors") 根据名称来找类型 ==> 反射
     * <p>
     * import java.lang.reflect.AnnotatedElement;
     * import java.lang.reflect.AnnotatedType;
     * import java.lang.reflect.Array;
     * import java.lang.reflect.Constructor;
     * import java.lang.reflect.Executable;
     * import java.lang.reflect.Field;
     * import java.lang.reflect.GenericArrayType;
     * import java.lang.reflect.GenericDeclaration;
     * import java.lang.reflect.InvocationTargetException;
     * import java.lang.reflect.Member;
     * import java.lang.reflect.Method;
     * import java.lang.reflect.Modifier;
     * import java.lang.reflect.Proxy;
     * import java.lang.reflect.RecordComponent;
     * import java.lang.reflect.Type;
     * import java.lang.reflect.TypeVariable;
     * <p>
     * import sun.reflect.generics.factory.CoreReflectionFactory;
     * import sun.reflect.generics.factory.GenericsFactory;
     * import sun.reflect.generics.repository.ClassRepository;
     * import sun.reflect.generics.repository.MethodRepository;
     * import sun.reflect.generics.repository.ConstructorRepository;
     * import sun.reflect.generics.scope.ClassScope;
     * import sun.security.util.SecurityConstants;
     * import sun.reflect.annotation.*;
     * import sun.reflect.misc.ReflectUtil;
     */

    // 利用反射：在运行时动态地返回指定名称的类型或者接口的Class对象
    private void testClassForName() {
        try {
            Class obj = Class.forName("ModelClass");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
