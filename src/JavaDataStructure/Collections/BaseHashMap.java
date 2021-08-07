package JavaDataStructure.Collections;

import java.util.HashMap;
import java.util.Map;

/**
 * 注意对Map的封装，不建议将Map(或者在边界上的其他接口)在系统中传递
 * 1. 本质上是一种数据结构, 一种存储数据的方式
 * 2. 没有固定的排序存储顺序, 添加的顺序和输出的顺序是不一致的 !!
 * 3. map<key, value>映射关系, key一般使用不可变类型
 * 4. 通过map可以构建一张图形网络
 */
public class BaseHashMap {

    private final int locationID;  // 地点ID
    private final String description; // 地点描述
    private final Map<String, Integer> exits; // <direction朝向, 地点ID>

    public BaseHashMap(int locationID, String description) {
        this.locationID = locationID;
        this.description = description;
        this.exits = new HashMap<>();
        this.exits.put("Q", 0);
        this.exits.getOrDefault("X", 0); // 提供查找的默认值，避免出现异常
    }

    public void addExits(String direction, int locationID) {
        this.exits.put(direction, locationID);
    }

    /**
     * Map<String, ReferenceType> baseMap = new HashMap<>();
     * 在baseMap中初始化数据
     * 1. Map<String, ReferenceType> cloneMap = baseMap;                ======> 直接赋值引用的方式，一定是Shadow Copy !!!
     * 2. Map<String, ReferenceType> cloneMap = new HashMap<>(baseMap); ======> 参数@NotNull
     * 3. Map<String, ReferenceType> cloneMap = new HashMap<>();
     * cloneMap.putAll(baseMap);
     * 通过cloneMap修改存储数据
     * 结果如下:
     * 1. 如果ReferenceType是不可变类型 (String, Integer); 则体现为Deep Copy的效果
     * 2. 如果ReferenceType是可变类型 (自定义Class); 则体现为Shadow Copy的效果
     * 3. 对原始的列表追加新的元素，则不属于拷贝的内容
     */
    public Map<String, Integer> getExits() {
        return new HashMap<>(this.exits);
    }

    public void testBasicMap() {
        Map<String, String> languages = new HashMap<>();
        languages.put("Java", "good level");       // put有value类型的返回值，可以用来判断值是否是第一次添加 !! 如果不是，会返回之前的一个值 !!
        languages.put("Java", "basic level");      // 更改原来key值所对应的value值 !!! 不会报错x
        System.out.println(languages.get("Java"));
        languages.put(null, null);                 // HashMap中允许添加null的数据 !!

        languages.remove("key");
        languages.remove("key", "value");          // 移除指定的key和value
        languages.replace("C++", "Good level");    // 返回null，或者返回替换前的原始值 !!
        languages.replace("C++", "oldValue", "newValue");

        if (languages.containsKey("Java") && languages.containsValue("good level")) { // ==> C#同样拥有两个方法 !!
            System.out.println("Find the key java with value");
        }
        for (String key : languages.keySet()) {
            System.out.println(key + ": " + languages.get(key));  // 返回的顺序不一定是put添加值的顺序 !!!!
        }
    }

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
}


