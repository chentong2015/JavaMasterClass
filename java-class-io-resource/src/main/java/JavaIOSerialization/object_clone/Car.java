package JavaIOSerialization.object_clone;

import java.io.Serializable;

public class Car implements Serializable {

    private static final long serialVersionUID = -5713945027627603702L;

    private String brandName;

    public Car(String brandName) {
        this.brandName = brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandName() {
        return brandName;
    }
}
