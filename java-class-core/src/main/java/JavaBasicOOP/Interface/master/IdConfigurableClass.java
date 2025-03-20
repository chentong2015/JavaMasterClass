package JavaBasicOOP.Interface.master;

// 由于IdConfigurable已经继承Configurable接口中方法原型
// 因此只需要直接继承IdConfigurable接口即可(Configurable接口被标记成@Deprecated)
public class IdConfigurableClass implements IdConfigurable, Configurable {

    @Override
    public void configure(String value, int count) {
        System.out.println("configure");
    }
}
