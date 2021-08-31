package JavaBasicLanguage.Base04Annotation;

// 自定义的注解一定extends继承自Annotation接口 !!
@interface testAnnotation {

}

public class BaseJavaAnnotation {

    public static void main(String[] args) {

        System.out.println(testAnnotation.class.isAnnotation());

    }

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

    // MyBatis源码注解的实现 ==> 如何从注解中获取SQL语句的 ?
    // @Documented
    //@Retention(RetentionPolicy.RUNTIME)
    //@Target(ElementType.METHOD)
    //@Repeatable(Select.List.class)
    //public @interface Select {
    //  /**
    //   * Returns an SQL for retrieving record(s).
    //   * @return an SQL for retrieving record(s)
    //   */
    //  String[] value();
    //
    //  /**
    //   * @return A database id that correspond this statement
    //   * @since 3.5.5
    //   */
    //  String databaseId() default "";
    //
    //  /**
    //   * The container annotation for {@link Select}.
    //   * @author Kazuki Shimizu
    //   * @since 3.5.5
    //   */
    //  @Documented
    //  @Retention(RetentionPolicy.RUNTIME)
    //  @Target(ElementType.METHOD)
    //  @interface List {
    //    Select[] value();
    //  }
    //}

    // @Select("SELECT * FROM blog WHERE id = #{id}")
    // Blog selectBlog(int id);

    // public @interface Select {
    //   String[] value();
    //   ...
    // }
    // 由于注解中使用的是数组，因此在使用@Select注解时，可以提供多个SQL，返回多结果
    // @Select{"SELECT id, name FROM t_user WHERE id = #{id}", "Select * from users"}
}
