package JavaBasicLanguage.Base01OOP.BaseInterface;

// implements 接口的实现
// 1. CAN-DO 接口表示一种能力
// 2. 必须实现接口中声明的所有的方法原型
// 3. 可以实现多个接口
// 4. 可以将接口强制转换成实现它的类型, 为了使用特定类型的方法 ===> 根据需求而定 !!!
public class DeskPhone implements ITelephone {

    private int myNumber;
    private boolean isRinging;

    public DeskPhone(int myNumber) {
        this.myNumber = myNumber;
    }

    // 接口方法的实现, 也是一种重写Override !!! ===> C#中不写override
    // 可以不写  @Override
    @Override
    public void powerOn() {
        System.out.println("No action taken");
    }

    @Override
    public void dial(int phoneNumber) {
        System.out.println("Now ringing " + phoneNumber + " on deskPhone");
    }

    @Override
    public boolean callPhone(int phoneNumber) {
        isRinging = (phoneNumber == myNumber);
        return isRinging;
    }

    @Override
    public boolean isRinging() {
        return isRinging;
    }
}
