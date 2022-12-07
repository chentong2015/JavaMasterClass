package JavaDataStructure.HashMap;

import java.util.HashMap;
import java.util.Map;

public class DemoHashmap {

    // HashMap中找不到对应的key，value返回的值为null，非空判断
    public static void main(String[] args) {
        Map<String, MyClass> myClassMap = new HashMap<>();
        myClassMap.put("item1", new MyClass());
        myClassMap.put("item2", new MyClass());

        MyClass value = myClassMap.get("it");
        System.out.println(value);
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
