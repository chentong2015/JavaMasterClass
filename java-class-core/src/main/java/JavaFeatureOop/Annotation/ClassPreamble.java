package JavaFeatureOop.Annotation;

// 在类型上直接使用自定义的注解，直接设置成员的值
// @ClassPreamble (
//   author = "John Doe",
//   date = "3/17/2002",
//   currentRevision = 6,
//   lastModified = "4/12/2004",
//   lastModifiedBy = "Jane Doe",
//   reviewers = {"Alice", "Bob", "Cindy"}
// )
// public class DemoClass {}
public @interface ClassPreamble {

    // 一定继承自extends Annotation
    // Annotation types are a form of interface

    // TODO: Annotation是一种接口的形式，声明方式和接口类似，同时可以设置default value
    String author();

    String date();

    int currentRevision() default 1;

    String lastModified() default "N/A";

    String lastModifiedBy() default "N/A";

    String[] reviewers();
}