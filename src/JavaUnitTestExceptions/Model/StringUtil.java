package JavaUnitTestExceptions.Model;

public class StringUtil {

    private int charAdded = 0;
    private StringBuilder stringBuilder = new StringBuilder();

    public void addChar(StringBuilder stringBuilder, char c) {
        this.stringBuilder.append(c);
        charAdded++;
    }
}
