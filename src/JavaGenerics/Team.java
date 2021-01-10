package JavaGenerics;

import java.util.ArrayList;
import java.util.List;

// 泛型的认识 <type parameter>
// 0. 泛型类，泛型接口
// 1. 约束List<>中类型，保证类型的安全，在编译的时候发现错误 !!!
// 2. 解决代码和算法重复的问题
// 3. 使用泛型类来避免类型的膨胀 !!

/**
 * 大写的含义 ：
 * E -> Element
 * T -> Type
 * K -> Key
 * V -> Value
 * S U V -> 2nd, 3rd, 4th
 */
// ? 通配符类型
// <? extends T> 表示泛型参数是T或者T的子类
// <? super T>   表示泛型参数是T或者他的父类型, 直至Object
// 这里的泛型也可以使用基本类型 !!! 必须给类型添加限制, 类型约束
// 同时声明T的继承关系和实现关系 !! 先class 后interface

// TODO: super 不能直接作用在泛型类的定义上 ????
public class Team<T extends Player, E extends IPlay> implements Comparable<Team<T, E>> {  // ===> C#区别：class GenericClass<T> where T : struct {}

    private String name;
    private int won = 0;

    // 泛型：类型是抽象类 ===> 所有抽象类型的实现类型都可以添加
    private List<T> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public boolean addPlayer(T player) {
        if (!members.contains(player)) {
            members.add(player);
            return true;
        }
        return false;
    }

    // 只有同样类型的team才能比赛 ==> 使用泛型来约束
    public void matchResult(Team<T, E> opponent, int ourScore, int theirScore) {
        // 记录比赛结果: 双方team队伍都需要计数 !!
    }

    // 比较同类型的team之间的排名 ==> 使用泛型来约束
    @Override
    public int compareTo(Team<T, E> team) {
        if (this.ranking() > team.ranking()) {
            return -1;
        } else if (this.ranking() < team.ranking()) {
            return 1;
        } else {
            return 0;
        }
    }

    // 当前队伍的排名计算：属于team的算法逻辑
    public int ranking() {
        return won * 2 + 10;
    }

    /**
     * Java中泛型方法, 泛型参数<T>写在方法的返回类型之前   ===> C#区别：泛型参数紧跟着方法的名称 public void Find<T>(T value) where T : class {}
     */
    // 1. 给Comparable<T> 给接口传入的类型必须是T泛型或者是它的父级类型
    // 2. List<T> 泛型接口所传入的类型(列表中的类型)必须是实现了Comparable接口的类型
    public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        System.out.println("Test method");
        return 0;
    }
}
