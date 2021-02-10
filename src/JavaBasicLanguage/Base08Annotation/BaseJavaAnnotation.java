package JavaBasicLanguage.Base08Annotation;

// TODO: Java Annotation 注解机制               ====> C#区别: Custom Attribute定制特性
public class BaseJavaAnnotation {

    // Java 语言定义文档 > 9.6 Annotation Types
    // https://docs.oracle.com/javase/tutorial/java/annotations/declaring.html
    // https://docs.oracle.com/javase/7/docs/technotes/guides/language/annotations.html

    /**
     * Annotation 的声明
     * public @interface RunWith {
     * }
     */

    /**
     *
     @Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
     @Retention(RetentionPolicy.RUNTIME)
     @Documented
     @API( status = Status.STABLE,
     since = "5.0"
     )
     @Testable public @interface Test {
     }
     */
}
