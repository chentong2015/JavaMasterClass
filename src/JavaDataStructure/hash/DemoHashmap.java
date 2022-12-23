package JavaDataStructure.hash;

import java.util.HashMap;
import java.util.Map;

public class DemoHashmap {

    // HashMap中找不到对应的key，value返回的值为null，非空判断
    public static void main(String[] args) {
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
