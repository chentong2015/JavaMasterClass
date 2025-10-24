package Interface;

// TODO. 接口中方法支持四种限定符:
//  - public                   默认限定符, 只提供方法原型
//  - default, private, static 必须提供方法主体的实现
public interface IBaseFunction {

    // 方法原型默认使用public修饰
    public void test();

    // TODO. Java 8支持缺省方法, 提供方法默认实现 => 可在实现类型中重写
    public default void testDefault() {
        System.out.println("IBaseFunction.test2()");
    }

    // TODO. private方法必须提供默认实现 => 不能被Override
    private void testPrivate() {
        System.out.println("IBaseFunction.testPrivate()");
    }

    // TODO. static方法必须提供默认实现 => 不能被Override
    static void testStatic() {
        System.out.println("IBaseFunction.testStatic()");
    }

    // 方法不能设置final限定, 方法必须被实现
    // final long getId();

    // 方法不能设置protected限定, 接口必须有继承
    // protected void testProtected();
    //
    // protected void testProtectedFull() {
    //    System.out.println("testProtected");
    // }
}
