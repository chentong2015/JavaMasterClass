package JavaGenerics.model;

public abstract class GenericModel {

    private String name;

    public GenericModel(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
