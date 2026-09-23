package JavaBasicTypeString.format;

import java.text.MessageFormat;

// MessageFormat 格式化字符串的输出形式
public class JavaMessageFormat {

    public static void main(String[] args) {
        // TODO. 使用 %d %s 占位符号格式化字符串
        String str = String.format("Post comment %d:%d", 1, 10);
        System.out.println(str);

        String query = "SELECT FROM %s entity where entity.name = '%s'";
        String result = String.format(query, "MyEntityClass", "value");
        System.out.println(result);

        // TODO. 使用 {0} {1} 占位符格式化字符串, 注意双''输出单'的效果
        String value = "Record ''{0}'': {1} field was truncated";
        String valueFormat = MessageFormat.format(value, "0001", "ID");
        System.out.println(valueFormat);

        // 根据特定格式输出小数点后位数
        MessageFormat mf = new MessageFormat("{0,number,#.##}, {0,number,#.#}");
        Object[] objs = { 3.1415D };
        System.out.println(mf.format(objs)); // 3.14, 3.1
    }
}
