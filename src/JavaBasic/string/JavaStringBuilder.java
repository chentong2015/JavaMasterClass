package JavaBasic.string;

// TODO: 可变的字符串类型, 两种类型操作基本一致
// 1. StringBuffer  适用于多线程，方法都通过"synchronized"加锁
//    StringBuffer实例基本总是用于单线程中，而知悉的却是内部同步，造成不必要的过度同步 !!
// 2. StringBuilder 适用于单线程，没有冲突的程序中
public class JavaStringBuilder {

    private void testStringBufferBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("first str");
        stringBuilder.append("second str");
        // 插入到指定位置，往前插入数据
        stringBuilder.insert(0, "table_name");
        stringBuilder.append(System.getProperty("line.separator"));
        stringBuilder.append("ending");
        String result = stringBuilder.toString();
    }
}
