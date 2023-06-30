package JavaDataStructure.hash;

import java.util.HashMap;
import java.util.Map;

public class BaseHashMapMain {

    public static void main(String[] args) {
        Map<Integer, BaseHashMap> locations = new HashMap<>();  // <key, value>: <locationID, Location>
        locations.put(0, new BaseHashMap(0, "the first location"));
        locations.put(1, new BaseHashMap(1, "the second location"));
        locations.put(2, new BaseHashMap(2, "the third location"));
        locations.get(0).addExits("W", 1); // 构成一种联通的网络结构，节点之间的通路关系 !!
        locations.get(0).addExits("S", 1); // 每一个Location包含可以前进的方向
        locations.get(1).addExits("N", 2);
        locations.get(2).addExits("E", 0);

        // Input the description 输入位置的方向
        Map<String, Integer> exists = locations.get(0).getExits();
        for (String exit : exists.keySet()) {
            System.out.print(" " + exit);
        }
        // 使用Entry来遍历Set中的数据 -> getKey() & getValue()   !!!!!
        for (Map.Entry<String, Integer> item : exists.entrySet()) {
            System.out.println(item.getKey() + " " + item.getValue());
        }

        // 根据选择的direction可以遍历到图中的指定的节点 !!!!   ====> 只检索关键字 !!
        Map<String, String> mapDirections = new HashMap<>();
        mapDirections.put("NORTH", "N");
        mapDirections.put("SOUTH", "S");
        mapDirections.put("EAST", "E");
        // 通过map来检查direction一致性, Map到exists的direction
    }

    public void testMapApis() {
        Map<String, String> languages = new HashMap<>();
        languages.put("Java", "good level");       // put方法有返回值，如果值不是首次添加，则返回旧值
        languages.put("Java", "basic level");      // 更改原来key值所对应value，不会报错
        System.out.println(languages.get("Java"));
        languages.put(null, null);                 // HashMap中允许添加null的数据

        languages.remove("key");
        languages.remove("key", "value");          // 移除指定的key和value
        languages.replace("C++", "Good level");    // 返回null，或者返回替换前的原始值
        languages.replace("C++", "oldValue", "newValue");

        if (languages.containsKey("Java") && languages.containsValue("good level")) {
            System.out.println("Find the key java with value");
        }

        // 返回的顺序不一定是put添加值的顺序，Map的数据存储无序
        for (String key : languages.keySet()) {
            System.out.println(key + ": " + languages.get(key));
        }
    }

    // merge()可以对现有存在的键值进行"合并"操作
    private void testMapMerge() {
        Map<String, Integer> warnings = new HashMap<>();
        warnings.put("key", 1);
        warnings.merge("key", 2, Integer::sum);
        System.out.println(warnings.get("key"));
    }
}
