package JavaBasicOOP.BaseAnnotation;

// 注意注解的声明位置约束：独立声明，或者嵌套在class内部，
public @interface AnnotationRestriction {

    /* Legal: an annotation interface can be a member interface */
    // 可以声明嵌套的注解, 通过注解名称调用AnnotationRestriction.InnerAnnotation.Class
    @interface InnerAnnotation {
    }

    // 无法在注解的方法内部或者嵌套类中进行声明
    // void method() {
    //     /* Illegal: an annotation interface cannot be a local interface */
    //     @interface A2 {}
    //
    //     class D {
    //        /* Illegal: an annotation interface cannot be specified anywhere
    //           within the body of local class D */
    //        @interface A3 {}
    //
    //        class E {
    //            /* Illegal: an annotation interface cannot be specified anywhere
    //               within the body of local class D, even as a member of a class E nested in D */
    //            @interface A4 {}
    //        }
    //     }
    // }
}
