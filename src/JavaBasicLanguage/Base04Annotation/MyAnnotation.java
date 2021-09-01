package JavaBasicLanguage.Base04Annotation;

// 1. MyAnnotation extends Annotation 满足继承关系
// 2. Meta-annotations 使用在注解上的元注释
//    @Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
//    @Retention(RetentionPolicy.RUNTIME) 保留政策
//    @Documented
public @interface MyAnnotation {
}
