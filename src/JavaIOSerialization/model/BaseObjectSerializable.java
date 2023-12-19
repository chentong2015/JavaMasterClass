package JavaIOSerialization.model;

import java.io.Serializable;

public class BaseObjectSerializable implements Serializable {

    private static final long serialVersionUID = 1L;

    private int ID;
    private String Name;

    public BaseObjectSerializable(int ID, String name) {
        this.ID = ID;
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
