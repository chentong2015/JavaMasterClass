package JavaReflection.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// TODO. 必须标注保留策略，才能在运行时被反射检测
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseAnnotation {
}
