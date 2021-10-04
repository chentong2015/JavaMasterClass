package JavaBasicLanguage.Base04Annotation;

// TODO: 为代码添加注解来实现特殊功能
//       这种可扩展的元数据信息能够通过"反射"在运行时动态改变程序的行为

// Java源生API中提供的预定义好的注解类型
// 1. @Deprecated 表面已经废弃
// 2. @Override
// 3. @SuppressWarnings 告诉编译器抑制特定警告
// 4. @FunctionalInterface 提供丰富的lambda表达式的使用场景
// TODO: 作用到别的注解上的元注释Meta-annotations
// 1. @Retention  指定如何存储标记的注释
//    RetentionPolicy.RUNTIME 标记的注解被JVM保留, 可以在runtime环境下使用
// 2. @Documented 标明将注解记录进javadoc
// 3. @Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
// 4. @Inherited  标明该注解类型可以从class的super class上查找
//      the user queries the annotation type on a class declaration, and the class declaration has no annotation for this type,
//      then the class's superclass will automatically be queried for the annotation type
// 5. @Repeatable 标明注解可在target上使用多次
public @interface ClassPreamble { // 一定继承自extends Annotation

    // Annotation types are a form of interface
    // TODO: Annotation是一种接口的形式，声明方式和接口类似，同时可以设置default value

    String author();

    String date();

    int currentRevision() default 1;

    String lastModified() default "N/A";

    String lastModifiedBy() default "N/A";

    String[] reviewers();

    // 可以声明嵌套的注解, 通过注解名称调用ClassPreamble.InnerAnnotation.Class
    // @interface InnerAnnotation {}
}

// 在类型上直接使用自定义的注解
// @ClassPreamble (
//   author = "John Doe",
//   date = "3/17/2002",
//   currentRevision = 6,
//   lastModified = "4/12/2004",
//   lastModifiedBy = "Jane Doe",
//   reviewers = {"Alice", "Bob", "Cindy"}
// )
// public class DemoClass {}