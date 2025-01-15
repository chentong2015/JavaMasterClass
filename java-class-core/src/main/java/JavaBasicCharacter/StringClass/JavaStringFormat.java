package JavaBasicCharacter.StringClass;

import java.text.MessageFormat;
import java.text.ParsePosition;

public class JavaStringFormat {

    // TODO. String format格式化替换%s %d, 支持多个替换
    private void testStringFormat() {
        String query = "SELECT FROM %s entity where entity.name = '%s'";
        String entityName = "MyEntityClass";
        String result = String.format(query, entityName, "value");
        String str = String.format("Post comment %d:%d", 1, 10);
    }

    // 都数据进行指定Pattern的格式化处理
    public static void main(String[] args) {
        MessageFormat mf = new MessageFormat("{0,number,#.##}, {0,number,#.#}");
        Object[] objs = { 3.1415D };
        String result = mf.format(objs);
        System.out.println(result);
        // result now equals "3.14, 3.1"

        objs = null;
        objs = mf.parse(result, new ParsePosition(0));
        // objs now equals {new Double(3.1)}

        MessageFormat mf1 = new MessageFormat("{0}, {0}, {0}");
        String forParsing = "x, y, z";
        Object[] objs1 = mf1.parse(forParsing, new ParsePosition(0));
        // result now equals {new String("z")}
    }
}
