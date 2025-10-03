package Interface.master;

public class SequenceGenerator implements IGenerator {

    // 以下通过调用接口定义的default方法，等效于没有重写，报错默认
    @Override
    public void configure() {
        IGenerator.super.configure();
    }

    // 重写接口的default方法，而不是实现
    @Override
    public boolean supportBatchInserts() {
        return false;
    }
}
