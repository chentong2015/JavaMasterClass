package JavaBasicLanguage.BaseInterface.base;

// implements 接口的实现
// 1. CAN-DO 接口表示一种能力
// 2. 必须实现接口中声明的所有的方法原型
// 3. 可以实现多个接口
// 4. 为了使用特定类型的方法，可以将接口强制转换成实现它的类型(需要逻辑上面确定)
public class DeskPhone implements ITelephone {

    private int myNumber;
    private boolean isRinging;

    public DeskPhone(int myNumber) {
        this.myNumber = myNumber;
    }

    // TODO: @Override逐渐可以不明确的写出来
    // 接口方法的实现, 也是一种重写Override
    @Override
    public void powerOn() {
        System.out.println("No action taken");
    }
}
