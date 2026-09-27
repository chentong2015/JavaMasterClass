package Object;

// 推荐为类型重写toString()方法
// 不推荐为helper类和enum类重写toString()方法
public class ObjectToString {

    // TODO. toString()方法默认返回类路径+@地址
    // getClass().getName() + "@" + Integer.toHexString(hashCode());
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(object.toString()); // java.lang.Object@6ce253f1

        ObjectToString instance = new ObjectToString();
        System.out.println(instance.toString()); // Object.ObjectToString/1406718218

        // 数组类型的toString()返回特定的类型标志
        byte[] bytes = "hello".getBytes();
        System.out.println(bytes); // [B + @hashcode

        int[] ints = new int[2];
        System.out.println(ints); // [I + @hashcode

        String[] strings = new String[2];
        System.out.println(strings); // [Ljava.lang.String; + @hashcode
    }

    // TODO. 重写toString()方法原则: 提供类型必要的关注信息
    @Override
    public String toString() {
        // return "value = " + value; 返回属性相关信息
        return getClass().getCanonicalName() + "/" + hashCode();
    }
}
