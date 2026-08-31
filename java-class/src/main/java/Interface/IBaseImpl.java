package Interface;

// TODO. 接口的实现表示一种"Can-Do"的能力
// 1. 可以同时实现多个接口
// 2. 必须实现接口中声明的所有方法原型
public class IBaseImpl implements IBaseFunction {

    private int number;

    public IBaseImpl(int number) {
        this.number = number;
    }

    // TODO: 接口方法的实现, 一种Override重写
    @Override
    public void test() {
        System.out.println("No action taken");
    }

    // TODO. 支持对Default缺省方法的Override重写
    @Override
    public void testDefault() {
        System.out.println("No action taken");
    }
}
