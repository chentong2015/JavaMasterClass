package JavaReflection;

public class BaseReflection {
    // TODO: Java 反射基础
    // https://www.oracle.com/technical-resources/articles/java/javareflection.html

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

    private void testClassForName() {
        try {
            Class obj = Class.forName("ModelClass");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
