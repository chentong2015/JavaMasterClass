package JavaDataStructure.Collections;

import JavaBasicLanguage.Base07Enum.BaseEnum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Set的特点：  =====> TreeSet, HashSet
// 1. 没有定于顺序的限制 no defined ordering, it's chaotic 混乱  ===> 不能够取指定位置的Item
// 2. 不能包含重复的数据, 默认会通过equals()来判断比较              ===> 需要同时重写 equals() & hashCode()
// 3. Set中的值相当于Map中的Key，Set不具备value  !!!!
// 4. Set最好的实现使HashSet，Use Hashes to store the items使用哈希值来存储元素  ==> 操作非常的迅速 union, intersection

/**
 * Set基本操作方法： size(); add(); remove(); contains();
 */
public final class BaseSet {
    private final String name; // name + bodyType可以构成Key + 组合value
    private final BaseEnum bodyType;
    private final Set<BaseSet> sets;

    public BaseSet(String name, double period) {
        this.name = name;
        this.bodyType = BaseEnum.PENNY;
        this.sets = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void addSetItem(BaseSet newItem, BaseEnum type) {
        if (type == BaseEnum.DIME) {
            this.sets.add(newItem);
        }
    }

    /**
     * hashCode() ==> by converting the internal address of the object into an integer
     * ex. @15aeb7ab -> 363771819
     * *
     * If two objects are equal according to the {@code equals(Object)} method, then calling the {@code hashCode}
     * method on each of the two objects must produce the same integer result
     * 不同对象返回不同的hashCode(离散integer)，通过HashMap来提供HashTable表的支持
     */
    // 必须满足5个条件的约束，才是正确的equals()方法: 可逆性，传递性, 对称性 ....
    // 标记final 子类不能再修改判断的规则
    @Override
    public final boolean equals(Object set) { // 该方法返回true才能说明两个对象是相等的 !!!
        // Object.equals() 方法直接比较两个对象的引用是否相等 !! ===> 通过自定义，使得不同引用的对象作为是相等的对象来处理 !!!
        if (this == set) { // 比较对象的引用，判断是相同的对象
            return true;
        }
        // 是否是相同类型，母类和子类型是不等的 ===> 这个是没有必要的
        // 1. 类型本身是不能被继承的
        // 2. 因为所使用field是pirate final的，在子类型中不会被改变 !!!
        if (set == null || (set.getClass() != this.getClass())) {
            return false;
        }
        // instanceof: 子类型的对象满足IS-A的关系，所以判断是为True
        if (set instanceof BaseSet) {
            BaseSet theSet = (BaseSet) set;
            // To check the name value
        }
        String compareName = ((BaseSet) set).getName();
        return this.name.equals(compareName);
    }

    /**
     * 具有相同hashCode的对象不一定是equals()的  !!!!!
     * 使用同一个String对象(初始化类型的对象)，使得这个String对象的hashCode == BaseSet对象的hashCode, 但是两者是不equals()的 !!!
     * hashCode 决定了对象所归属的Bucket位置 => HashSet/HashMap
     */
    @Override
    public final int hashCode() {
        return this.name.hashCode();  // 只用类型的field的hashCode来作为整个类型的hashCode
        // return this.name.hashCode() + 1; 通过添加偏移量来取解决上面的问题
        // return 0; 不能直接返回0
        // 需要计算出来的值是离散的，同时具有唯一性
    }

    public void testSet() {
        Set<BaseSet> mySets = new HashSet<>();
        mySets.add(new BaseSet("name", 100));
        // 添加的新的对象被认为是新的对象，不会造成冲突 !! 需要重写equal()方法; 提供自定义的对象比较方式
        mySets.add(new BaseSet("name", 200));
        for (BaseSet set : mySets) {
            System.out.println(set.getName());
        }
    }

    public static void main(String[] args) {
        Set<Integer> squares = new HashSet<>();
        Set<Integer> cubes = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            squares.add(i * i);
            cubes.add(i * i * i);
        }
        // Union 合并的时候，不会添加同样的Item到Set中             ====> C#区别： evenNumbers.Count & numbers.UnionWith(oddNumbers);
        Set<Integer> union = new HashSet<>(squares);
        union.addAll(cubes);
        System.out.println("The amount size is " + union.size());  // ====> Outer Join

        // Intersection: retainAll() 提取公共部分，保留全部
        Set<Integer> intersection = new HashSet<>(squares);   // ====> Inner Join
        intersection.retainAll(cubes);

        // removeAll() 移除指定的数据
        Set<String> nature = new HashSet<>();
        String[] natureWords = {"all", "nature", "is", "but", "art"};
        nature.addAll(Arrays.asList(natureWords));

        Set<String> divine = new HashSet<>();
        String[] divineWords = {"to", "err", "is", "human", "all"};
        divine.addAll(Arrays.asList(divineWords));

        Set<String> diff1 = new HashSet<>(nature);
        diff1.removeAll(divine); // {"nature", "but", "art"};   ====> Left Join 等效于SQL中的实现

        // ContainsAll() 判断一个Set是否是另一个Set的子集
        // 不改变Set，只是返回判断的结果
        if (nature.containsAll(divine)) {
            System.out.println("Divine is a subSet of nature");
        }

        // Array -> ArrayList -> Set 不同集合之间的转换 !!
        String sentence = "One day in the year of the fox";
        String[] wordsArray = sentence.split(" ");
        List<String> wordsList = Arrays.asList(wordsArray); //
        Set<String> words = new HashSet<>();
        words.addAll(wordsList);
    }
}
