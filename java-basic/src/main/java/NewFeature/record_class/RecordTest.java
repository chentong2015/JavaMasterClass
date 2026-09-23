package NewFeature.record_class;

public class RecordTest {

    record Point(int x, int y) { }

    public static void main(String[] args) {
        RecordClass recordObject = new RecordClass(1, "name");
        System.out.println(recordObject.id());
        System.out.println(recordObject.name());
        System.out.println(recordObject);

        Point point = new Point(1, 2);
        System.out.println(point.x);
        System.out.println(point.y);
    }
}