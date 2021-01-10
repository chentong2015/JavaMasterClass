package JavaBasicLanguage.Base05OOP.Composation;

public class Monitor {

    private int size;
    private String name;
    private int dimensions;

    public Monitor(int size, String name, int dimensions) {
        this.size = size;
        this.name = name;
        this.dimensions = dimensions;
    }

    public void loadMonitor() {
        System.out.println("Load monitor ...");
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDimensions() {
        return dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }
}
