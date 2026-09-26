package OOP_Types.Interface;

// TODO. 接口中方法支持四种限定符:
//  - public  默认限定符, 只提供方法原型
//  - default, private, static 必须提供方法主体的实现
public interface IBaseFunc {

    // 方法原型默认使用public修饰 ==> 必须被Override
    public void test();


    // TODO. JDK8 支持缺省方法, 必须提供默认实现 => 可以被Override
    public default void testDefault() {
        System.out.println("IBaseFunction.test2()");
    }

    // TODO. JDK9 支持private方法, 必须提供默认实现 => 不能被Override
    private void testPrivate() {
        System.out.println("IBaseFunction.testPrivate()");
    }

    // TODO. static方法, 必须提供默认实现 => 不能被Override
    static void testStatic() {
        System.out.println("IBaseFunction.testStatic()");
    }


    // 方法不能设置final限定, 方法必须被实现
    // final long getId();

    // 方法不能设置protected限定: protected是给子类访问设计的, 而接口只有实现类 !!
    // protected void testProtected();
    //
    // protected void testProtectedFull() {
    //    System.out.println("testProtected");
    // }
}
