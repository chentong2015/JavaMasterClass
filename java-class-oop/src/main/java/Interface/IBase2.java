package Interface;

import java.util.List;

// 接口可以继承自接口
public interface IBase2 extends IBase {

    // TODO. 使用泛型接口, 最大限度的解耦
    List<String> write();

    void read(List<String> savedValues);
}