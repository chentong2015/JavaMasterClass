package JavaBasicLanguage.BaseAnnotation;

// 注解的声明位置：独立声明，或者嵌套在class内部
public class AnnotationRestriction {

    /* Legal: an annotation interface can be a member interface */
    // @interface A1 {}
    // 
    // void m() {
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
    //               within the body of local class D,
    //               even as a member of a class E nested in D */
    //            @interface A4 {}
    //        }
    //     }
    // }


}
