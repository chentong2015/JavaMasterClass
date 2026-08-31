package Object;

// 推荐为类型重写toString()方法
// 不推荐为helper类和enum类重写toString()方法
public class ObjectToString {

    private String value = "field value";

    // TODO. Object对象默认返回类路径+@地址
    //  getClass().getName() + "@" + Integer.toHexString(hashCode());
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(object.toString()); // java.lang.Object@6ce253f1

        ObjectToString instance = new ObjectToString();
        System.out.println(instance.toString()); // Object.ObjectToString/1406718218
    }

    // TODO. 重写toString()方法原则: 提供类型必要的关注信息
    @Override
    public String toString() {
        // return "value = " + value; 返回属性相关信息
        return getClass().getCanonicalName() + "/" + hashCode();
    }
}
