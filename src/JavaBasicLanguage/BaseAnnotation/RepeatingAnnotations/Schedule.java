package JavaBasicLanguage.BaseAnnotation.RepeatingAnnotations;

import java.lang.annotation.Repeatable;

// 括号中提供包含重复注解的类型Container Annotation
// Repeating @Schedule annotations is stored in a @Schedules annotation
@Repeatable(Schedules.class)
public @interface Schedule {

    String dayOfMonth() default "first";

    String dayOfWeek() default "Mon";

    int hour() default 12;
}
