package JavaBasicLanguage.Base01OOP.Encapsulation;

import java.util.HashMap;
import java.util.Map;

/**
 * 类型的封装：一旦类型对象被创建出来之后, 不可变化 ! 不允许外部改变类型对象
 * 1. 不提供setter方法, 不提供改变内部fields的方式
 * 2. Make all fields final and private
 * 3. Don't allow subclasses to override methods
 * 4. 如果实例对象的Fields包含了对可变对象的引用，不要让这些对象改变
 * >> 4.1 不要提供方法去改变"可变对象" mutable objects
 * >> 4.2 Don't share references to the mutable objects 不要存储外部通过构造器传递进来的引用 ...
 */

// 实例：Expose programs inner objects to external developers who don't have access to the source code
// they can not know implications of changes they might make to objects 对外部的封装，类型的不可变性 !!
// 1. 软件提供第三方扩展和插件
// 2. 浏览器支持第三方插件
// 3. Office Excel provides access to its objects to write in VBA
public class BaseImmutableClass {

    // 1. 没有提供fields的setter方法
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    public BaseImmutableClass(int locationID, String description, Map<String, Integer> exits) {
        this.locationID = locationID;
        this.description = description;
        // (@NotNull) 这里参数不能为空
        this.exits = new HashMap<>(exits);  // 2. 不直接存储外部传递进来的引用：做一个深度拷贝的效果
        this.exits.put("Q", 0);
    }

    // 3. 标记final：不能被重写的方法
    // 4. 不将可变对象的引用暴露到外部
    public final Map<String, Integer> getExits() {
        return new HashMap<>(this.exits);
    }

    // 5. 可以构建getter方法
    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }
}
