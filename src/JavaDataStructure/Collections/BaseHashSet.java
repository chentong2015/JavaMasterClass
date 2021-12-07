package JavaDataStructure.Collections;

import JavaBasicLanguage.BaseEnum.EnumMaster;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Set<T>： TreeSet, HashSet
// 1. 没有定于顺序的限制 no defined ordering, it's chaotic 混乱  ===> 不能够取指定位置的Item
// 2. 不能包含重复的数据, 默认会通过equals()来判断比较其中的元素     ===> 需要同时重写 equals() & hashCode()
public final class BaseHashSet {

    private final Set<BaseHashSet> sets;

    public BaseHashSet(String name, double period) {
        this.sets = new HashSet<>();
    }

    public void addSetItem(BaseHashSet newItem, EnumMaster type) {
        if (type == EnumMaster.DIME) {
            this.sets.add(newItem);
        }
    }

    // 通过重写BaseHashSet类型的equal()方法，自定义对象的比较方法，值下面添加的是两个不同的对象 !!
    public void testSet() {
        Set<BaseHashSet> mySets = new HashSet<>();
        mySets.add(new BaseHashSet("name", 100));
        mySets.add(new BaseHashSet("name", 200));
    }

    public static void main(String[] args) {
        Set<Integer> squares = new HashSet<>();
        Set<Integer> cubes = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            squares.add(i * i);
            cubes.add(i * i * i);
        }
        // Union 合并的时候，不会添加同样的Item到Set中                  ====> C#区别： evenNumbers.Count & numbers.UnionWith(oddNumbers);
        Set<Integer> union = new HashSet<>(squares);
        union.addAll(cubes);
        System.out.println("The amount size is " + union.size());  // ====> Outer Join

        // Intersection: retainAll() 提取公共部分，保留全部
        Set<Integer> intersection = new HashSet<>(squares);        // ====> Inner Join
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
