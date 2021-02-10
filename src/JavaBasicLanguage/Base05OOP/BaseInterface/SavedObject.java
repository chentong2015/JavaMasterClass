package JavaBasicLanguage.Base05OOP.BaseInterface;

import JavaBasicLanguage.Base05OOP.BaseInterface.Model.ISaveable;

import java.util.List;

public class SavedObject implements ISaveable {

    // 
    @Override
    public List<String> write() {
        return null;
    }

    @Override
    public void read(List<String> savedValues) {

    }

    // 实现ITelephone接口中的方法
    @Override
    public void powerOn() {

    }

    @Override
    public void dial(int phoneNumber) {

    }

    @Override
    public boolean callPhone(int phoneNumber) {
        return false;
    }

    @Override
    public boolean isRinging() {
        return false;
    }
}
