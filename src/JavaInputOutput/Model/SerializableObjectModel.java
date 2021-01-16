package JavaInputOutput.Model;

import java.io.Serializable;

// 可序列化的对象模型
public class SerializableObjectModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int ID;
    private String Name;

    public SerializableObjectModel(int ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
