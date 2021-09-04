package JavaBasicLanguage.Base04Annotation;

// 注解的声明位置：独立声明，或者嵌套在class内部
public class AnnotationRestriction {

    // @interface A1 {}  /* Legal: an annotation interface can be a member interface */
    //
    //    void m() {
    //        @interface A2 {}  /* Illegal: an annotation interface cannot be a local interface */
    //
    //        class D {
    //            @interface A3 {}  /* Illegal: an annotation interface cannot be specified anywhere within the body of local class D */
    //
    //            class E {
    //                @interface A4 {}
    //                  /* Illegal: an annotation interface cannot be specified anywhere within the body of local class D, even as a member of a class E nested in D */
    //            }
    //        }
    //    }
}
