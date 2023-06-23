package JavaDataStructure.hash;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class BaseHashMapTest {

    // TODO. HashMap和HashTable区别:
    // 1. HashMap中可存储null值，HashTable的key和value均不能设置成null
    // 2. HashMap数据的存储是非同步的，Hashtable数据的存储和获取都是同步的，会对性能造成影响
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", null);
        map.put(null, "value");
        System.out.println(map.get(null));

        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put(null, "value");
        System.out.println(hashtable.get(null));
    }

    // HashMap中找不到对应的key，value返回的值为null，非空判断
    public static void test(String[] args) {
        Map<String, MyClass> myClassMap = new HashMap<>();
        myClassMap.put("item1", new MyClass());
        myClassMap.put("item2", new MyClass());

        System.out.println(myClassMap.containsKey("it"));
        System.out.println(myClassMap.get("it"));
        System.out.println(myClassMap.get("item1"));
    }

    static class MyClass {
        private int id;

        public MyClass() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
