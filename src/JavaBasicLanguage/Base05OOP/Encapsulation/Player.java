package JavaBasicLanguage.Base05OOP.Encapsulation;

// 封装的基本使用 ===> Black box 黑盒
public class Player {

    // Fields 一旦公开
    // 1. 所有使用这个变量的地方都将在名称变动之后受到影响 !!! 使用refactor > rename批量更改
    // 2. 在没有使用构造器的情况下，对于数据的初始化会非常的困难 !!! 无法确保数据的有效性
    public String name;
    public int health;
    public String weapon;

    // 3. Control the process 自定义控制逻辑和过程
    public void loseHealth(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            System.out.println("The player knocked oyut");
        }
    }

    public int getHealthRemaining() {
        return health;
    }

}
