package Object;

// TODO. Java对象的HashCode本质是存储地址所隐射(转换)的正数值
// JVM控制内存管理，对象的真实内存地址不会暴露(JVM在GC时移动对象)
public class ObjectHashcode {

    public static void main(String[] args) {
        CodeClass instance = new CodeClass();
        System.out.println(instance); // @65ab7765 hashCode()的16进制表示
        System.out.println(instance.hashCode()); // -> 1705736037 计算的哈希值

        CodeClass instance1 = new CodeClass();
        System.out.println(instance1); // @e9e54c2
        System.out.println(instance1.hashCode()); // -> 455659002

        // TODO. 将Hashcode值重新转换回16进制的表示值
        System.out.println(Integer.toHexString(instance1.hashCode())); // @e9e54c2
    }

    static class CodeClass {
    }
}
