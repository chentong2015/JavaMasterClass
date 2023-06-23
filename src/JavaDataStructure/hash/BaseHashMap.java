package JavaDataStructure.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Map封装: 不建议将Map(或者在边界上的其他接口)在系统中传递
 * 1. 本质上是一种数据结构, 一种存储数据的方式
 * 2. 没有固定的排序存储顺序, 添加的顺序和输出的顺序不一致
 * 3. map<key, value>映射关系, key一般使用不可变类型
 * 4. 可以通过map构建一张图形网络
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
}


