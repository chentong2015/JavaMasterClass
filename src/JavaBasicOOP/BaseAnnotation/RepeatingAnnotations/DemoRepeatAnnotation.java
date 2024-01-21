package JavaBasicOOP.BaseAnnotation.RepeatingAnnotations;

// 这里虽然包含多给相同类型的注解，但会被解析成一个Container Annotation
@Schedule(dayOfMonth = "last")
@Schedule(dayOfWeek = "Fri", hour = 23)
// @Schedules({@Schedule(dayOfMonth="last")}) 不能再同时使用Annotation Container作用
public class DemoRepeatAnnotation {

    // 使用重复注解的场景: 每一个Annotation提供一种执行条件
    // Use a timer service that enables you to run a method at a given time or on a certain schedule
    // Similar to the UNIX cron service
}
